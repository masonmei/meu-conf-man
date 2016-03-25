package com.baidu.oped.iop.m4.custom.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author mason
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable<PK extends Serializable> extends AbstractPersistable<PK>
        implements Auditable<String, PK> {

    private static final long serialVersionUID = -905215232065386023L;

    @JsonIgnore
    private String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastModifiedDate;

    @Version
    @JsonIgnore
    private Long version;

    /* Migrate to modified;*/
    @Override
    public String getCreatedBy() {
        return null;
    }

    @Override
    public void setCreatedBy(String createdBy) {
    }

    @Override
    public DateTime getCreatedDate() {
        return null;
    }

    @Override
    public void setCreatedDate(DateTime creationDate) {
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
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
