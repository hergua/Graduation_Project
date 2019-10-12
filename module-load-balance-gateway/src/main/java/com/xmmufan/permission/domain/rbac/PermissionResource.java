package com.xmmufan.permission.domain.rbac;

import com.xmmufan.permission.constant.permission.DataOperationEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    private String id;

    @Column(columnDefinition="enum('menu','button','link')")
    private String type;

    @NotNull
    private String url;

    @NotNull
    private String operation;



}
