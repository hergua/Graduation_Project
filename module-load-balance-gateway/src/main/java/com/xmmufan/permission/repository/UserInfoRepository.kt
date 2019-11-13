package com.xmmufan.permission.repository

import com.xmmufan.permission.domain.rbac.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoRepository : JpaRepository<UserInfo, String> {

}
