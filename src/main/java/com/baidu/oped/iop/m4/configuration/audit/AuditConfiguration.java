package com.baidu.oped.iop.m4.configuration.audit;


import com.baidu.oped.iop.m4.custom.audit.SpringSecurityAuditorAware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author mason
 */
@Configuration
@EnableJpaAuditing(modifyOnCreate = true)
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }
}
