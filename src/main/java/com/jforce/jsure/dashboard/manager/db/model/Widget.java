package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.dashboard.manager.converter.LanguageSupportedMapConverter;
import com.jforce.jsure.dashboard.manager.converter.PanelSizeConverter;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import com.jforce.jsure.dashboard.manager.enums.model.WidgetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Widget extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "widget_code",unique = true,nullable = false,length = 5)
    private String widgetCode;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "name",nullable = false)
    private Map<LanguageType,String> name;

    @Column(name = "is_manuel_refreshable", nullable = false)
    private Boolean isManuelRefreshable;

    @Column(name = "is_auto_refresh_enabled", nullable = false)
    private Boolean isAutoRefreshEnabled;

    @Column(name = "auto_refresh_duration_in_seconds")
    private Long autoRefreshDurationInSeconds;

    @Column(name = "is_maximize", nullable = false)
    private Boolean isMaximize;

    @Column(name = "module_code", nullable = false, length = 10)
    private String moduleCode;

    @Convert(converter = PanelSizeConverter.class)
    @Column(name = "panel_sizes")
    private List<PanelSize> panelSizes;

    @Enumerated(EnumType.STRING)
    @Column(name = "widget_type", nullable = false)
    private WidgetType widgetType;
}
