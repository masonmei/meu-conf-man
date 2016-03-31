package com.baidu.oped.iop.m4.domain.repository.metric;

import com.baidu.oped.iop.m4.domain.entity.metric.Metric;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author mason
 */
public interface MetricRepository extends JpaRepository<Metric, Long> {
    Optional<Metric> findOneByProductNameAndAppNameAndName(String productName, String appName, String name);
}
