package com.cx.os.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import javax.servlet.annotation.WebFilter;

/**
 * 拦截器，拦截所有的页面，使用sitemesh的模板
 */
@WebFilter(filterName = "sitemesh",urlPatterns = {"/*"})
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter{
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        //使用decorator.jsp去装配所有的页面
        builder.addDecoratorPath("/*","/decorator.jsp");
    }
}
