package com.hyundaimotors.hmb.cdppapp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hyundaimotors.hmb.cdppapp.common.filter.DuplicateRequestFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DuplicateRequestFilter> loggingFilter() {
        FilterRegistrationBean<DuplicateRequestFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new DuplicateRequestFilter());
        registrationBean.addUrlPatterns("/api/v1/HMBInboundContactInterfaceWorkflow"); // 적용할 URL 패턴 지정
        registrationBean.setOrder(1); // 필터의 순서 지정

        return registrationBean;
    }
}