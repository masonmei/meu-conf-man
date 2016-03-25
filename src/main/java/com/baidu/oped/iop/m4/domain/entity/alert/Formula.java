package com.baidu.oped.iop.m4.domain.entity.alert;

import static javax.persistence.EnumType.STRING;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

/**
 * @author mason
 */
@Embeddable
public class Formula {

    @Column(name = "formula_metric")
    private String metric;

    @Enumerated(STRING)
    private Statistic statistic;

    @Enumerated(STRING)
    @Column(name = "formula_comparison_operator")
    private ComparisonOperator comparisonOperator;

    @Column(name = "formula_value")
    private BigDecimal value;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
