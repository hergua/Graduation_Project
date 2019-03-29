package cn.hergua.servicemodule.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/26
 * <p>
 *
 * </p>
 */

@Entity
@Table(name = "tab_goods_desc_pic_url")
public class GoodsDescPicUrl implements Serializable {

    @Id
    private Long id;

    @JoinColumn(name = "goodsId")
    @ManyToOne(targetEntity = Goods.class, cascade = CascadeType.REFRESH)
    private Goods goods;

    private String url;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
