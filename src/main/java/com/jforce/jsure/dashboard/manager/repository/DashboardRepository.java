package com.jforce.jsure.dashboard.manager.repository;

import com.jforce.jsure.base.db.repository.BaseDaoRepository;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends BaseDaoRepository<Dashboard> {

    @Query("select d from Dashboard d where d.username =:username")
    List<Dashboard> findCurrentDashboardsByUsername(String username);
}
