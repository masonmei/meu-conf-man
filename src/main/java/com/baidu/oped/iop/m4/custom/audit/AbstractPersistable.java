package com.baidu.oped.iop.m4.custom.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Root of all the persistable.
 *
 * @author mason
 */
@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> implements Persistable<PK> {

    private static final long serialVersionUID = -5554308939380869754L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private PK id;

    public PK getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setId(final PK id) {
        this.id = id;
    }

    /**
     * Must be {@link Transient} in order to ensure that no JPA provider complains because of a missing setter.
     *
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass()
                .getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPersistable<?> that = (AbstractPersistable<?>) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
