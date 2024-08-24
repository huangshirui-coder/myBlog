package com.example.demo.config;

import com.example.demo.auth.filter.IPFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<IPFilter> ipFilterRegistration() {
        FilterRegistrationBean<IPFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IPFilter());
        registrationBean.addUrlPatterns("/*"); // 设置过滤器拦截的路径
        registrationBean.setName("ipFilter");
        registrationBean.setOrder(1); // 设置过滤器的顺序
        return registrationBean;
    }
}
