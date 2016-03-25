package com.baidu.oped.iop.m4.mvc.dto.metric;

import com.baidu.oped.iop.m4.domain.entity.common.MetricType;
import com.baidu.oped.iop.m4.domain.entity.metric.Metric;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of Metric.
 *
 * @author mason
 */
public class MetricDto extends AbstractAuditableDto<Metric> {

    private static final long serialVersionUID = -3909955811666777015L;

    private Long id;
    private String productName;
    private String appName;
    private String name;
    private MetricType type;
    private String source;
    private String alias;

    @Override
    public void fromModel(Metric metric) {
        initProcess(metric);
        this.id = metric.getId();
        this.productName = metric.getProductName();
        this.appName = metric.getAppName();
        this.name = metric.getName();
        this.alias = metric.getAlias();
        this.source = metric.getSource();
        this.type = metric.getType();
    }

    @Override
    public void toModel(Metric metric) {
        metric.setProductName(productName);
        metric.setAppName(appName);
        metric.setName(name);
        metric.setAlias(alias);
        metric.setSource(source);
        metric.setType(type);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
