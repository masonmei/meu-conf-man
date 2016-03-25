package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of DerivedTask.
 *
 * @author mason
 */
public class DerivedTaskDto extends AbstractAuditableDto<DerivedTask> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

    @Override
    public void fromModel(DerivedTask derivedTask) {
        initProcess(derivedTask);
        this.id = derivedTask.getId();
        this.productName = derivedTask.getProductName();
        this.appName = derivedTask.getAppName();
        this.name = derivedTask.getName();
    }

    @Override
    public void toModel(DerivedTask derivedTask) {
        derivedTask.setProductName(productName);
        derivedTask.setAppName(appName);
        derivedTask.setName(name);

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
