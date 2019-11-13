package com.xmmufan.permission.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/21
 *
 *
 * 用于鉴定用户授权的过滤器
 *
 */
class AccessFilter : ZuulFilter() {

    override fun filterType(): String {
        return FilterCode.PRE
    }

    override fun filterOrder(): Int {
        return 0
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun run(): Any? {
        val ctx = RequestContext.getCurrentContext()
        val request = ctx.request

        log.info(String.format("%s request to %s", request.method, request.requestURL.toString()))

        val accessToken = request.getParameter("token")
        if (accessToken == null) {
            log.warn("access token is empty")
            ctx.setSendZuulResponse(false)
            ctx.responseStatusCode = 401
            return null
        } else {

            // 判断token授权

            log.info("")
            return null
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(AccessFilter::class.java)
    }


}
