package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.ComparisonOperator;
import com.baidu.oped.iop.m4.domain.entity.alert.Formula;
import com.baidu.oped.iop.m4.domain.entity.alert.Statistic;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.math.BigDecimal;

/**
 * @author mason
 */
public class FormulaDto implements Dto<Formula>{
    private static final long serialVersionUID = -9115955723947528269L;

    private String metric;
    private ComparisonOperator comparisonOperator;
    private Statistic statistic;
    private BigDecimal value;

    @Override
    public FormulaDto fromModel(Formula model) {
        this.metric = model.getMetric();
        this.comparisonOperator = model.getComparisonOperator();
        this.statistic = model.getStatistic();
        this.value = model.getValue();
        return this;
    }

    @Override
    public void toModel(Formula model) {
        model.setMetric(metric);
        model.setComparisonOperator(comparisonOperator);
        model.setStatistic(statistic);
        model.setValue(value);
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
