package com.jforce.jsure.dashboard.manager.service;

import com.jforce.jsure.base.db.service.BaseDbService;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardIU;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;

import java.util.List;

public interface IDashboardService extends BaseDbService<Dashboard> {

    List<Dashboard> findAllDashboards();

    List<Dashboard> findCurrentDashboardsByUser();

    DtoDashboardInfo createNewDashboard(DtoDashboardIU dtoDashboardIU);

    DtoDashboardInfo updateDashboard(String id, DtoDashboardIU dtoDashboardIU);

    DtoDashboard deleteDashboard(String id);
}
