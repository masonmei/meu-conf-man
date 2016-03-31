package com.baidu.oped.iop.m4.domain.repository;

/**
 * @author mason
 */
public enum SearchField {
    ALL,
    NAME,
    TARGET,
    NOTIFY_TYPE,
    MONITORING_OBJECT,
    FORMULA,
    PARAMS,
    CALLBACK_URL;

    public static SearchField get(String name) {
        if (name == null) {
            return ALL;
        }
        for (SearchField searchField : values()) {
            if (searchField.name()
                    .equalsIgnoreCase(name)) {
                return searchField;
            }
        }
        return ALL;
    }
}
