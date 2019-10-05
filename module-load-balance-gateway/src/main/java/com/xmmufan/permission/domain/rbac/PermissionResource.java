package com.xmmufan.permission.domain.rbac;

import com.xmmufan.permission.constant.permission.DataOperationEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:25
 */

@Entity
@Data
public class PermissionResource {

    @Id
    @GeneratedValue
    private String id;

    @Column(columnDefinition="enum('menu','button')")
    private String type;

    private String url;

    private DataOperationEnum operation;



}
