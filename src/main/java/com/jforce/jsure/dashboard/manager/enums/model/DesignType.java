package com.jforce.jsure.dashboard.manager.enums.model;

import com.jforce.jsure.base.enums.model.IEnumBundleBase;

public enum DesignType implements IEnumBundleBase {
    DYNAMIC("dashboard-design-type.dynamic"),
    PRE_DEFINED("dashboard-design-type.pre-defined");

    private String bundleCode;

    private DesignType(String bundleCode) {
        this.bundleCode = bundleCode;
    }

    @Override
    public String getBundleCode() {
        return this.bundleCode;
    }

    @Override
    public String getValue() {
        return name();
    }

}


