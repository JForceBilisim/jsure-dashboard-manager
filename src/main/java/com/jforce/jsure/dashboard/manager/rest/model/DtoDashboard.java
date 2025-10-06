package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.annotations.definitions.EnumConverter;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.base.restservice.model.EnumValue;
import com.jforce.jsure.dashboard.manager.db.model.DashboardTemplate;
import com.jforce.jsure.dashboard.manager.enums.model.DashboardType;
import com.jforce.jsure.dashboard.manager.enums.model.DesignType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoDashboard extends DtoEntityModel {

    private String name;

    @EnumConverter(enumClass = DesignType.class, fieldName = "designType")
    private EnumValue designType;

    private String username;

    private Boolean isMainDashboard;

    private DtoDashboardTemplate dashboardTemplate;

    @EnumConverter(enumClass = DashboardType.class, fieldName = "dashboardType")
    private EnumValue dashboardType;
}
