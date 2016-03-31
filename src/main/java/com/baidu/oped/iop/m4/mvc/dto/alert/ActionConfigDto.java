package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.ActionConfig;
import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mason
 */
public class ActionConfigDto implements Dto<ActionConfig> {

    private static final long serialVersionUID = 8879057522480364864L;

    private int alertThreshold;
    private Set<TimeRangeDto> disabledTimes = new HashSet<>();

    @Override
    public ActionConfigDto fromModel(ActionConfig model) {
        this.alertThreshold = model.getAlertThreshold();
        this.disabledTimes = model.getDisabledTimes()
                .stream()
                .map(timeRange -> new TimeRangeDto().fromModel(timeRange))
                .collect(Collectors.toSet());
        return this;
    }

    @Override
    public void toModel(ActionConfig model) {
        model.setAlertThreshold(alertThreshold);
        model.setDisabledTimes(disabledTimes.stream()
                .map(disabledTimeDto -> {
                    TimeRange timeRange = new TimeRange();
                    disabledTimeDto.toModel(timeRange);
                    return timeRange;
                })
                .collect(Collectors.toSet()));
    }

    public int getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    public Set<TimeRangeDto> getDisabledTimes() {
        return disabledTimes;
    }

    public void setDisabledTimes(Set<TimeRangeDto> disabledTimes) {
        this.disabledTimes = disabledTimes;
    }
}
