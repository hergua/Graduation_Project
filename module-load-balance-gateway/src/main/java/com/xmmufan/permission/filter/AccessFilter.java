package com.xmmufan.permission.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/21
 * <p>
 *     用于鉴定用户授权的过滤器
 * </p>
 */
public class AccessFilter extends ZuulFilter {


    private final static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return FilterCode.PRE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }else{

            // 判断token授权

            log.info("");
            return null;
        }
    }



}
