package com.baidu.oped.iop.m4.domain.index.common;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface ReceiverGroupSearchRepository extends ElasticsearchRepository<ReceiverGroup, Long> {

    Page<ReceiverGroup> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

}
