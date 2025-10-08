package com.jforce.jsure.dashboard.manager.rest.service;

import com.jforce.jsure.base.restservice.model.RestRootEntity;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboard;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardIU;
import com.jforce.jsure.dashboard.manager.rest.model.DtoDashboardInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface RestDashboardService {

    RestRootEntity<List<DtoDashboard>> findAllDashboards();

    RestRootEntity<List<DtoDashboardInfo>> findCurrentDashboardsByUser();

    RestRootEntity<DtoDashboardInfo> createNewDashboard(DtoDashboardIU dtoDashboardIU);

    RestRootEntity<DtoDashboardInfo> updateNewDashboard(String id, DtoDashboardIU dtoDashboardIU);
}
