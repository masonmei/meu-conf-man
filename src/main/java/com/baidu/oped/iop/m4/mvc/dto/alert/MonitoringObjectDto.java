package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.MonitoringObject;
import com.baidu.oped.iop.m4.domain.entity.common.MetricType;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mason
 */
public class MonitoringObjectDto implements Dto<MonitoringObject>{
    private static final long serialVersionUID = 8342646557424181501L;

    private MetricType type;
    private Set<String> names = new HashSet<>();

    @Override
    public MonitoringObjectDto fromModel(MonitoringObject model) {
        this.names = StringUtils.commaDelimitedListToSet(model.getName());
        this.type = model.getType();
        return this;
    }

    @Override
    public void toModel(MonitoringObject model) {
        model.setName(StringUtils.collectionToCommaDelimitedString(names));
        model.setType(type);
    }

    public MetricType getType() {
        return type;
    }

    public void setType(MetricType type) {
        this.type = type;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }
}
