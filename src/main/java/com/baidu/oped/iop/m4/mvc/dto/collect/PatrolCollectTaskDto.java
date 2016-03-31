package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTaskMethod;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAppLayerAuditableDto;

/**
 * Data translate object of PatrolCollectTask.
 *
 * @author mason
 */
public class PatrolCollectTaskDto extends AbstractAppLayerAuditableDto<PatrolCollectTask> {

    private static final long serialVersionUID = 3254881422730323993L;

    private int cycle;
    private String target;
    private PatrolCollectTaskMethod method;
    private String comment;
    private int port;
    private int timeout;
    private String configParam;

    @Override
    public PatrolCollectTaskDto fromModel(PatrolCollectTask model) {
        super.initProcess(model);
        this.cycle = model.getCycle();
        this.target = model.getTarget();
        this.method = model.getMethod();
        this.comment = model.getComment();
        this.port = model.getPort();
        this.timeout = model.getTimeout();
        this.configParam = model.getConfigParam();
        return this;
    }

    @Override
    public void toModel(PatrolCollectTask model) {
        model.setName(getName());
        model.setCycle(cycle);
        model.setTarget(target);
        model.setMethod(method);
        model.setComment(comment);
        model.setPort(port);
        model.setTimeout(timeout);
        model.setConfigParam(configParam);
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

    public PatrolCollectTaskMethod getMethod() {
        return method;
    }

    public void setMethod(PatrolCollectTaskMethod method) {
        this.method = method;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getConfigParam() {
        return configParam;
    }

    public void setConfigParam(String configParam) {
        this.configParam = configParam;
    }

}
