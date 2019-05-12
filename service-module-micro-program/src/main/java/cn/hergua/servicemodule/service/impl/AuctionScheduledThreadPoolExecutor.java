package cn.hergua.servicemodule.service.impl;


import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@DependsOn("AuctionRepository")
public class AuctionScheduledThreadPoolExecutor {

    private final GoodsService goodsService;

    private final AuctionRecordService recordService;

    private static List<Auction> auctionQueue;

    /**
     * 为商品创建线程池用于计算商品的成交时间
     * 独立开出五条线程每微秒钟计算商品的拍卖状态
     */
    @Autowired
    public AuctionScheduledThreadPoolExecutor(AuctionService auctionService, GoodsService goodsService, AuctionRecordService recordService) {

        this.goodsService = goodsService;
        this.recordService = recordService;
        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPool.scheduleAtFixedRate(this::scanAuctionSailStatus, 1, 1, TimeUnit.SECONDS);
        auctionQueue = auctionService.queryAuctionOnSailing();

    }

    /**
     * 在此方法内进行商品状态判断
     *
     * @return 每个微妙对商品状态进行计算
     */
    private void scanAuctionSailStatus() {
        log.info("拍卖队列: "+auctionQueue.toString());
        for (Auction auction : auctionQueue) {
            if (auction.getTransactionTime().getTime() <= System.currentTimeMillis()) {
                if (recordService.queryByGoods(auction).isEmpty()) {
                    Goods delayGoods = auction.getGoods();
                    delayGoods.setStatus("4");
                    goodsService.updateGoods(delayGoods);
                } else {
                    Goods delayGoods = auction.getGoods();
                    delayGoods.setStatus("3");
                    goodsService.updateGoods(delayGoods);
                }
                auctionQueue.remove(auction);
            }
        }
    }

    public static void addAuctionToQueue(Auction auction){
        if (auction != null)
            auctionQueue.add(auction);
    }



}
