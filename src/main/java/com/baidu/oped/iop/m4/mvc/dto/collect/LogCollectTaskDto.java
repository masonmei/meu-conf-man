package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;
import com.baidu.oped.iop.m4.mvc.dto.common.Dto;

import java.util.Date;

/**
 * Data translate object of LogCollectTask.
 *
 * @author mason
 */
public class LogCollectTaskDto extends AbstractAuditableDto<LogCollectTask> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

    @Override
    public void fromModel(LogCollectTask logCollectTask) {
        initProcess(logCollectTask);
        this.id = logCollectTask.getId();
        this.productName = logCollectTask.getProductName();
        this.appName = logCollectTask.getAppName();
        this.name = logCollectTask.getName();
    }

    @Override
    public void toModel(LogCollectTask logCollectTask) {
        logCollectTask.setProductName(productName);
        logCollectTask.setAppName(appName);
        logCollectTask.setName(name);

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
