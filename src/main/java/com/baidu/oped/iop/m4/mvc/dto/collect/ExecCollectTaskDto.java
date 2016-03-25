package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;

import java.util.Date;

/**
 * Data translate object of ExecCollectTask.
 *
 * @author mason
 */
public class ExecCollectTaskDto extends AbstractAuditableDto<ExecCollectTask> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

    @Override
    public void fromModel(ExecCollectTask execCollectTask) {
        initProcess(execCollectTask);
        this.id = execCollectTask.getId();
        this.productName = execCollectTask.getProductName();
        this.appName = execCollectTask.getAppName();
        this.name = execCollectTask.getName();
    }

    @Override
    public void toModel(ExecCollectTask execCollectTask) {
        execCollectTask.setProductName(productName);
        execCollectTask.setAppName(appName);
        execCollectTask.setName(name);

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
