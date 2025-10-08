package com.jforce.jsure.dashboard.manager.rest.service.impl;

import com.jforce.jsure.base.restservice.base.BaseRestController;
import com.jforce.jsure.base.restservice.model.RestRootEntity;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.db.model.DashboardWidget;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardIU;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardWidget;
import com.jforce.jsure.dashboard.manager.rest.service.RestDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardWidgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "${jsure-dm.tags.dashboard-service.name}", description = "${jsure-dm.tags.dashboard.description}")
public class RestDashboardServiceImpl extends BaseRestController implements RestDashboardService {

    private final IDashboardService dashboardService;

    private final IDashboardWidgetService dashboardWidgetService;

    @Override
    @Operation(description = "${jsure-dm.apis.operations.list-all-dashboards.description}", summary = "${jsure-dm.apis.operations.list-all-dashboards.summary}", operationId = "list-all-dashboards")
    @GetMapping(path = "${jsure-dm.apis.operations.list-all-dashboards.path}", produces = { "application/json" })
    public RestRootEntity<List<DtoDashboard>> findAllDashboards() {
        List<Dashboard> dashboards = dashboardService.findAllDashboards();
        return ok(dashboardService.toDTOList(dashboards));
    }

    @Override
    @Operation(description = "${jsure-dm.apis.operations.find-current-dashboards-by-user.description}", summary = "${jsure-dm.apis.operations.find-current-dashboards-by-user.summary}", operationId = "find-current-dashboards-by-user")
    @GetMapping(path = "${jsure-dm.apis.operations.find-current-dashboards-by-user.path}", produces = { "application/json" })
    public RestRootEntity<List<DtoDashboardInfo>> findCurrentDashboardsByUser() {
        List<Dashboard> currentDashboards = dashboardService.findCurrentDashboardsByUser();
        return ok(dashboardWidgetService.findWidgetsOfDashboards(currentDashboards));
    }

    @Override
    @Operation(description = "${jsure-dm.apis.operations.create-dashboard.description}", summary = "${jsure-dm.apis.operations.create-dashboard.summary}", operationId = "create-dashboard")
    @PostMapping(path = "${jsure-dm.apis.operations.create-dashboard.path}", produces = { "application/json" })
    public RestRootEntity<DtoDashboardInfo> createNewDashboard(@RequestBody @Validated DtoDashboardIU dtoDashboardIU) {
        return ok(dashboardService.createNewDashboard(dtoDashboardIU));
    }

}
