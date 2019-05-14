package cn.hergua.servicemodule.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "tab_auction")
public class Auction implements Serializable {

    @Id
    private Long id;

    @JoinColumn(name = "goodsId")
    @OneToOne(targetEntity = Goods.class, cascade = CascadeType.ALL)
    private Goods goods;

    private String type;

    private BigDecimal startingPrice;

    private BigDecimal priceIncrease;

    private BigDecimal currentPrice;

    @OneToMany(mappedBy = "auction", targetEntity = AuctionRecord.class, cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<AuctionRecord> records;

    private Date transactionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getPriceIncrease() {
        return priceIncrease;
    }

    public void setPriceIncrease(BigDecimal priceIncrease) {
        this.priceIncrease = priceIncrease;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<AuctionRecord> getRecords() {
        return records;
    }

    public void setRecords(List<AuctionRecord> records) {
        this.records = records;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
