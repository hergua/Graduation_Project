package com.xmmufan.permission.domain.rbac;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:03
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    private String id;

    @NotNull
    @Column(unique = true)
    private String userGroupName;

    @NotNull
    private String description;

    @ManyToMany
    @JoinTable(name = "userGroup_permission_mapper")
    private List<Permission> basicPermissions;

    @OneToMany
    @JsonIgnore
    private List<User> users;

}
