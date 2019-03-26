package cn.hergua.servicemodule.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tab_auction_record")
public class AuctionRecord  implements Serializable {

    @Id
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "auctionId")
    private Auction auction;

    private Timestamp createTime;

    private BigDecimal bidPrice;

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

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}
