package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.base.security.model.JSureSessionInstance;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.repository.DashboardRepository;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import com.jforce.jsure.utils.JStringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends BaseDbServiceImpl<DashboardRepository, Dashboard> implements IDashboardService {



    @Override
    public Class<?> getDTOClassForService() {
        return DtoDashboard.class;
    }

    @Override
    public <D extends DtoEntityModel> D toDTO(Dashboard dashboard) {
        DtoDashboard dtoDashboard = super.toDTO(dashboard);

        return (D) dtoDashboard;
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
