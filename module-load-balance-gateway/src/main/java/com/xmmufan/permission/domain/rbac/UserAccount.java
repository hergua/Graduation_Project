package com.xmmufan.permission.domain.rbac;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:08
 */
@Entity
@Data
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    private String username;

    private String password;

    private String salt;

    @Column(nullable = false, columnDefinition = "int default 8")
    private int encryptedTimes;

    @Column(nullable = false, columnDefinition = "tinyint default true")
    private boolean enable;

    @Column(nullable = false, columnDefinition = "enum('MD5') default 'MD5'")
    private String encryptedAlgorithm;

}
