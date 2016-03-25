package com.baidu.oped.iop.m4.domain.repository.aggr;


import com.baidu.oped.iop.m4.domain.entity.aggr.AggrTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface AggrTaskRepository extends JpaRepository<AggrTask, Long> {
    Page<AggrTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<AggrTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

}
