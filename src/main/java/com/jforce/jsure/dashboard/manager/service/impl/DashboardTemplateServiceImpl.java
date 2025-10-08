package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.db.model.DashboardTemplate;
import com.jforce.jsure.dashboard.manager.repository.DashboardTemplateRepository;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardTemplate;
import com.jforce.jsure.dashboard.manager.service.IDashboardTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardTemplateServiceImpl extends BaseDbServiceImpl<DashboardTemplateRepository, DashboardTemplate> implements IDashboardTemplateService {

    @Override
    public Class<?> getDTOClassForService() {
        return DtoDashboardTemplate.class;
    }

    @Override
    public <D extends DtoEntityModel> D toDTO(DashboardTemplate dashboardTemplate) {
        DtoDashboardTemplate dtoDashboardTemplate = super.toDTO(dashboardTemplate);
        return (D) dtoDashboardTemplate;
    }
}
