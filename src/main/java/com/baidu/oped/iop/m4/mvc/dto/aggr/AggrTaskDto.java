package com.baidu.oped.iop.m4.mvc.dto.aggr;

import com.baidu.oped.iop.m4.domain.entity.aggr.AggrTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of AggrTask.
 *
 * @author mason
 */
public class AggrTaskDto extends AbstractAuditableDto<AggrTask> {

    private static final long serialVersionUID = 7866591676496468310L;

    private Long id;
    private String productName;
    private String appName;
    private String name;
    private String comment;
    private String operation;
    private String tags;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public void toModel(AggrTask aggrTask) {
        aggrTask.setProductName(productName);
        aggrTask.setAppName(appName);
        aggrTask.setName(name);
        aggrTask.setComment(comment);
        aggrTask.setOperation(operation);
        aggrTask.setTags(tags);
    }

    @Override
    public void fromModel(AggrTask aggrTask) {
        initProcess(aggrTask);
        this.id = aggrTask.getId();
        this.productName = aggrTask.getProductName();
        this.appName = aggrTask.getAppName();
        this.name = aggrTask.getName();
        this.tags = aggrTask.getTags();
        this.comment = aggrTask.getComment();
        this.operation = aggrTask.getOperation();
    }
}
