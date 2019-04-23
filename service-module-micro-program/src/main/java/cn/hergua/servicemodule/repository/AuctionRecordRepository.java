package cn.hergua.servicemodule.repository;

import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.AuctionRecord;
import cn.hergua.servicemodule.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/28
 * <p>
 *
 * </p>
 */
@Repository
public interface AuctionRecordRepository extends JpaRepository<AuctionRecord, Long> {

    @Query(value = "SELECT COUNT(DISTINCT USER_ID) FROM tab_auction_record WHERE AUCTION_ID = :auctionId ",nativeQuery = true)
    public int queryCountOfPayer(@Param("auctionId") Long auctionId);

    public List<AuctionRecord> queryAuctionRecordByAuctionOrderByBidPrice(Auction auction);


}
