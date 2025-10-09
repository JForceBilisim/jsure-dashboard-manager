package com.jforce.jsure.dashboard.manager.repository;

import com.jforce.jsure.base.db.repository.BaseDaoRepository;
import com.jforce.jsure.dashboard.manager.db.model.Dashboard;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardRepository extends BaseDaoRepository<Dashboard> {

    @EntityGraph(value = "dashboard-template-detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select d from Dashboard d where d.username =:username")
    List<Dashboard> findCurrentDashboardsByUsername(String username);

    @EntityGraph(value = "dashboard-template-detail", type = EntityGraph.EntityGraphType.FETCH)
    List<Dashboard> findAll();

    @EntityGraph(value = "dashboard-template-detail", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Dashboard> findById(String id);
}
