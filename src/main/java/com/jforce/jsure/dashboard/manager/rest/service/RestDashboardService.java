package com.jforce.jsure.dashboard.manager.rest.service;

import com.jforce.jsure.base.restservice.model.RestRootEntity;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;

import java.util.List;

public interface RestDashboardService {

    RestRootEntity<List<DtoDashboard>> findAllDashboards();

    RestRootEntity<List<DtoDashboard>> findCurrentDashboardsByUser();
}
