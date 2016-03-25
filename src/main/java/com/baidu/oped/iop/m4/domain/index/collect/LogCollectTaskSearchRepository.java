package com.baidu.oped.iop.m4.domain.index.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.LogCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface LogCollectTaskSearchRepository extends ElasticsearchRepository<LogCollectTask, Long> {

    Page<LogCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<LogCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<LogCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName, String target,
            Pageable pageable);
}
