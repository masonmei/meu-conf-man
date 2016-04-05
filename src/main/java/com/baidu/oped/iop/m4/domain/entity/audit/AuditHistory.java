package com.baidu.oped.iop.m4.domain.entity.audit;

import static javax.persistence.EnumType.STRING;

import com.baidu.oped.iop.m4.custom.audit.AbstractPersistable;
import com.baidu.oped.iop.m4.utils.JacksonUtils;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "aggr_task_unique_index", columnList = "`product_name`, `app_name`, `name`, `entity_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`, `entity_name`, `action_date`"),
        @Index(name = "product_layer_index", columnList = "`product_name`, `entity_name`, `action_date`")})
public class AuditHistory extends AbstractPersistable<Long> {

    @NotNull
    @Column(name = "`product_name`", length = 128, nullable = false)
    private String productName;

    @NotNull
    @Column(name = "`name`", length = 128, nullable = false)
    private String name;

    @NotNull
    @Column(name = "`app_name`", length = 128, nullable = false)
    private String appName = "";

    private String author;

    @Enumerated(STRING)
    private ActionType action;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`action_date`")
    private Date actionDate;

    @Column(name = "`entity_name`")
    private String entityName;

    @Basic(optional = false)
    @Lob
    private String details;

    private Long version;

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public <T> T getDetailObject(Class<T> clazz) {
        return JacksonUtils.deserialize(details, clazz);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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

    public <T> void setDetailObject(T object) {
        setDetails(JacksonUtils.serialize(object));
    }
}
