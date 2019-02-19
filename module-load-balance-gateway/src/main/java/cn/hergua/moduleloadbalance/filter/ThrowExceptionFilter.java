package cn.hergua.moduleloadbalance.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;

import javax.servlet.http.HttpServletResponse;


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/22
 * <p>
 *
 * </p>
 */
public class ThrowExceptionFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public String filterType() {
        return FilterCode.POST;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        logger.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
//            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }
        return null;
    }
}
