package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.rbac.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo queryByUser_Id(String userId);

}
