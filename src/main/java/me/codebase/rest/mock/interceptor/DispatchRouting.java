package me.codebase.rest.mock.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chendong on 2017/4/20.
 * <p>
 * 静态资源 获取配置
 * <p>
 * WebMvcConfigurerAdapter 中存在 一些好用 adepter method
 * <p>
 * if not use springboot configuration use this instead
 */
//@EnableWebMvc
public class DispatchRouting {

    @Bean
    public RealPathSetter getRealPathSetter() {
        return new RealPathSetter();
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setInterceptors(getRealPathSetter());
        return mapping;
    }

    @Bean
    public ParameterizableViewController getParameterizableViewController() {
        ParameterizableViewController controller = new ParameterizableViewController();
        controller.setViewName("/get");
        controller.setSupportedMethods(HttpMethod.GET.name(), HttpMethod.POST.name());
        return controller;
    }

    @Bean
    public UrlBasedViewResolver defaultViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setCache(false);
        resolver.setViewClass(InternalResourceView.class);
        return resolver;
    }

    @Bean
    public SimpleUrlHandlerMapping getSimpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Map<String, ParameterizableViewController> map = new HashMap<>();
        map.put("/v1/**", getParameterizableViewController());
        mapping.setUrlMap(map);
        return mapping;
    }


}