package com.baidu.oped.iop.m4.domain.repository.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface PatrolCollectTaskRepository extends JpaRepository<PatrolCollectTask, Long> {
    Page<PatrolCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndTargetContains(String productName, String target, Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName,
            String target, Pageable pageable);
}
