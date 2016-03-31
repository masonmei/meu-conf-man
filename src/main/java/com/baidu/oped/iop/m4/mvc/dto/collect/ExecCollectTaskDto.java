package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTaskMethod;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of ExecCollectTask.
 *
 * @author mason
 */
public class ExecCollectTaskDto extends AbstractAppLayerAuditableDto<ExecCollectTask> {

    private static final long serialVersionUID = 6090311841792778215L;

    private ExecCollectTaskMethod method;
    private int cycle;
    private String target;
    private String comment;

    @Override
    public ExecCollectTaskDto fromModel(ExecCollectTask model) {
        super.initProcess(model);
        this.cycle = model.getCycle();
        this.target = model.getTarget();
        this.comment = model.getComment();
        this.method = model.getMethod();
        return this;
    }

    @Override
    public void toModel(ExecCollectTask model) {
        model.setName(getName());
        model.setMethod(method);
        model.setCycle(cycle);
        model.setTarget(target);
        model.setComment(comment);
    }

    public ExecCollectTaskMethod getMethod() {
        return method;
    }

    public void setMethod(ExecCollectTaskMethod method) {
        this.method = method;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
