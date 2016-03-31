package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Filter;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

/**
 * @author mason
 */
public class FilterDto implements Dto<Filter>{
    private static final long serialVersionUID = -4916681777142792973L;

    private int max;
    private int total;

    @Override
    public FilterDto fromModel(Filter model) {
        this.max = model.getMax();
        this.total = model.getTotal();
        return this;
    }

    @Override
    public void toModel(Filter model) {
        model.setMax(max);
        model.setTotal(total);
    }

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
