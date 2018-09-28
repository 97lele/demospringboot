package com.csdn.demospringboot.config.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class MvcConfiguration implements WebMvcConfigurer,ApplicationContextAware  {
    // Spring容器
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }
    /*
    文件上传解析
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        // 1024 * 1024 * 20 = 20M
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }
    //试图解析
    @Bean(name = "viewResolver")
    public ViewResolver createViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 设置Spring 容器
        viewResolver.setApplicationContext(this.applicationContext);
        // 取消缓存
        viewResolver.setCache(false);
        // 设置解析的前缀
        viewResolver.setPrefix("/WEB-INF/html/");
        // 设置试图解析的后缀
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

  /*
   @Autowired
   拦截器
  @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(拦截器).addPathPatterns("路径");
    }*/

}
