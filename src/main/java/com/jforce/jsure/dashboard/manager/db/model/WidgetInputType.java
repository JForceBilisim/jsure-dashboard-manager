package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.dashboard.manager.converter.LanguageSupportedMapConverter;
import com.jforce.jsure.dashboard.manager.enums.model.InputType;
import com.jforce.jsure.dashboard.manager.enums.model.WidgetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class WidgetInputType extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "widget_input_code",unique = true,nullable = false)
    private String widgetInputCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "input_type", nullable = false)
    private InputType inputType;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "name",nullable = false)
    private Map<LanguageType,String> name;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "description",nullable = false)
    private String description;
}
