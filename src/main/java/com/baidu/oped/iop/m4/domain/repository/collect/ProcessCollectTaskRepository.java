package com.baidu.oped.iop.m4.domain.repository.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface ProcessCollectTaskRepository extends JpaRepository<ProcessCollectTask, Long> {

    Page<ProcessCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<ProcessCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<ProcessCollectTask> findByProductNameAndTargetContains(String productName, String target, Pageable pageable);

    Page<ProcessCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName,
            String target, Pageable pageable);
}
