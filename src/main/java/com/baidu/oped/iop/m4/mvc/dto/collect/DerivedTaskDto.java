package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of DerivedTask.
 *
 * @author mason
 */
public class DerivedTaskDto extends AbstractAppLayerAuditableDto<DerivedTask> {

    private static final long serialVersionUID = -7113900358525560919L;
    private String formula;
    private String comment;

    @Override
    public DerivedTaskDto fromModel(DerivedTask model) {
        super.initProcess(model);
        this.comment = model.getComment();
        this.formula = model.getFormula();
        return this;
    }

    @Override
    public void toModel(DerivedTask model) {
        model.setName(getName());
        model.setFormula(formula);
        model.setComment(comment);
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
