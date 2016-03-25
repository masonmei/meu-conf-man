package com.baidu.oped.iop.m4.domain.index.alert;

import com.baidu.oped.iop.m4.domain.entity.alert.Policy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface PolicySearchRepository extends ElasticsearchRepository<Policy, Long> {

    Page<Policy> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<Policy> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);
}
