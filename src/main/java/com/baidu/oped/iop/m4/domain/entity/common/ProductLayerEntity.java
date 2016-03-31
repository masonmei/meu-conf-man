package com.baidu.oped.iop.m4.domain.entity.common;

import com.baidu.oped.iop.m4.custom.audit.AbstractAuditable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
@MappedSuperclass
public abstract class ProductLayerEntity<PK extends Serializable> extends AbstractAuditable<PK> {

    private static final long serialVersionUID = 934487915183531942L;

    @NotNull
    @Column(name = "`product_name`", length = 128, nullable = false)
    private String productName;

    @NotNull
    @Column(name = "`name`", length = 128, nullable = false)
    private String name;

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
