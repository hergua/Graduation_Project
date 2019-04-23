package cn.hergua.servicemodule.service.impl;


import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AuctionScheduledThreadPoolExecutor {

    @Autowired
    AuctionService auctionService;

    @Autowired
    GoodsService goodsService;

    private ScheduledExecutorService scheduledThreadPool;

    private static List<Auction> auctionQueue = new ArrayList<>();

    public AuctionScheduledThreadPoolExecutor() {
        scheduledThreadPool = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPool.schedule(this::scanAuctionSailStatus, 1, TimeUnit.SECONDS);
    }

    @Bean
    public Runnable scanAuctionSailStatus(){
        return () -> {
            List<Auction> auctionQueue = AuctionScheduledThreadPoolExecutor.auctionQueue;
            auctionQueue.forEach(auction -> {
                if (auction.getTransactionTime().getTime() <= System.currentTimeMillis()){
                    Goods delayGoods = auction.getGoods();
                    delayGoods.setStatus("3");
                    goodsService.updateGoods(delayGoods);
                }
            });
        };
    }

    public void addAuctionToQueue(Auction auction){
        if (auction != null)
            AuctionScheduledThreadPoolExecutor.auctionQueue.add(auction);
    }



}
