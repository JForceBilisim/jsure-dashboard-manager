package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.restservice.model.DtoCrudModel;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoDashboardWidgetIU extends DtoCrudModel {

    private String widgetId;

    private Integer coordX;

    private Integer coordY;

    private PanelSize panelSize;
}
