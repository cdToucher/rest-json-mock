package me.codebase.rest.mock.interceptor;

import me.codebase.rest.mock.constant.Patterns;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chendong on 2017/11/30.
 */
@Component
public class RealPathSetter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (!Patterns.interceptorPath.equals(url) && !Patterns.errorPath.equals(url)) {
            request.setAttribute(Patterns.redirectedURI, url);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request
            , HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
