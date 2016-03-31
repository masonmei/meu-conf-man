package com.baidu.oped.iop.m4.mvc.dto.common;

import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;

import java.util.Date;

/**
 * @author mason
 */
public abstract class AbstractAppLayerAuditableDto<T extends AppLayerEntity<Long>>
        extends AbstractProductLayerAuditableDto<T> {

    private String appName;

    public void initProcess(T model) {
        super.initProcess(model);
        this.appName = model.getAppName();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
