package com.nexthoughts.spring.mvc.demo.servlet3;


import com.nexthoughts.spring.mvc.demo.config.HibernateConfig;
import com.nexthoughts.spring.mvc.demo.config.SpringRootConfig;
import com.nexthoughts.spring.mvc.demo.config.SpringSecurityConfig;
import com.nexthoughts.spring.mvc.demo.config.SpringWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDemoWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class, HibernateConfig.class,SpringSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }
}
