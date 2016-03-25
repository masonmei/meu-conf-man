package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Action;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of Action.
 *
 * @author mason
 */
public class ActionDto implements Dto<Action> {

    private Long id;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;
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

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public void toModel(Action action) {
        action.setProductName(productName);
        action.setAppName(appName);
        action.setName(name);

    }

    @Override
    public void fromModel(Action action) {
        this.id = action.getId();
        this.productName = action.getProductName();
        this.appName = action.getAppName();
        this.name = action.getName();
        this.lastModifiedBy = action.getLastModifiedBy();
        this.lastModifiedDate = action.getLastModifiedDate().toDate();
        this.version = action.getVersion();
    }
}
