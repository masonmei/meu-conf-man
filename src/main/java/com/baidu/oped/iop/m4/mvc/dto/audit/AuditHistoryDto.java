package com.baidu.oped.iop.m4.mvc.dto.audit;

import com.baidu.oped.iop.m4.domain.entity.audit.AuditHistory;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

/**
 * Data translate object of AuditHistory.
 *
 * @author mason
 */
public class AuditHistoryDto implements Dto<AuditHistory> {

    private Long id;
    private int version;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void toModel(AuditHistory auditHistory) {
        auditHistory.setProductName(productName);
        auditHistory.setAppName(appName);
        auditHistory.setName(name);

    }

    @Override
    public void fromModel(AuditHistory auditHistory) {
        this.id = auditHistory.getId();
        this.productName = auditHistory.getProductName();
        this.appName = auditHistory.getAppName();
        this.name = auditHistory.getName();
        this.version = auditHistory.getVersion();
    }
}
