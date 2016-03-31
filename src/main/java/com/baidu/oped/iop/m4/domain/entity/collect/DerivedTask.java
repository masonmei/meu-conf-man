package com.baidu.oped.iop.m4.domain.entity.collect;

import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "derived_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "product_layer_index", columnList = "`product_name`"),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
public class DerivedTask extends AppLayerEntity<Long> {

    @Column(length = 255)
    private String formula;

    @Column(length = 1024)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
