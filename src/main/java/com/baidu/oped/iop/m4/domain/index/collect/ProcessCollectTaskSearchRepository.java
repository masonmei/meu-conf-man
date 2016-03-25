package com.baidu.oped.iop.m4.domain.index.collect;

import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface ProcessCollectTaskSearchRepository extends ElasticsearchRepository<ProcessCollectTask, Long> {

    Page<ProcessCollectTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<ProcessCollectTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<ProcessCollectTask> findByProductNameAndAppNameAndTargetContains(String productName, String appName,
            String target, Pageable pageable);

}
