package com.xmmufan.permission.repository;

import com.xmmufan.permission.domain.rbac.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 * <p>
 * </p>
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {

}
