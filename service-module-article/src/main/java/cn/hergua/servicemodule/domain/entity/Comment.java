package cn.hergua.servicemodule.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
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
public class Comment implements Serializable {

    @Id
    private Long id;

    private Long userId;

    @JoinColumn(name = "toArticleId")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Article.class, cascade = CascadeType.REFRESH)
    private Article article;

    @JoinColumn(name = "referCommentId")
    private Comment referComment;


    private Timestamp createTime;

    private Timestamp updateTime;

    @Lob
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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

    public Comment getReferComment() {
        return referComment;
    }

    public void setReferComment(Comment referComment) {
        this.referComment = referComment;
    }
}
