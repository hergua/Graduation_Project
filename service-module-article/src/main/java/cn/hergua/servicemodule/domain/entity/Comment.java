package cn.hergua.servicemodule.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/3
 * <p>
 *
 * </p>
 */

@Entity
@Table(name = "tab_comment")
public class Comment {

    @Id
    private Long id;

    private Long userId;

    private Long toArticleId;

    private Long referCommentId;

    private Timestamp createTime;

    private Timestamp updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getToArticleId() {
        return toArticleId;
    }

    public void setToArticleId(Long toArticleId) {
        this.toArticleId = toArticleId;
    }

    public Long getReferCommentId() {
        return referCommentId;
    }

    public void setReferCommentId(Long referCommentId) {
        this.referCommentId = referCommentId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
