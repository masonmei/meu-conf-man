package com.baidu.oped.iop.m4.domain.entity.alert;

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mason
 */
@Embeddable
public class TimeRange {

    @Column(nullable = false, name = "from_time")
    private Time from;

    @Column(nullable = false, name = "to_time")
    private Time to;

    public Time getFrom() {
        return from;
    }

    public void setFrom(Time from) {
        this.from = from;
    }

    public Time getTo() {
        return to;
    }

    public void setTo(Time to) {
        this.to = to;
    }
}
