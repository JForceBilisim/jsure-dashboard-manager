package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoDashboardInfo extends DtoEntityModel {

    private DtoDashboard dashboard;

    private List<DtoWidgetInfo> widgets;
}
