package com.baidu.oped.iop.m4.mvc.dto.common;

import com.baidu.oped.iop.m4.domain.entity.common.ProductLayerEntity;

/**
 * @author mason
 */
public abstract class AbstractProductLayerAuditableDto<T extends ProductLayerEntity<Long>>
        extends AbstractAuditableDto<T> {

    private Long id;
    private String productName;
    private String name;

    public void initProcess(T model) {
        super.initProcess(model);
        this.id = model.getId();
        this.productName = model.getProductName();
        this.name = model.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
