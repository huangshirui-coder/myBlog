package com.example.demo.auth.filter;

import com.example.demo.entity.BlackIp;
import com.example.demo.service.BlackIpService;
import com.example.demo.utils.SpringBeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IPFilter implements Filter {
    @Autowired
    BlackIpService blackIpService;

    List<String> blackIpList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        blackIpService = ctx.getBean(BlackIpService.class);
        if (blackIpService != null) {
            List<BlackIp> list = blackIpService.selectBlackIpList();
            blackIpList = list.stream().map(BlackIp::getBlackIp).collect(Collectors.toList());
        }
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = httpRequest.getRemoteAddr();
        if (blackIpList.contains(ip)) {
            // 黑名单IP，拒绝访问
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "IP is in blacklist");
        } else {
            // 非黑白名单IP，根据业务需求处理（例如：允许或拒绝）
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
