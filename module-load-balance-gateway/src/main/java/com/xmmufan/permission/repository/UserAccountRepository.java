package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.rbac.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 黄源钦
 * @Description
 * @Date 13:40
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {

    UserAccount queryByUsername(String username);
}
