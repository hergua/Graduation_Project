package com.xmmufan.permission.service.impl

import com.google.protobuf.ServiceException
import com.xmmufan.permission.aspect.EncryptedPasswordAndSalt
import com.xmmufan.permission.constant.exception.AccountException
import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserAccount
import com.xmmufan.permission.domain.rbac.UserInfo
import com.xmmufan.permission.repository.UserAccountRepository
import com.xmmufan.permission.service.UserAccountService
import com.xmmufan.permission.service.UserService
import org.apache.shiro.crypto.hash.SimpleHash
import org.apache.shiro.util.ByteSource
import org.springframework.stereotype.Service

import javax.validation.constraints.NotNull

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
class UserAccountServiceImpl(private val accountRepository: UserAccountRepository, private val userService: UserService) : UserAccountService {

    @EncryptedPasswordAndSalt
    @Throws(AccountException::class)
    override fun updatePassword(id: String, password: String) {
        val oldAccount = accountRepository.findById(id)
        if (oldAccount.isPresent) {
            val newAccount = oldAccount.get()
            newAccount.password = this.encryptedPassword(newAccount, password)
            accountRepository.saveAndFlush(newAccount)
        } else {
            throw AccountException("该账户不存在！")
        }
    }

    @Throws(ServiceException::class)
    override fun lockAccount(id: String) {
        val oldAccount = accountRepository.findById(id)
        if (oldAccount.isPresent) {
            val newAccount = oldAccount.get()
            newAccount.isEnable = false
            accountRepository.saveAndFlush(newAccount)
        } else {
            throw ServiceException("该账户不存在！")
        }
    }

    @Throws(AccountException::class)
    override fun unlockAccount(id: String) {
        val oldAccount = accountRepository.findById(id)
        if (oldAccount.isPresent) {
            val newAccount = oldAccount.get()
            newAccount.isEnable = true
            accountRepository.saveAndFlush(newAccount)
        } else {
            throw AccountException("该账户不存在！")
        }
    }

    private fun encryptedPassword(@NotNull account: UserAccount, newPasswordStr: String): String {

        val salt = account.salt
        val encryptTimes = account.encryptedTimes
        val encryptedAlgorithm = account.encryptedAlgorithm
        return SimpleHash(encryptedAlgorithm,
                newPasswordStr, ByteSource.Util.bytes(salt!!),
                encryptTimes).toString()
    }

    override fun findByUsername(username: String): UserAccount? {
        return accountRepository.queryByUsername(username)
    }

    @EncryptedPasswordAndSalt
    @Throws(AccountException::class)
    override fun saveUserAccount(account: UserAccount) {
        if (existUsername(account.username))
            throw AccountException("该用户名已存在！")
        val userAccount = accountRepository.save(account)
        userService.saveUser(User(account = userAccount, info = UserInfo.empty()))
    }

    private fun existUsername(username: String): Boolean {
        return accountRepository.queryByUsername(username) !== null
    }
}
