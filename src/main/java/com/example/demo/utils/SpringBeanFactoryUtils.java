package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * bean未加载前获取bean
 */
@Component
public class SpringBeanFactoryUtils implements ApplicationContextAware {
    private static ApplicationContext context = null;

    //获取bean
    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanFactoryUtils.context == null) {
            SpringBeanFactoryUtils.context = applicationContext;
        }
    }
}
