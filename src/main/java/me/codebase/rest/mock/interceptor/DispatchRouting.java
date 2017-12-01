package me.codebase.rest.mock.interceptor;

import me.codebase.rest.mock.config.ConfigFactory;
import me.codebase.rest.mock.constant.Patterns;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by chendong on 2017/4/20.
 * <p>
 * 静态资源 获取配置
 * <p>
 * WebMvcConfigurerAdapter 中存在 一些好用 adepter method
 */
@Component
public class DispatchRouting extends WebMvcConfigurerAdapter {

    @Resource
    private RealPathSetter holder;

    @Resource
    private ConfigFactory configFactory;

    /**
     * 配置静态访问资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Patterns.resourcePathPatterns)
                .addResourceLocations(Patterns.resourceLocations);
        super.addResourceHandlers(registry);
    }

    /**
     * 重定向
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(configFactory.getFilter()).setViewName(Patterns.interceptorPath);
        super.addViewControllers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(holder);
        super.addInterceptors(registry);
    }

}