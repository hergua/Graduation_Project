package com.xmmufan.permission.domain.permission;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmmufan.permission.domain.UserInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/2
 * <p>
 * </p>
 */

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Builder
public class User implements Serializable {

    @Id
    private Long id;

    /**
     * 名称
     */
    @Column(unique = true)
    private String username;

    /**
     * 名称
     */
    @JsonIgnore
    private String name;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 盐值
     */
    @JsonIgnore
    private String salt;

    /**
     * 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
     */
    private byte state;

    /**
     * 一个用户具有多个角色
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    @JsonIgnore
    private List<SysRole> roleList;


}
