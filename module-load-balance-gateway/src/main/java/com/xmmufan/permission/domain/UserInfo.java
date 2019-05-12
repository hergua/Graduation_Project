package com.xmmufan.permission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmmufan.permission.domain.permission.User;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/26
 * <p>
 *
 * </p>
 */

@Entity
@Table(name = "sys_user_info")
public class UserInfo {

    @Id
    private Long id;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    private String nickName;

    private String gender;

    private String sign;

    private Date birthday;

    private String email;

    private String location;

    private String headPicture;

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
