package com.baidu.oped.iop.m4.domain.entity.alert;

import static com.baidu.oped.iop.m4.domain.entity.common.MetricType.INSTANCE;

import static javax.persistence.EnumType.STRING;

import com.baidu.oped.iop.m4.domain.entity.common.MetricType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

/**
 * @author mason
 */
@Embeddable
public class MonitoringObject {

    @Column(name = "monitoring_object_name")
    private String name;

    @Enumerated(STRING)
    @Column(name = "monitoring_object_type")
    private MetricType type = INSTANCE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetricType getType() {
        return type;
    }

    public void setType(MetricType type) {
        this.type = type;
    }
}
