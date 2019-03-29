package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/21
 * <p>
 * </p>
 */
@Service
public class AuctionService {

    @Autowired
    AuctionRepository repository;

    public void saveAuction(Auction auction){
        repository.save(auction);
    }

    public Auction queryAuctionByGoodsId(Goods goods){
        return repository.queryByGoods(goods);
    }

    public List<Auction> queryAuctionsByType(String type){
        return repository.queryAuctionsByType(type);
    }

}
