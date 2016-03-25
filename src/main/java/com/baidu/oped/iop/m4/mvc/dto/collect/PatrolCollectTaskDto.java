package com.baidu.oped.iop.m4.mvc.dto.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.mvc.dto.common.AbstractAuditableDto;

/**
 * Data translate object of PatrolCollectTask.
 *
 * @author mason
 */
public class PatrolCollectTaskDto extends AbstractAuditableDto<PatrolCollectTask> {

    private Long id;
    private String productName;
    private String appName;
    private String name;

    @Override
    public void fromModel(PatrolCollectTask patrolCollectTask) {
        initProcess(patrolCollectTask);
        this.id = patrolCollectTask.getId();
        this.productName = patrolCollectTask.getProductName();
        this.appName = patrolCollectTask.getAppName();
        this.name = patrolCollectTask.getName();
    }

    @Override
    public void toModel(PatrolCollectTask patrolCollectTask) {
        patrolCollectTask.setProductName(productName);
        patrolCollectTask.setAppName(appName);
        patrolCollectTask.setName(name);

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
