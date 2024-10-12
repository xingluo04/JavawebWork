package com.gzu.filterdemo;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    // 排除列表，包含不需要登录就能访问的路径
    private static final String[] excludedPaths = {"/login", "/register", "/public"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // 获取当前请求的路径
        String requestURI = httpRequest.getRequestURI();

        // 检查请求路径是否在排除列表中
        if (isRequestURIExcluded(httpRequest, requestURI)) {
            // 如果在排除列表中，则允许请求通过
            chain.doFilter(request, response);
        } else {
            // 如果不在排除列表中，检查用户是否已登录
            if (session != null && session.getAttribute("user") != null) {
                // 用户已登录，允许请求继续
                chain.doFilter(request, response);
            } else {
                // 用户未登录，重定向到登录页面
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 检查请求路径是否在排除列表中。
     *
     * @param requestURI 请求路径
     * @return 如果请求路径在排除列表中，则返回 true，否则返回 false。
     */
    private boolean isRequestURIExcluded(HttpServletRequest httpRequest, String requestURI) {
        for (String path : excludedPaths) {
            if (requestURI.startsWith(httpRequest.getContextPath() + path)) {
                return true;
            }
        }
        return false;
    }

}
