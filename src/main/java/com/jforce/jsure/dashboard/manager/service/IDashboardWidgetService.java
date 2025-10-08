package com.jforce.jsure.dashboard.manager.service;

import com.jforce.jsure.base.db.service.BaseDbService;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.db.model.DashboardWidget;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;

import java.util.List;

public interface IDashboardWidgetService extends BaseDbService<DashboardWidget> {

    List<DtoDashboardInfo> findWidgetsOfDashboards(List<Dashboard> dashboards);

    public DashboardWidget findDashboardWidgetByDashboardAndWidget(String dashboardId, String widgetId);
}
