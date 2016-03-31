package com.baidu.oped.iop.m4.mvc.dto.alert;

import static com.baidu.oped.sia.boot.utils.Constrains.Datetime.TIME_FORMAT;

import com.baidu.oped.iop.m4.domain.entity.alert.TimeRange;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;
import com.baidu.oped.sia.boot.utils.Constrains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;

/**
 * @author mason
 */
public class TimeRangeDto implements Dto<TimeRange> {
    private static final long serialVersionUID = 1375586184540583406L;

    @DateTimeFormat(pattern = TIME_FORMAT)
    private LocalTime from;

    @DateTimeFormat(pattern = TIME_FORMAT)
    private LocalTime to;


    @Override
    public TimeRangeDto fromModel(TimeRange model) {
        this.from = model.getFrom().toLocalTime();
        this.to = model.getTo().toLocalTime();
        return this;
    }

    @Override
    public void toModel(TimeRange model) {
        model.setFrom(Time.valueOf(from));
        model.setTo(Time.valueOf(to));
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }
}
