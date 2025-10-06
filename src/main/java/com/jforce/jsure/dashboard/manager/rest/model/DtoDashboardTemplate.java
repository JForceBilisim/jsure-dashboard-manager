package com.jforce.jsure.dashboard.manager.rest.model;

import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DtoDashboardTemplate extends DtoEntityModel {

    private String code;

    private Map<LanguageType,String> name;
}
