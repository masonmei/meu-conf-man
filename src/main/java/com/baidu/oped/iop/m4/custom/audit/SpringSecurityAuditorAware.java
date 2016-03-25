package com.baidu.oped.iop.m4.custom.audit;


import static com.baidu.oped.sia.boot.utils.Constrains.SYSTEM;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mason
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            return auth.getName();
        }

        return SYSTEM;
    }
}
