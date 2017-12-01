package me.codebase.rest.mock.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by chendong on 2016/12/28.
 * <p>
 * 简单跨域设置用于rest微服务
 */
@Component
public class SimpleCORSFilter implements Filter {

    private static final String ORIGIN = "Origin";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static final String CONTENT_TYPE = "Content-Type";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = getHeaderOrigin((HttpServletRequest) servletRequest);
        response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, UPDATE, OPTIONS, HEAD, PROPFIND");
        response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Overwrite, Destination, Content-Type, Depth, User-Agent, X-File-Size, X-Requested-With, If-Modified-Since, X-File-Name, Cache-Control");
        response.addHeader(ACCESS_CONTROL_MAX_AGE, "1728000");
        response.addHeader(CONTENT_TYPE, "application/json;charset=UTF-8");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }

    private String getHeaderOrigin(HttpServletRequest request) throws UnsupportedEncodingException, MalformedURLException {
        boolean hasOrigin = false;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            if (ORIGIN.equals(headerNames.nextElement())) {
                hasOrigin = true;
                break;
            }
        }
        if (hasOrigin) {
            String origin = request.getHeader(ORIGIN);
            return origin == null ? "null" : origin;
        } else {
            return new URL(request.getRequestURL().toString()).getHost();
        }
    }
}
