package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.rbac.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 黄源钦
 * @Description
 * @Date 13:41
 */
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup,String> {
}
