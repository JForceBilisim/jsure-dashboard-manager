package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.db.model.Widget;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoWidgetInfo extends DtoEntityModel {

    private DtoWidget widget;

    private Integer coordX;

    private Integer coordY;

    private PanelSize panelSize;
}
