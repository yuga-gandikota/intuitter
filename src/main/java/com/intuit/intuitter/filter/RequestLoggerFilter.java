package com.intuit.intuitter.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filter to log incoming requests (url, query string etc). Can be done using RequestLoggingFilterConfig and Interceptors.
 * 
 * It also adds current JSESSIONID to log4j MDC so that all further logging that happens for 
 * this request will have the JSESSION in the log. This helps in debugging issues. 
 *
 * @author Yuga Gandikota
 */
@Component
public class RequestLoggerFilter extends GenericFilterBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggerFilter.class);
	private static final String SESSION_ID_KEY = "SESSION_ID";
	
	@Value("#{'${request.logger.filter.enabled}'}")
	boolean enabled = true;

	@Value("#{'${request.logger.filter.log.request.body}'}")
	boolean logRequestBody = false;
	
	@Value("#{'${request.logger.filter.log.request.cookies}'}")
	boolean logCookies = false;
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		
		//check if this filter is enabled or now.
		//if not, pass control to filter chain.
		if (enabled) {
			try {
				HttpServletRequest httpRequest = ((HttpServletRequest)request);
				HttpSession session = httpRequest.getSession();
				if (request instanceof HttpServletRequest) {
					MDC.put(SESSION_ID_KEY, session.getId());
				}
								
				if (LOGGER.isInfoEnabled()) {
					String payload = "-";
					if (logRequestBody) {
						payload = ""; //extract payload.
					}
					
					String cookies = "-";
					if (logCookies) {
						cookies = httpRequest.getHeader("Cookie");
					}
					String uri = httpRequest.getRequestURI();
					String method = httpRequest.getMethod();
					String queryString = httpRequest.getQueryString();
					
					LOGGER.info("request URI:{}, method:{}, queryString:{}, cookies:{}, payload:{}", 
							uri, method, queryString, cookies, payload);
				}
				
				chain.doFilter(request, response);		
			}
			finally {
				MDC.remove(SESSION_ID_KEY);
			}
		}
		else {			
			chain.doFilter(request, response);		
		}
	}
}
