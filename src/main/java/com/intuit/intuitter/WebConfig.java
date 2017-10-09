package com.intuit.intuitter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.intuitter.filter.RequestLoggerFilter;

@Configuration
public class WebConfig {
	/**
	 * Registers request logging filter (custom filter). Can be done using RequestLoggingFilterConfig also.
	 */
    @Bean
    public FilterRegistrationBean requestLoggingFilterRegistrationBean() {
    	
        FilterRegistrationBean requestLoggerBean = new FilterRegistrationBean();
        requestLoggerBean.setFilter(new RequestLoggerFilter());
        requestLoggerBean.addUrlPatterns("/*");
        return requestLoggerBean;
    }
}