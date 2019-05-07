package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.UserInfo;
import com.xmmufan.permission.domain.permission.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo queryByUser(User user);

}
