package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of ProcessCollectTask.
 *
 * @author mason
 */
public class ProcessCollectTaskDto extends AbstractAppLayerAuditableDto<ProcessCollectTask> {
    private static final long serialVersionUID = -7515288853580045091L;

    private String comment;
    private String method;
    private int cycle;
    private String target;

    @Override
    public ProcessCollectTaskDto fromModel(ProcessCollectTask model) {
        super.initProcess(model);
        this.comment = model.getComment();
        this.method = model.getMethod();
        this.cycle = model.getCycle();
        this.target = model.getTarget();
        return this;
    }

    @Override
    public void toModel(ProcessCollectTask model) {
        model.setName(getName());
        model.setComment(comment);
        model.setTarget(target);
        model.setCycle(cycle);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
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

}
