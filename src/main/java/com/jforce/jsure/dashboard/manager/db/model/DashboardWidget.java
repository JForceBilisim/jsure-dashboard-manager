package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.dashboard.manager.converter.ConfigurationMapConverter;
import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "dashboard-widget-detail", attributeNodes = {
        @NamedAttributeNode(value = "widget") })
public class DashboardWidget extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dashboard dashboard;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Widget widget;

    @Column(name = "coord_x", nullable = false)
    private Integer coordX;

    @Column(name = "coord_y")
    private Integer coordY;

    @Enumerated(EnumType.STRING)
    @Column(name = "panel_size")
    private PanelSize panelSize;

    @Lob
    @Convert(converter = ConfigurationMapConverter.class)
    @Column(name = "configuration")
    private Map<String,String> configuration;

    @Column(name = "name")
    private String name;
}
