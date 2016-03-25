package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Policy;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of Policy.
 *
 * @author mason
 */
public class PolicyDto extends AbstractAuditableDto<Policy> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

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

    @Override
    public void toModel(Policy policy) {
        policy.setProductName(productName);
        policy.setAppName(appName);
        policy.setName(name);
    }

    @Override
    public void fromModel(Policy policy) {
        initProcess(policy);
        this.id = policy.getId();
        this.productName = policy.getProductName();
        this.appName = policy.getAppName();
        this.name = policy.getName();
    }
}
