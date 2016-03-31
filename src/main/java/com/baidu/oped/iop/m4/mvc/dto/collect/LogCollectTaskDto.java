package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.LogTaskParam;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of LogCollectTask.
 *
 * @author mason
 */
public class LogCollectTaskDto extends AbstractAppLayerAuditableDto<LogCollectTask> {

    private static final long serialVersionUID = -7048409487747640685L;

    private String method;
    private String comment;
    private String target;
    private LogTaskParamDto params;
    private int cycle;

    @Override
    public LogCollectTaskDto fromModel(LogCollectTask model) {
        super.initProcess(model);
        this.method = model.getMethod();
        this.comment = model.getComment();
        this.target = model.getTarget();
        this.params = new LogTaskParamDto().fromModel(model.getLogTaskParam());
        this.cycle = model.getCycle();
        return this;
    }

    @Override
    public void toModel(LogCollectTask model) {
        model.setName(getName());
        model.setMethod(method);
        model.setComment(comment);
        model.setTarget(target);
        model.setCycle(cycle);
        LogTaskParam logTaskParam = new LogTaskParam();
        params.toModel(logTaskParam);
        model.setLogTaskParam(logTaskParam);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public LogTaskParamDto getParams() {
        return params;
    }

    public void setParams(LogTaskParamDto params) {
        this.params = params;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

}
