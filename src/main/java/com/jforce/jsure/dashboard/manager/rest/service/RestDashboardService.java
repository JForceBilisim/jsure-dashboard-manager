package com.jforce.jsure.dashboard.manager.rest.service;

import com.jforce.jsure.base.restservice.model.RestRootEntity;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardIU;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;


import java.util.List;

public interface RestDashboardService {

    RestRootEntity<List<DtoDashboard>> findAllDashboards();

    RestRootEntity<List<DtoDashboardInfo>> findCurrentDashboardsByUser();

    RestRootEntity<DtoDashboardInfo> findDashboardById(String id);

    RestRootEntity<DtoDashboardInfo> createNewDashboard(DtoDashboardIU dtoDashboardIU);

    RestRootEntity<DtoDashboardInfo> updateDashboard(String id, DtoDashboardIU dtoDashboardIU);

    RestRootEntity<DtoDashboard> deleteDashboard(String id);

    RestRootEntity<DtoDashboard> makeDashboardMain(String id);

    RestRootEntity<DtoDashboard> rollbackDashboardMain(String id);
}
