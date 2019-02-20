package com.xmmufan.permission.configuration.premission;

import com.xmmufan.permission.constant.permission.AccountState;
import com.xmmufan.permission.domain.permission.User;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 * <p>
 * 该类用于设置自定义的用户授权realm
 * 使用JPA获取并对比用户的用户名和密码
 * </p>
 */
@Slf4j
public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String username = (String) principals.getPrimaryPrincipal();

        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        try {
            User targetUser = userService.findByUsername(username);
            targetUser.getRoleList().forEach(sysRole -> {
                roles.add(sysRole.getRole());
                sysRole.getPermissions().forEach(sysPermission -> permissions.add(sysPermission.getPermission()));
            });
        } catch (NullPointerException e) {
            final String message = "No account found for user '" + username +"'";
            log.error(message, e);
            throw new UnknownAccountException(message, e);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permissions);
        log.info(Arrays.toString(permissions.toArray()));
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }
        User user = userService.findByUsername(username);
        if (user.getState() == AccountState.LOCKED){
            throw new LockedAccountException("user: "+username+" this account has been locked. ");
        }
        String password = user.getPassword();
        String salt = user.getSalt();

        return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), this.getName());
    }


    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(8);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
