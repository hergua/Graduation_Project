package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.rbac.PermissionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 黄源钦
 * @Description
 * @Date 13:39
 */
@Repository
public interface PermissionResourceRepository extends JpaRepository<PermissionResource,String> {
}
