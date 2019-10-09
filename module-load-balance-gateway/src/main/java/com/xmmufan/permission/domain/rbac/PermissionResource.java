package com.xmmufan.permission.domain.rbac;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:25
 */

@Entity
@Data
public class PermissionResource implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @Column(columnDefinition="enum('menu','button','link')")
    private String type;

    private String url;

    private String operation;


}
