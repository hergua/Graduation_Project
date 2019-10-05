package com.xmmufan.permission.domain.rbac;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:08
 */
@Entity
@Data
public class UserAccount {

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
