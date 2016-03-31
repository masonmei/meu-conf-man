package com.baidu.oped.iop.m4.mvc.dto.metric;

import com.baidu.oped.iop.m4.domain.entity.common.MetricType;
import com.baidu.oped.iop.m4.domain.entity.metric.Metric;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of Metric.
 *
 * @author mason
 */
public class MetricDto extends AbstractAppLayerAuditableDto<Metric> {

    private static final long serialVersionUID = -3909955811666777015L;

    private MetricType type;
    private String source;
    private String alias;

    @Override
    public MetricDto fromModel(Metric metric) {
        super.initProcess(metric);
        this.alias = metric.getAlias();
        this.source = metric.getSource();
        this.type = metric.getType();
        return this;
    }

    @Override
    public void toModel(Metric metric) {
        metric.setName(getName());
        metric.setAlias(alias);
        metric.setSource(source);
        metric.setType(type);
    }

    public MetricType getType() {
        return type;
    }

    public void setType(MetricType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
