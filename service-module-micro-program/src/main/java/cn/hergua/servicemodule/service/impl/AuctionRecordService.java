package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.AuctionRecord;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.repository.AuctionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class AuctionRecordService {

    @Autowired
    AuctionRecordRepository repository;

    public void saveAuctionRecord(AuctionRecord record){
        repository.save(record);
    }

    public int queryCountOfPayer(Long auctionId){
        return repository.queryCountOfPayer(auctionId);
    }

    public List<AuctionRecord> queryByGoods(Auction auction){
        return repository.queryAuctionRecordByAuctionOrderByBidPrice(auction);
    }

    public List<AuctionRecord> queryByUserId(Long userId){
        return repository.queryByUserId(userId);
    }

    public BigDecimal getLastestPrice(Long auctionId){
        return repository.getLastestPrice(auctionId);
    }

}
