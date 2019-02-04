package com.driverexam.config;

import com.driverexam.interceptor.StuLoginInterceptor;
import com.driverexam.interceptor.TeaLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    StuLoginInterceptor stuLoginInterceptor;
    @Autowired
    TeaLoginInterceptor teaLoginInterceptor;

    final String[] stuLoginIntercept = {
            "/student/**"
    };
    final String[] teaLoginIntercept = {
            "/teacher/**"
    };
//    final String[] notStuLoginInterceptPaths = {
//            "/student/login"
//    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stuLoginInterceptor)
                .addPathPatterns(stuLoginIntercept);
        registry.addInterceptor(teaLoginInterceptor)
                .addPathPatterns(teaLoginIntercept);
//                .excludePathPatterns(notStuLoginInterceptPaths);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/");
        resolver.setSuffix(".html");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
