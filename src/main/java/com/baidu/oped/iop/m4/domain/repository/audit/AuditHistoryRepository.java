package com.baidu.oped.iop.m4.domain.repository.audit;


import com.baidu.oped.iop.m4.domain.entity.audit.AuditHistory;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mason
 */
public interface AuditHistoryRepository extends JpaRepository<AuditHistory, Long> {

}
