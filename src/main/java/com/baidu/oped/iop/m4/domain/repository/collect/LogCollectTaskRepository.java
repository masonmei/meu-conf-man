package com.baidu.oped.iop.m4.domain.repository.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface LogCollectTaskRepository extends JpaRepository<LogCollectTask, Long> {

    Page<LogCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<LogCollectTask> findByProductNameAndTargetContains(String productName, String target, Pageable pageable);

    Page<LogCollectTask> findByProductNameAndParamsContains(String productName, String params, Pageable pageable);

    Page<LogCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<LogCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName, String target,
            Pageable pageable);

    Page<LogCollectTask> findByProductNameAndAppNameAndParamsContains(String productName, String appName, String params,
            Pageable pageable);
}
