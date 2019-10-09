package com.xmmufan.permission.configuration.premission;

import com.xmmufan.permission.domain.rbac.PermissionResource;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.service.UserAccountService;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 * <p>
 *      该类用于设置自定义的用户授权realm
 *      使用JPA获取并对比用户的用户名和密码
 * </p>
 */
@Slf4j
public class CustomizeUserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserAccountService accountService;

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new NullPointerException("PrincipalCollection method argument cannot be null.");
        }
        String username = (String) principals.getPrimaryPrincipal();
        Set<String> userGroup = new HashSet<>(8);
        Set<String> permissionResources = new HashSet<>(30);
        try {
            UserAccount targetAccount = accountService.findByUsername(username);
            User targetUser = userService.queryByAccount(targetAccount);
            userGroup.add(targetUser.getUserGroup().getId());
            targetUser.getUserGroup().getBasicPermissions().forEach(permission -> {
                permission.getResources().forEach(resource -> permissionResources.add(resource.getUrl()));
            });
            targetUser.getCustomizePermission().forEach(permission -> {
                permission.getResources().forEach(resource -> permissionResources.add(resource.getUrl()));
            });
        } catch (NullPointerException e) {
            final String message = "No account found for user '" + username +"'";
            log.error(message, e);
            throw new UnknownAccountException(message, e);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(userGroup);
        info.setStringPermissions(permissionResources);
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }
        UserAccount account = accountService.findByUsername(username);
        User user = userService.queryByAccount(account);
        if (!user.getAccount().isEnable()){
            throw new LockedAccountException("user: "+username+" this account has been locked. ");
        }
        String password = account.getPassword();
        String salt = account.getSalt();

        return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), this.getName());
    }
    /**
     * @Description
     * @Param [credentialsMatcher]
     * @Author Hergua
     * @Date  2019/2/20
     * @return void
     **/
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(8);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
