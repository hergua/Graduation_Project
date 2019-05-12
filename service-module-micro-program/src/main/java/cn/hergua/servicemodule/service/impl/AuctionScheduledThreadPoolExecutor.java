package cn.hergua.servicemodule.service.impl;


import cn.hergua.servicemodule.constant.ResponseModel;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AuctionScheduledThreadPoolExecutor {

    private final GoodsService goodsService;

    private final AuctionRecordService recordService;

    final AuctionService auctionService;

//    private static List<Auction> auctionQueue;
//
//    final RestTemplate restTemplate;

    /**
     * 为商品创建线程池用于计算商品的成交时间
     * 独立开出五条线程每微秒钟计算商品的拍卖状态
     */
    @Autowired
    public AuctionScheduledThreadPoolExecutor(AuctionService auctionService, GoodsService goodsService, AuctionRecordService recordService) {

        this.goodsService = goodsService;
        this.recordService = recordService;
        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPool.scheduleAtFixedRate(scanAuctionSailStatus(), 1, 1, TimeUnit.SECONDS);
//        auctionQueue = auctionService.queryAuctionOnSailing();
        this.auctionService = auctionService;
    }

    /**
     * 在此方法内进行商品状态判断
     *
     * @return 每个微妙对商品状态进行计算
     */
    private Runnable scanAuctionSailStatus() {
        return () -> {
            synchronized (this){
                List<Auction> auctionList = auctionService.queryAllAuction();
                for (Auction auction : auctionList) {
                    if (auction.getTransactionTime().getTime() < System.currentTimeMillis()) {
                        Goods delayGoods = auction.getGoods();
                        if (!delayGoods.getStatus().equals("2")){
                            continue;
                        }
                        if (recordService.queryByGoods(auction).isEmpty()) {
                            delayGoods.setStatus("4");
                        } else {
                            delayGoods.setStatus("3");
                        }
                        goodsService.updateGoods(delayGoods);
                    }
                }
            }
        };
    }

//    public static void addAuctionToQueue(Auction auction){
//        if (auction != null)
//            auctionQueue.add(auction);
//    }



}
