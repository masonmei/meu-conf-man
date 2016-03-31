package com.baidu.oped.iop.m4.mvc.dto.common;

import static com.baidu.oped.sia.boot.utils.Constrains.Datetime.DATE_TIME_FORMAT;

import com.baidu.oped.iop.m4.custom.audit.AbstractAuditable;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author mason
 */
public abstract class AbstractAuditableDto<T extends AbstractAuditable> implements Dto<T> {

    private String lastModifiedBy;
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private Date lastModifiedDate;
    private Long version;

    public void initProcess(T model) {
        this.lastModifiedBy = model.getLastModifiedBy();
        this.lastModifiedDate =
                model.getLastModifiedDate() == null ? null : model.getLastModifiedDate().toDate();
        this.version = model.getVersion();
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
