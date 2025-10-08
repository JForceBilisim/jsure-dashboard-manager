package com.jforce.jsure.dashboard.manager.repository;

import com.jforce.jsure.base.db.repository.BaseDaoRepository;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import com.jforce.jsure.dashboard.manager.db.model.DashboardWidget;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardWidgetRepository extends BaseDaoRepository<DashboardWidget> {

    @EntityGraph(value = "dashboard-widget-detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select dw from DashboardWidget dw where dw.dashboard.id in :dashboardIds")
    List<DashboardWidget> findWidgetsByDashboard(List<String> dashboardIds);

    @Query("select dw from DashboardWidget dw where dw.dashboard.id =:dashboardId and dw.widget.id =:widgetId")
    DashboardWidget findDashboardWidgetByDashboardAndWidget(String dashboardId, String widgetId);
}
