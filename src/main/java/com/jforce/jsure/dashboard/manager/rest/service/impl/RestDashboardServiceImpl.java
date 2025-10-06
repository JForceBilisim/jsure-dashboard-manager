package com.jforce.jsure.dashboard.manager.rest.service.impl;

import com.jforce.jsure.base.restservice.base.BaseRestController;
import com.jforce.jsure.base.restservice.model.RestRootEntity;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.rest.service.RestDashboardService;
import com.jforce.jsure.dashboard.manager.service.IDashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "${jsure-rs.tags.dashboard-service.name}", description = "${jsure-rs.tags.dashboard.description}")
public class RestDashboardServiceImpl extends BaseRestController implements RestDashboardService {

    private final IDashboardService dashboardService;

    @Override
    @Operation(description = "${jsure-rs.apis.operations.list-all-dashboards.description}", summary = "${jsure-rs.apis.operations.list-all-dashboards.summary}", operationId = "list-all-dashboards")
    @GetMapping(path = "${jsure-rs.apis.operations.list-all-dashboards.path}", produces = { "application/json" })
    public RestRootEntity<List<DtoDashboard>> findAllDashboards() {
        List<Dashboard> dashboards = dashboardService.findAllDashboards();
        return ok(dashboardService.toDTOList(dashboards));
    }

    @Override
    @Operation(description = "${jsure-rs.apis.operations.find-current-dashboards-by-user.description}", summary = "${jsure-rs.apis.operations.find-current-dashboards-by-user.summary}", operationId = "find-current-dashboards-by-user")
    @GetMapping(path = "${jsure-rs.apis.operations.find-current-dashboards-by-user.path}", produces = { "application/json" })
    public RestRootEntity<List<DtoDashboard>> findCurrentDashboardsByUser() {
        List<Dashboard> currentDashboards = dashboardService.findCurrentDashboardsByUser();
        return ok(dashboardService.toDTOList(currentDashboards));
    }

}
