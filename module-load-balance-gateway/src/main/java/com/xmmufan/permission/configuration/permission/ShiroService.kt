package com.xmmufan.permission.configuration.permission

import com.xmmufan.permission.service.PermissionService
import lombok.extern.slf4j.Slf4j
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver
import org.apache.shiro.web.servlet.AbstractShiroFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.HashMap
import java.util.Objects
import java.util.function.BiConsumer

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/24
 *
 *
 * 清空权限缓存并重新从数据库读取
 *
 */
@Service
@Slf4j
class ShiroService @Autowired
constructor(private val shiroFilterFactoryBean: ShiroFilterFactoryBean, private val permissionService: PermissionService) {

    /**
     * @Description: 数据库读取权限配置，封装map
     * @Param: []
     * @return: java.util.Map<java.lang.String , java.lang.String>
     * @Author: Mr.Hergua
     * @Date: 2018/11/24
    </java.lang.String> */
    private fun loadFilterChainDefinitions(): Map<String, String> {
        val filterChainDefinitionMap = HashMap<String, String>(30)
        filterChainDefinitionMap["/logout"] = "logout"
        filterChainDefinitionMap["/user/subLogin"] = "anon"
//        permissionService.queryAllPermission().forEach { permission -> permission.resources?.forEach { resource -> filterChainDefinitionMap[resource.url] = "authc, perms[" + resource.operation + "]" } }
        filterChainDefinitionMap["/**"] = "authc"
        return filterChainDefinitionMap
    }

    /**
     * @Description: 清空权限缓存，并且重新重新从数据库读取
     * @Param: []
     * @return: void
     * @Author: Mr.Hergua
     * @Date: 2018/11/24
     */
    fun updatePermission() {
        synchronized(shiroFilterFactoryBean) {

            try {
                val shiroFilter = shiroFilterFactoryBean.getObject() as AbstractShiroFilter
                val filterChainResolver = Objects.requireNonNull(shiroFilter).filterChainResolver as PathMatchingFilterChainResolver
                val manager = filterChainResolver
                        .filterChainManager as DefaultFilterChainManager

                manager.filterChains.clear()
                shiroFilterFactoryBean.filterChainDefinitionMap.clear()
                shiroFilterFactoryBean.filterChainDefinitionMap = loadFilterChainDefinitions()

                val chains = shiroFilterFactoryBean.filterChainDefinitionMap
                chains.forEach(BiConsumer<String, String> { chainName, chainDefinition -> manager.createChain(chainName, chainDefinition) })
            } catch (e: Exception) {
                throw RuntimeException("Dynamic access permission failed. ")
            }

        }
    }


}
