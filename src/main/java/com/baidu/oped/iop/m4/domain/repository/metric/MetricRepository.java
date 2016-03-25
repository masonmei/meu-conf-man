package com.baidu.oped.iop.m4.domain.repository.metric;

import com.baidu.oped.iop.m4.domain.entity.metric.Metric;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
