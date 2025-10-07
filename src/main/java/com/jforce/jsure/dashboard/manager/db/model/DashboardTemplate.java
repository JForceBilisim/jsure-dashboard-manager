package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.dashboard.manager.converter.LanguageSupportedMapConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class DashboardTemplate extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "code",nullable = false, unique = true)
    private String code;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "name",nullable = false)
    private Map<LanguageType,String> name;
}
