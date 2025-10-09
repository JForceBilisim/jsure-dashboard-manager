package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.enums.model.JMessageType;
import com.jforce.jsure.base.exceptions.JSureException;
import com.jforce.jsure.base.exceptions.model.JMessageFactory;
import com.jforce.jsure.base.exceptions.model.JSureMessage;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.base.security.model.JSureSessionInstance;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.db.model.DashboardTemplate;
import com.jforce.jsure.dashboard.manager.db.model.DashboardWidget;
import com.jforce.jsure.dashboard.manager.db.model.Widget;
import com.jforce.jsure.dashboard.manager.repository.DashboardRepository;
import com.jforce.jsure.dashboard.manager.rest.model.*;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardTemplateService;
import com.jforce.jsure.dashboard.manager.service.IDashboardWidgetService;
import com.jforce.jsure.dashboard.manager.service.IWidgetService;
import com.jforce.jsure.utils.JObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends BaseDbServiceImpl<DashboardRepository, Dashboard> implements IDashboardService {

    @Autowired
    private IDashboardTemplateService dashboardTemplateService;

    @Autowired
    @Lazy
    private IWidgetService widgetService;

    @Autowired
    @Lazy
    private IDashboardWidgetService dashboardWidgetService;


    @Override
    public Class<?> getDTOClassForService() {
        return DtoDashboard.class;
    }

    @Override
    public <D extends DtoEntityModel> D toDTO(Dashboard dashboard) {
        DtoDashboard dtoDashboard = super.toDTO(dashboard);
        if(dashboardTemplateService.isCanCopy(dashboard.getDashboardTemplate())) {
            dtoDashboard.setDashboardTemplate(dashboardTemplateService.toDTO(dashboard.getDashboardTemplate()));
        }
        return (D) dtoDashboard;
    }

    @Override
    public <D extends DtoEntityModel> List<D> toDTOList(List<Dashboard> dashboards) {
        List<D> dtoList = new ArrayList<>();
        if (dashboards != null && !dashboards.isEmpty()) {
            for (Dashboard t : dashboards) {
                dtoList.add(toDTO(t));
            }
        }
        return dtoList;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Dashboard> findAllDashboards() {
        return (List<Dashboard>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dashboard> findCurrentDashboardsByUser() {
        JSureSessionInstance sessionInstance = sessionInstanceService.getSessionInstance();
        return dao.findCurrentDashboardsByUsername(sessionInstance.getUserInformation().getUserName());
    }

    @Override
    public DtoDashboardInfo findDashboardById(String id) {
        Optional<Dashboard> dashboard = dao.findById(id);
        List<DashboardWidget> dashboardWidgets = dashboardWidgetService.findDashboardWidgetByDashboardId(id);
        return createNewDashboardInfo(dashboard.get(), dashboardWidgets);
    }

    @Override
    public DtoDashboardInfo createNewDashboard(DtoDashboardIU dtoDashboardIU) {
        if(JObjectUtils.isEmpty(dtoDashboardIU.getWidgets())) {
            throw new JSureException(new JSureMessage(JMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, JMessageFactory.ofBundle(JMessageType.NOT_EXISTS_IN_THE_RECORDS_1006.getBundleCode())));
        }
        Dashboard dashboard = saveNewDashboard(dtoDashboardIU);
        for(DtoDashboardWidgetIU dtoDashboardWidgetIU: dtoDashboardIU.getWidgets()) {
            saveNewDashboardWidget(dashboard, dtoDashboardWidgetIU);
        }
        return createDashboardInfo(dashboard, dtoDashboardIU);
    }

    @Override
    public DtoDashboardInfo updateDashboard(String id, DtoDashboardIU dtoDashboardIU) {
        Dashboard dashboard = findAndCheckById(id);
        updateDashboard(dashboard, dtoDashboardIU);
        for(DtoDashboardWidgetIU dtoDashboardWidgetIU: dtoDashboardIU.getWidgets()) {
            updateDashboardWidget(dashboard, dtoDashboardWidgetIU);
        }
        return createDashboardInfo(dashboard, dtoDashboardIU);
    }

    @Override
    public DtoDashboard deleteDashboard(String id) {
        Dashboard dashboard = findAndCheckById(id);
        List<DashboardWidget> dashboardWidgets = dashboardWidgetService.findDashboardWidgetByDashboardId(id);
        dashboardWidgetService.deleteAll(dashboardWidgets);
        delete(dashboard);
        return toDTO(dashboard);
    }

    @Override
    public DtoDashboard makeMainDashboard(String id) {
        Dashboard dashboard = findAndCheckById(id);
        List<Dashboard> userDashboards = dao.findCurrentDashboardsByUsername(sessionInstanceService.getSessionInstance().getUserInformation().getUserName());
        userDashboards.forEach(userDash -> {
            userDash.setIsMainDashboard(false);
            update(userDash);
        });
        dashboard.setIsMainDashboard(true);
        update(dashboard);
        return toDTO(dashboard);
    }

    @Override
    public DtoDashboard rollbackMainDashboard(String id) {
        Dashboard dashboard = findAndCheckById(id);
        dashboard.setIsMainDashboard(false);
        update(dashboard);
        return toDTO(dashboard);
    }

    private void updateDashboardWidget(Dashboard dashboard, DtoDashboardWidgetIU dtoDashboardWidgetIU) {
        List<DashboardWidget> dashboardWidgets = dashboardWidgetService.findDashboardWidgetByDashboardId(dashboard.getId());
        dashboardWidgetService.deleteAll(dashboardWidgets);
        saveNewDashboardWidget(dashboard, dtoDashboardWidgetIU);
    }

    private void updateDashboard(Dashboard dashboard, DtoDashboardIU dtoDashboardIU) {
        dashboard.setName(dtoDashboardIU.getName());
        dashboard.setDesignType(dtoDashboardIU.getDesignType());
        dashboard.setUsername(sessionInstanceService.getSessionInstance().getUserInformation().getUserName());
        if(dtoDashboardIU.getDashboardTemplateId() != null) {
            DashboardTemplate dashboardTemplate = dashboardTemplateService.findAndCheckById(dtoDashboardIU.getDashboardTemplateId());
            dashboard.setDashboardTemplate(dashboardTemplate);
        }
        dashboard.setDashboardType(dtoDashboardIU.getDashboardType());
        update(dashboard);
    }

    private Dashboard saveNewDashboard(DtoDashboardIU dtoDashboardIU) {
        Dashboard dashboard = new Dashboard();
        dashboard.setName(dtoDashboardIU.getName());
        dashboard.setDesignType(dtoDashboardIU.getDesignType());
        dashboard.setUsername(sessionInstanceService.getSessionInstance().getUserInformation().getUserName());
        if(dtoDashboardIU.getDashboardTemplateId() != null) {
            DashboardTemplate dashboardTemplate = dashboardTemplateService.findAndCheckById(dtoDashboardIU.getDashboardTemplateId());
            dashboard.setDashboardTemplate(dashboardTemplate);
        }
        dashboard.setDashboardType(dtoDashboardIU.getDashboardType());
        save(dashboard);
        return dashboard;
    }

    private void saveNewDashboardWidget(Dashboard dashboard, DtoDashboardWidgetIU dtoDashboardWidgetIU) {
        DashboardWidget dashboardWidget = new DashboardWidget();
        dashboardWidget.setDashboard(dashboard);
        Widget widget = widgetService.findAndCheckById(dtoDashboardWidgetIU.getWidgetId());
        dashboardWidget.setWidget(widget);
        dashboardWidget.setCoordX(dtoDashboardWidgetIU.getCoordX());
        dashboardWidget.setCoordY(dtoDashboardWidgetIU.getCoordY());
        dashboardWidget.setPanelSize(dtoDashboardWidgetIU.getPanelSize());
        dashboardWidgetService.save(dashboardWidget);
    }

    private DtoDashboardInfo createDashboardInfo(Dashboard dashboard, DtoDashboardIU dtoDashboardIU) {
        DtoDashboardInfo dtoDashboardInfo = new DtoDashboardInfo();
        dtoDashboardInfo.setDashboard(toDTO(dashboard));
        List<DtoWidgetInfo> widgetDtos = dtoDashboardIU.getWidgets().stream().map(dw -> {
            DtoWidgetInfo dtoWidgetInfo = new DtoWidgetInfo();
            Widget widget = widgetService.findAndCheckById(dw.getWidgetId());
            DtoWidget dtoWidget = widgetService.toDTO(widget);
            dtoWidgetInfo.setWidget(dtoWidget);
            dtoWidgetInfo.setCoordX(dw.getCoordX());
            dtoWidgetInfo.setCoordY(dw.getCoordY());
            dtoWidgetInfo.setPanelSize(dw.getPanelSize());
            return dtoWidgetInfo;
        }).collect(Collectors.toList());
        dtoDashboardInfo.setWidgets(widgetDtos);
        return dtoDashboardInfo;
    }

    private DtoDashboardInfo createNewDashboardInfo(Dashboard dashboard, List<DashboardWidget> dashboardWidgets) {
        DtoDashboardInfo dtoDashboardInfo = new DtoDashboardInfo();
        dtoDashboardInfo.setDashboard(toDTO(dashboard));
        List<DtoWidgetInfo> widgetDtos = dashboardWidgets.stream().map(dw -> {
            DtoWidgetInfo dtoWidgetInfo = new DtoWidgetInfo();
            Widget widget = widgetService.findAndCheckById(dw.getWidget().getId());
            DtoWidget dtoWidget = widgetService.toDTO(widget);
            dtoWidgetInfo.setWidget(dtoWidget);
            dtoWidgetInfo.setCoordX(dw.getCoordX());
            dtoWidgetInfo.setCoordY(dw.getCoordY());
            dtoWidgetInfo.setPanelSize(dw.getPanelSize());
            return dtoWidgetInfo;
        }).collect(Collectors.toList());
        dtoDashboardInfo.setWidgets(widgetDtos);
        return dtoDashboardInfo;
    }
}
