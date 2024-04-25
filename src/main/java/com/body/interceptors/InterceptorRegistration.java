package com.body.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InterceptorRegistration implements HibernatePropertiesCustomizer {
    private final AuditLogInterceptor myInterceptor;

    @Autowired
    public InterceptorRegistration(AuditLogInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", myInterceptor);
    }
}