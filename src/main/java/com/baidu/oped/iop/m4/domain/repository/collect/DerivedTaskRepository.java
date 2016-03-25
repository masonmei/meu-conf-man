package com.baidu.oped.iop.m4.domain.repository.collect;


import com.baidu.oped.iop.m4.domain.entity.collect.DerivedTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface DerivedTaskRepository extends JpaRepository<DerivedTask, Long> {
    Page<DerivedTask> findByProductNameAndNameContains(String productName, String name, Pageable pageable);

    Page<DerivedTask> findByProductNameAndFormulaContains(String productName, String name, Pageable pageable);

    Page<DerivedTask> findByProductNameAndAppNameAndNameContains(String productName, String appName, String name,
            Pageable pageable);

    Page<DerivedTask> findByProductNameAndAppNameAndFormulaContains(String productName, String appName, String formula,
            Pageable pageable);
}
