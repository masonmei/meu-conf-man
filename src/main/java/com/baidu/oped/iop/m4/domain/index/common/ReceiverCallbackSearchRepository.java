package com.baidu.oped.iop.m4.domain.index.common;

import com.baidu.oped.iop.m4.domain.entity.common.ReceiverCallback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mason
 */
public interface ReceiverCallbackSearchRepository extends ElasticsearchRepository<ReceiverCallback, Long> {

    Page<ReceiverCallback> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<ReceiverCallback> findByProductNameAndCallbackUriContains(String productName, String callbackUrl,
            Pageable pageable);

}
