package com.baidu.oped.iop.m4.domain.index.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface PatrolCollectTaskSearchRepository extends ElasticsearchRepository<PatrolCollectTask, Long> {

    Page<PatrolCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndAppNameAndMethod(String productName, String appName, String method,
            Pageable pageable);

    Page<PatrolCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName, String target,
            Pageable pageable);
}
