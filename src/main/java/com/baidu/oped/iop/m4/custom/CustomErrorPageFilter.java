package com.baidu.oped.iop.m4.custom;

import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.ErrorPageFilter;

import java.util.Set;

/**
 * @author mason
 */
public class CustomErrorPageFilter extends ErrorPageFilter {
    @Override
    public Set<ErrorPage> getErrorPages() {
        return super.getErrorPages();
    }
}
