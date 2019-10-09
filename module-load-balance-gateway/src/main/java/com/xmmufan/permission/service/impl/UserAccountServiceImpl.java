package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.constant.exception.AccountException;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.repository.UserAccountRepository;
import com.xmmufan.permission.service.UserAccountService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository accountRepository;

    @Override
    public void updatePassword(String id, String password) throws AccountException {
        Optional<UserAccount> oldAccount = accountRepository.findById(id);
        if (oldAccount.isPresent()){
            UserAccount newAccount = oldAccount.get();
            newAccount.setPassword(this.encryptedPassword(newAccount,password));
            accountRepository.saveAndFlush(newAccount);
        }else {
            throw new AccountException("该账户不存在！");
        }
    }

    @Override
    public void lockAccount(String id) throws AccountException {
        Optional<UserAccount> oldAccount = accountRepository.findById(id);
        if (oldAccount.isPresent()){
            UserAccount newAccount = oldAccount.get();
            newAccount.setEnable(false);
            accountRepository.saveAndFlush(newAccount);
        }else {
            throw new AccountException("该账户不存在！");
        }
    }

    @Override
    public void unlockAccount(String id) throws AccountException {
        Optional<UserAccount> oldAccount = accountRepository.findById(id);
        if (oldAccount.isPresent()){
            UserAccount newAccount = oldAccount.get();
            newAccount.setEnable(true);
            accountRepository.saveAndFlush(newAccount);
        }else {
            throw new AccountException("该账户不存在！");
        }
    }

    private String encryptedPassword(@NotNull UserAccount account, String newPasswordStr){

        String salt = account.getSalt();
        int encryptTimes = account.getEncryptedTimes();
        String encryptedAlgorithm = account.getEncryptedAlgorithm();
        return new SimpleHash(encryptedAlgorithm,
                newPasswordStr, ByteSource.Util.bytes(salt),
                encryptTimes).toString();
    }

    @Override
    public UserAccount findByUsername(String username) {
        return accountRepository.queryByUsername(username);
    }

    @Override
    public boolean existUsername(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public UserAccount saveUserAccount(UserAccount account) {
        return accountRepository.save(account);
    }
}
