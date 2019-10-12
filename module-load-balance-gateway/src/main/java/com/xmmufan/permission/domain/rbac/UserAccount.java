package com.xmmufan.permission.domain.rbac;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String salt;

    @Column(columnDefinition = "int default 8")
    private int encryptedTimes = 8;

    @Column(columnDefinition = "tinyint default true")
    private boolean enable = true;

    @Column(columnDefinition = "enum('MD5') default 'MD5'")
    private String encryptedAlgorithm = "MD5";

}
