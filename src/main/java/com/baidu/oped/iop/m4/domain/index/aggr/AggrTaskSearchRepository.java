package com.baidu.oped.iop.m4.domain.index.aggr;

import com.baidu.oped.iop.m4.domain.entity.aggr.AggrTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface AggrTaskSearchRepository extends ElasticsearchRepository<AggrTask, Long> {

    Page<AggrTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<AggrTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);


}
