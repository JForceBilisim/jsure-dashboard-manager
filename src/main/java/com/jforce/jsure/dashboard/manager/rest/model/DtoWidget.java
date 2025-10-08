package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DtoWidget extends DtoEntityModel {

    private String widgetCode;

    private Map<LanguageType,String> name;

    private Boolean isManuelRefreshable;

    private Boolean isAutoRefreshEnabled;

    private Long autoRefreshDurationInSeconds;

    private Boolean isMaximize;

    private String moduleCode;

    private List<PanelSize> panelSizes;
}
