package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of ProcessCollectTask.
 *
 * @author mason
 */
public class ProcessCollectTaskDto extends AbstractAuditableDto<ProcessCollectTask> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

    @Override
    public void fromModel(ProcessCollectTask processCollectTask) {
        initProcess(processCollectTask);
        this.id = processCollectTask.getId();
        this.productName = processCollectTask.getProductName();
        this.appName = processCollectTask.getAppName();
        this.name = processCollectTask.getName();
    }

    @Override
    public void toModel(ProcessCollectTask processCollectTask) {
        processCollectTask.setProductName(productName);
        processCollectTask.setAppName(appName);
        processCollectTask.setName(name);

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
