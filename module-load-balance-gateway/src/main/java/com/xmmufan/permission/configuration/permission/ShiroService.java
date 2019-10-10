package com.xmmufan.permission.configuration.permission;

import com.xmmufan.permission.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/24
 * <p>
 * 清空权限缓存并重新从数据库读取
 * </p>
 */
@Service
@Slf4j
public class ShiroService {

    private final ShiroFilterFactoryBean shiroFilterFactoryBean;

    private final PermissionService permissionService;

    @Autowired
    public ShiroService(ShiroFilterFactoryBean shiroFilterFactoryBean, PermissionService permissionService) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
        this.permissionService = permissionService;
    }

    /**
     * @Description: 数据库读取权限配置，封装map
     * @Param: []
     * @return: java.util.Map<java.lang.String       ,       java.lang.String>
     * @Author: Mr.Hergua
     * @Date: 2018/11/24
     */
    private Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new HashMap<>(30);
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/user/subLogin", "anon");
        permissionService.queryAllPermission().forEach(permission ->
                permission.getResources().forEach(resource ->
                        filterChainDefinitionMap.put(resource.getUrl(), "authc, perms[" + resource.getOperation() + "]")));
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * @Description: 清空权限缓存，并且重新重新从数据库读取
     * @Param: []
     * @return: void
     * @Author: Mr.Hergua
     * @Date: 2018/11/24
     */
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {

            try {
                AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
                PathMatchingFilterChainResolver filterChainResolver =
                        (PathMatchingFilterChainResolver) Objects.requireNonNull(shiroFilter).getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                        .getFilterChainManager();

                manager.getFilterChains().clear();
                shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
                shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

                Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
                chains.forEach(manager::createChain);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("Dynamic access permission failed. ");
            }

        }
    }


}
