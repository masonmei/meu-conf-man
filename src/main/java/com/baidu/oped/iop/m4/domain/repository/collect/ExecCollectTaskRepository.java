package com.baidu.oped.iop.m4.domain.repository.collect;


import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface ExecCollectTaskRepository extends JpaRepository<ExecCollectTask, Long> {

    Page<ExecCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndTargetContains(String productName, String target, Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName,
            String target, Pageable pageable);
}
