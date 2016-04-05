package com.baidu.oped.iop.m4.configuration.audit;


import com.baidu.oped.iop.m4.custom.audit.SpringSecurityAuditorAware;
import com.baidu.oped.iop.m4.service.RecordAuditingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author mason
 */
@Configuration
@EnableJpaAuditing(modifyOnCreate = true)
public class AuditConfiguration {

    @Autowired
    private RecordAuditingService recordAuditingService;

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

//    @Bean
//    public RecordAuditingEntityListener recordAuditingEntityListener(){
//        RecordAuditingEntityListener entityListener = new RecordAuditingEntityListener();
//        entityListener.setRecordAuditingService(recordAuditingService);
//        return entityListener;
//    }
}
