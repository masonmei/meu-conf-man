package com.baidu.oped.iop.m4.domain.entity.alert;

import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

/**
 * @author mason
 */
@Embeddable
public class ActionConfig {
    @ElementCollection(targetClass = TimeRange.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "action_config_disabled_times", joinColumns = {@JoinColumn(name = "action_config_id")})
    private Set<TimeRange> disabledTimes;

    @Column(name = "action_config_alert_threshold")
    private int alertThreshold;

    public int getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    public Set<TimeRange> getDisabledTimes() {
        return disabledTimes;
    }

    public void setDisabledTimes(Set<TimeRange> disabledTimes) {
        this.disabledTimes = disabledTimes;
    }
}
