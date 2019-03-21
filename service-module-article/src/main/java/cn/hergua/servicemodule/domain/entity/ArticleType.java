package cn.hergua.servicemodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/3
 * <p>
 *
 * </p>
 */

@Entity
@Table(name = "tab_type")
public class ArticleType {

    @Id
    private Long id;

    private String secondType;

    private String typeDesc;

    private String firstType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public ArticleType() {
    }

    public ArticleType(Long id, String firstType, String secondType, String typeDesc) {
        this.id = id;
        this.secondType = secondType;
        this.typeDesc = typeDesc;
        this.firstType = firstType;
    }
}
