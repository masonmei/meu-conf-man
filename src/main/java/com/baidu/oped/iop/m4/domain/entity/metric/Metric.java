package com.baidu.oped.iop.m4.domain.entity.metric;

import static javax.persistence.EnumType.STRING;

import com.baidu.oped.iop.m4.domain.entity.common.AppLayerEntity;
import com.baidu.oped.iop.m4.domain.entity.common.MetricType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mason
 */
@Entity
@Table(indexes = {
        @Index(name = "aggr_task_unique_index", columnList = "`product_name`, `app_name`, `name`", unique = true),
        @Index(name = "app_layer_index", columnList = "`product_name`, `app_name`")})
public class Metric extends AppLayerEntity<Long> {

    private static final long serialVersionUID = 2913724065625796907L;

    private String alias;

    @Enumerated(STRING)
    private MetricType type;

    private String source;


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MetricType getType() {
        return type;
    }

    public void setType(MetricType type) {
        this.type = type;
    }
}
