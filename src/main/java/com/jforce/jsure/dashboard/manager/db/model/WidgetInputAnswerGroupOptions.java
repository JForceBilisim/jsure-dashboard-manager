package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.dashboard.manager.converter.LanguageSupportedMapConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class WidgetInputAnswerGroupOptions extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WidgetInputAnswerGroup widgetInputAnswerGroup;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "option_label",nullable = false)
    private Map<LanguageType,String> optionLabel;

    @Column(name = "option_value",nullable = false)
    private String optionValue;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
