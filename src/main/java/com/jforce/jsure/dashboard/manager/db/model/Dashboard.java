package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureSimpleCompanyEntity;
import com.jforce.jsure.dashboard.manager.enums.model.DashboardType;
import com.jforce.jsure.dashboard.manager.enums.model.DesignType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Dashboard extends JsureSimpleCompanyEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_type",nullable = false)
    private DesignType designType;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "is_main_dashboard")
    private Boolean isMainDashboard;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private DashboardTemplate dashboardTemplate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dashboard_type",nullable = false)
    private DashboardType dashboardType;

}
