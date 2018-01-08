package com.cx.os.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * springmvc的配置
 * 注解解释为：
 * 配置类
 * 允许使用springmvc,启用之后会开启一些默认配置，比如ViewResolver
 * 扫描包为com.cx.os
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.cx.os")
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
    @Bean
    public InternalResourceViewResolver viewResolver(){
        //viewresolver来渲染页面
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //指定渲染页面路径
        viewResolver.setPrefix("/WEB-INF/views/");
        //指定页面格式
        viewResolver.setSuffix(".jsp");
        //使用jstl标准进行页面的渲染
        viewResolver.setViewClass(JstlView.class);
        return  viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("/WEB-INF/statics/");
    }
}
