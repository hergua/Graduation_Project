package com.xmmufan.permission.domain.rbac;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author 黄源钦
 * @Description
 * @Date 14:29
 */
@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String nickName;

    private String gender;

    private String sign;

    private LocalDate birthday;

    private String email;

    private String location;

    private String headPicture;

}
