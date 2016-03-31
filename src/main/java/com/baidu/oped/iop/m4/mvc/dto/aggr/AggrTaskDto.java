package com.baidu.oped.iop.m4.mvc.dto.aggr;

import com.baidu.oped.iop.m4.domain.entity.aggr.AggrTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of AggrTask.
 *
 * @author mason
 */
public class AggrTaskDto extends AbstractAppLayerAuditableDto<AggrTask> {

    private static final long serialVersionUID = 7866591676496468310L;

    private String comment;
    private String operation;
    private String tags;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public void toModel(AggrTask aggrTask) {
        aggrTask.setName(getName());
        aggrTask.setComment(comment);
        aggrTask.setOperation(operation);
        aggrTask.setTags(tags);
    }

    @Override
    public AggrTaskDto fromModel(AggrTask aggrTask) {
        super.initProcess(aggrTask);
        this.tags = aggrTask.getTags();
        this.comment = aggrTask.getComment();
        this.operation = aggrTask.getOperation();
        return this;
    }

}
