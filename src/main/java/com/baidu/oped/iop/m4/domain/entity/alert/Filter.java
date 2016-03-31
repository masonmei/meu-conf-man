package com.baidu.oped.iop.m4.domain.entity.alert;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mason
 */
@Embeddable
public class Filter {

    @Column(name = "filter_total")
    private int total;
    @Column(name = "filter_max")
    private int max;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
