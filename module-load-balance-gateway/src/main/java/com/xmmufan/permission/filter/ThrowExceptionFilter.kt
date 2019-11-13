package com.xmmufan.permission.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletResponse


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/22
 *
 *
 *
 *
 */
class ThrowExceptionFilter : ZuulFilter() {

    private val logger = LoggerFactory.getLogger(this.javaClass)


    override fun filterType(): String {
        return FilterCode.POST
    }

    override fun filterOrder(): Int {
        return 0
    }

    override fun shouldFilter(): Boolean {
        return false
    }

    override fun run(): Any? {
        logger.info("This is a pre filter, it will throw a RuntimeException")
        val ctx = RequestContext.getCurrentContext()
        try {
            //            doSomething();
        } catch (e: Exception) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ctx.set("error.exception", e)
        }

        return null
    }
}
