package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.restservice.model.DtoCrudModel;
import com.jforce.jsure.dashboard.manager.enums.model.DashboardType;
import com.jforce.jsure.dashboard.manager.enums.model.DesignType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoDashboardIU extends DtoCrudModel {

    private String name;

    private DesignType designType;

    private String username;

    private String dashboardTemplateId;

    private DashboardType dashboardType;

    private List<DtoDashboardWidgetIU> widgets;
}
