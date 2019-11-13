package com.xmmufan.permission.configuration.permission

import com.xmmufan.permission.repository.PermissionResourceRepository
import org.apache.shiro.cache.CacheManager
import org.apache.shiro.cache.MemoryConstrainedCacheManager
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

import java.util.LinkedHashMap

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 * shiro配置类
 */

@Configuration
open class ShiroConfiguration {


    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = ["lifecycleBeanPostProcessor"])
    open fun lifecycleBeanPostProcessor(): LifecycleBeanPostProcessor = LifecycleBeanPostProcessor()


    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     * 进行数据库读取权限映射信息，设置进拦截列表map中
     */
    @Bean(name = ["shiroFilterFactoryBean"])
    open fun shiroFilterFactoryBean(securityManager: SecurityManager, resourceRepository: PermissionResourceRepository): ShiroFilterFactoryBean {


        val shiroFilterFactoryBean = ShiroFilterFactoryBean()
        shiroFilterFactoryBean.securityManager = securityManager

        val filterChainDefinitionMap = LinkedHashMap<String, String>()
        filterChainDefinitionMap[ALL_PATH] = DISABLED_LOGIN
        //        filterChainDefinitionMap.put(LOG_OUT_PATH, "logout");
        //        filterChainDefinitionMap.put(STATIC_RESOURCE_PATH, DISABLED_LOGIN);
        //        filterChainDefinitionMap.put(SUBMIT_LOGIN_PATH,DISABLED_LOGIN);
        //        filterChainDefinitionMap.put(USER_REGISTER_PATH,DISABLED_LOGIN);
        //        resourceRepository.findAll().forEach(resource -> filterChainDefinitionMap.put(resource.getUrl(), "authc, perms["+resource.getOperation()+"]"));
        //        filterChainDefinitionMap.put(ALL_PATH, REQUIRE_LOGIN);
        //        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.filterChainDefinitionMap = filterChainDefinitionMap

        return shiroFilterFactoryBean
    }


    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean
    open fun userAuthorizingRealm(): CustomizeUserAuthorizingRealm = CustomizeUserAuthorizingRealm()

    /**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    open fun cacheManager(): CacheManager = MemoryConstrainedCacheManager()

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理。
     */
    @Bean
    open fun securityManager(): SecurityManager {
        return DefaultWebSecurityManager().apply {
            this.setRealm(userAuthorizingRealm())
            this.cacheManager = cacheManager()
        }
    }


    @Bean
    @ConditionalOnMissingBean
    open fun defaultAdvisorAutoProxyCreator(): DefaultAdvisorAutoProxyCreator {
        return DefaultAdvisorAutoProxyCreator().apply {
            this.isProxyTargetClass = true
        }
    }

    /**
     * 开启对注解 @RequirePermission 的支持
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    open fun authorizationAttributeSourceAdvisor(): AuthorizationAttributeSourceAdvisor {
        return AuthorizationAttributeSourceAdvisor().apply {
            this.securityManager = securityManager()
        }
    }

    companion object {

        val LOG_OUT_PATH = "/logout"
        val STATIC_RESOURCE_PATH = "/static/**"
        val SUBMIT_LOGIN_PATH = "/subLogin"
        val ALL_PATH = "/**"
        val USER_REGISTER_PATH = "/user/addUser"


        val DISABLED_LOGIN = "anon"
        val REQUIRE_LOGIN = "authc"
    }

}
