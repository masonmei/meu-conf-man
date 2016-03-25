package com.baidu.oped.iop.m4.domain.index.metric;

import com.baidu.oped.iop.m4.domain.entity.metric.Metric;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface MetricSearchRepository extends ElasticsearchRepository<Metric, Long> {

    Page<Metric> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<Metric> findByProductNameAndAliasContains(String productName, String alias, Pageable pageable);
}
