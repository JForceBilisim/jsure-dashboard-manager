package com.jforce.jsure.dashboard.manager.service;

import com.jforce.jsure.base.db.service.BaseDbService;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;

import java.util.List;

public interface IDashboardService extends BaseDbService<Dashboard> {

    List<Dashboard> findAllDashboards();

    List<Dashboard> findCurrentDashboardsByUser();
}
