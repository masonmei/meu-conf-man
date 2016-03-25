package com.baidu.oped.iop.m4.domain.index.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ExecCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface ExecCollectTaskSearchRepository extends ElasticsearchRepository<ExecCollectTask, Long> {

    Page<ExecCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndAppNameAndMethod(String productName, String appName, String method,
            Pageable pageable);

    Page<ExecCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName,
            String target, Pageable pageable);
}
