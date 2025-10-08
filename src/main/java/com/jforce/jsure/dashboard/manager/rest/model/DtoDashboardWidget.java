package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoDashboardWidget extends DtoEntityModel {

    private DtoDashboard dashboard;

    private DtoWidget widget;

    private Integer coordX;

    private Integer coordY;

    private PanelSize panelSize;

}
