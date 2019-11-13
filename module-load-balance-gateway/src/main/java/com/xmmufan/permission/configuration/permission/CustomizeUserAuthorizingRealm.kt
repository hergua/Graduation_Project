package com.xmmufan.permission.configuration.permission

import com.xmmufan.permission.domain.rbac.UserAccount
import com.xmmufan.permission.service.UserAccountService
import com.xmmufan.permission.service.UserService
import org.apache.shiro.authc.*
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.util.ByteSource
import org.springframework.beans.factory.annotation.Autowired

import java.util.HashSet
import java.util.Objects

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 *
 *
 * 该类用于设置自定义的用户授权realm
 * 使用JPA获取并对比用户的用户名和密码
 *
 */
class CustomizeUserAuthorizingRealm : AuthorizingRealm() {

    @Autowired
    private val accountService: UserAccountService? = null

    @Autowired
    private val userService: UserService? = null

    override fun doGetAuthorizationInfo(principals: PrincipalCollection?): AuthorizationInfo {
        if (principals == null) {
            throw NullPointerException("PrincipalCollection method argument cannot be null.")
        }
        val username = principals.primaryPrincipal as String
        val userGroup = HashSet<String>(8)
        val permissionResources = HashSet<String>(30)
        try {
            val targetAccount = accountService!!.findByUsername(username)!!
            val targetUser = userService!!.queryByAccount(targetAccount)
//            userGroup.add(targetUser.userGroup.id)

            targetUser.userGroup?.basicPermissions?.forEach { permission -> permission.resources?.forEach { resource -> resource.url?.let { permissionResources.add(it) } } }

            targetUser.customizePermission?.forEach { permission -> permission.resources?.forEach { resource -> resource.url?.let { permissionResources.add(it) } } }

        } catch (e: NullPointerException) {
            val message = "No account found for user '$username'"
            throw UnknownAccountException(message, e)
        }

        val info = SimpleAuthorizationInfo(userGroup)
        info.stringPermissions = permissionResources
        return info
    }


    @Throws(AuthenticationException::class)
    override fun doGetAuthenticationInfo(token: AuthenticationToken): AuthenticationInfo {
        val upToken = token as UsernamePasswordToken
        val username = upToken.username ?: throw AccountException("Null username are not allowed by this realm.")
        val account = accountService!!.findByUsername(username)!!
        val user = userService!!.queryByAccount(account)
        if (!user.account!!.isEnable) {
            throw LockedAccountException("user: $username this account has been locked. ")
        }
        val password = account.password
        val salt = account.salt!!

        return SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), this.name)
    }

    /**
     * @Description
     * @Param [credentialsMatcher]
     * @Author Hergua
     * @Date  2019/2/20
     * @return void
     */
    override fun setCredentialsMatcher(credentialsMatcher: CredentialsMatcher) {
        val hashedCredentialsMatcher = HashedCredentialsMatcher()
        hashedCredentialsMatcher.hashAlgorithmName = "MD5"
        hashedCredentialsMatcher.hashIterations = 8
        super.setCredentialsMatcher(hashedCredentialsMatcher)
    }
}
