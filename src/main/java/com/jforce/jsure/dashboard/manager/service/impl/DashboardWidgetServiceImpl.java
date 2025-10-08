package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.enums.model.JMessageType;
import com.jforce.jsure.base.exceptions.JSureException;
import com.jforce.jsure.base.exceptions.model.JMessageFactory;
import com.jforce.jsure.base.exceptions.model.JSureMessage;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.db.model.DashboardWidget;
import com.jforce.jsure.dashboard.manager.db.model.Widget;
import com.jforce.jsure.dashboard.manager.repository.DashboardWidgetRepository;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardWidget;
import com.jforce.jsure.dashboard.manager.rest.model.DtoWidget;
import com.jforce.jsure.dashboard.manager.rest.model.DtoWidgetInfo;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardWidgetService;
import com.jforce.jsure.dashboard.manager.service.IWidgetService;
import com.jforce.jsure.utils.JObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardWidgetServiceImpl extends BaseDbServiceImpl<DashboardWidgetRepository, DashboardWidget> implements IDashboardWidgetService {

    @Autowired
    private IDashboardService dashboardService;

    @Autowired
    private IWidgetService widgetService;

    @Override
    public Class<?> getDTOClassForService() {
        return DtoDashboardWidget.class;
    }

    @Override
    public <D extends DtoEntityModel> D toDTO(DashboardWidget dashboardWidget) {
        DtoDashboardWidget dtoDashboardWidget = super.toDTO(dashboardWidget);
        if(dashboardService.isCanCopy(dashboardWidget.getDashboard())) {
            dtoDashboardWidget.setDashboard(dashboardService.toDTO(dashboardWidget.getDashboard()));
        }
        if(widgetService.isCanCopy(dashboardWidget.getWidget())) {
            dtoDashboardWidget.setWidget(widgetService.toDTO(dashboardWidget.getWidget()));
        }
        return (D) dtoDashboardWidget;
    }

    @Override
    public List<DtoDashboardInfo> findWidgetsOfDashboards(List<Dashboard> dashboards) {
        if(JObjectUtils.isEmpty(dashboards)) {
            throw new JSureException(new JSureMessage(JMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, JMessageFactory.ofBundle(JMessageType.NOT_EXISTS_IN_THE_RECORDS_1006.getBundleCode())));
        }
        List<String> dashboardIds = new ArrayList<>();
        dashboards.forEach(dashboard -> dashboardIds.add(dashboard.getId()));
        List<DashboardWidget> dashboardWidgets = dao.findWidgetsByDashboard(dashboardIds);
        Map<Dashboard, List<DashboardWidget>> grouped =
                dashboardWidgets.stream()
                        .collect(Collectors.groupingBy(
                                DashboardWidget::getDashboard,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        List<DtoDashboardInfo> dtoDashboardInfos = new ArrayList<>();
        for (Map.Entry<Dashboard, List<DashboardWidget>> entry : grouped.entrySet()) {
            Dashboard dashboard = entry.getKey();
            List<DashboardWidget> dwList = entry.getValue();

            List<DtoWidgetInfo> widgetDtos = dwList.stream().map(dw -> {
                DtoWidgetInfo dtoWidgetInfo = new DtoWidgetInfo();
                DtoWidget dtoWidget = widgetService.toDTO(dw.getWidget());
                dtoWidgetInfo.setWidget(dtoWidget);
                dtoWidgetInfo.setCoordX(dw.getCoordX());
                dtoWidgetInfo.setCoordY(dw.getCoordY());
                dtoWidgetInfo.setPanelSize(dw.getPanelSize());
                return dtoWidgetInfo;
            }).collect(Collectors.toList());

            DtoDashboardInfo dtoDashboardInfo = new DtoDashboardInfo();
            dtoDashboardInfo.setDashboard(dashboardService.toDTO(dashboard));

            dtoDashboardInfo.setWidgets(widgetDtos);

            dtoDashboardInfos.add(dtoDashboardInfo);
        }

        return dtoDashboardInfos;
    }
}
