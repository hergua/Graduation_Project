package cn.hergua.servicemodule.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/26
 * <p>
 *
 * </p>
 */

@Entity
@Table(name = "tab_goods")
public class Goods  implements Serializable {

    @Id
    private Long id;

    private Long articleId;

    private Long userId;

    private String introduce;

    private String goodsName;

    private String goodsHeadPictureUrl;

    private String type;

    private Integer goodsMount;

    /**
     * 三种状态，在售，未出售，已售出
     */
    private String status;

    @OneToMany(mappedBy = "goods", targetEntity = GoodsDescPicUrl.class, cascade = CascadeType.ALL)
    private List<GoodsDescPicUrl> urls;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsHeadPictureUrl() {
        return goodsHeadPictureUrl;
    }

    public void setGoodsHeadPictureUrl(String goodsHeadPictureUrl) {
        this.goodsHeadPictureUrl = goodsHeadPictureUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGoodsMount() {
        return goodsMount;
    }

    public void setGoodsMount(Integer goodsMount) {
        this.goodsMount = goodsMount;
    }

    public List<GoodsDescPicUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<GoodsDescPicUrl> urls) {
        this.urls = urls;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
