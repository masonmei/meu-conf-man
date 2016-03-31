package com.baidu.oped.iop.m4.mvc.dto.audit;

import com.baidu.oped.iop.m4.domain.entity.audit.ActionType;
import com.baidu.oped.iop.m4.domain.entity.audit.AuditHistory;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

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
    private ActionType action;
    private Date actionDate;
    private String author;
    private String entityName;
    private String details;

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
    public void toModel(AuditHistory model) {
        model.setProductName(productName);
        model.setAppName(appName);
        model.setName(name);
        model.setAction(action);
        model.setActionDate(actionDate);
        model.setAuthor(author);
        model.setEntityName(entityName);
        model.setDetails(details);
    }

    @Override
    public AuditHistoryDto fromModel(AuditHistory model) {
        this.id = model.getId();
        this.productName = model.getProductName();
        this.appName = model.getAppName();
        this.name = model.getName();
        this.version = model.getVersion();
        this.action = model.getAction();
        this.actionDate = model.getActionDate();
        this.author = model.getAuthor();
        this.entityName = model.getEntityName();
        this.details = model.getDetails();
        return this;
    }
}
