package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WidgetInput extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Widget widget;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WidgetInputType widgetInputType;

    @Column(name = "isMandatory",nullable = false)
    private Boolean isMandatory;

    @Column(name = "defaultValue")
    private String defaultValue;

    @Column(name = "minValue")
    private String minValue;

    @Column(name = "maxValue")
    private String maxValue;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private WidgetInputAnswerGroup widgetInputAnswerGroup;


}
