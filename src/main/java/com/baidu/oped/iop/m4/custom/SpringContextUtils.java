package com.baidu.oped.iop.m4.custom;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author mason
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> tClass) throws BeansException {
        return applicationContext.getBean(name, tClass);
    }

    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }

}
