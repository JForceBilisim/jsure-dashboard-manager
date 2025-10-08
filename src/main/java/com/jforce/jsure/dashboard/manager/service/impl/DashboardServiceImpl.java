package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.base.security.model.JSureSessionInstance;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.repository.DashboardRepository;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends BaseDbServiceImpl<DashboardRepository, Dashboard> implements IDashboardService {

    @Autowired
    private IDashboardTemplateService dashboardTemplateService;


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
}
