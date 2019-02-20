package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.permission.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 * <p>
 * </p>
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);

}
