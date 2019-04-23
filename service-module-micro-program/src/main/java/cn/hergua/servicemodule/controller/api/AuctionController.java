package cn.hergua.servicemodule.controller.api;


import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.service.impl.AuctionScheduledThreadPoolExecutor;
import cn.hergua.servicemodule.service.impl.AuctionService;
import cn.hergua.servicemodule.service.impl.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/auction")
@RefreshScope
public class AuctionController {


    @Autowired
    AuctionService auctionService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    AuctionScheduledThreadPoolExecutor poolExecutor;

    @PostMapping("/createAuction")
    public ResponseModel createAuction(Auction auction, Long goodsId){
        ResponseModel model = new ResponseModel();
        try {
            if (auction == null || goodsId == null){
                throw new RuntimeException("arguments should not be none ");
            }
            Goods goods = goodsService.queryByGoodsId(goodsId);
            if (!goods.getStatus().equals("1")){
                throw new RuntimeException("goods is on sail or sail out");
            }
            goods.setStatus("2");
            auction.setId(new SnowFlake().nextId());
            auction.setGoods(goods);
            auctionService.saveAuction(auction);
            poolExecutor.addAuctionToQueue(auction);
            log.info("the application for auction create success, goodsId:"+ goodsId);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

    @PostMapping("/queryAuctionByUser")
    public ResponseModel queryAuctionByUser(Long userId){
        ResponseModel model = new ResponseModel();
        try {
            List<Goods> goods = goodsService.queryByUserId(userId);
            List<Auction> auctions = new ArrayList<>();
            goods.forEach(singleGoods -> {
                Auction auction = auctionService.queryAuctionByGoodsId(singleGoods);
                auctions.add(auction);
            });
            model.setData(auctions);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

    @GetMapping("/getAllAuction")
    public ResponseModel queryAllAuction(){
        ResponseModel model = new ResponseModel();
        try {
            List<Auction> auctions = auctionService.queryAllAuction();
            model.setData(auctions);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

    @GetMapping("/auctionOnSailing")
    public ResponseModel queryAuctionOnSailing(){
        ResponseModel model = new ResponseModel();
        try {
            List<Auction> auctions = auctionService.queryAuctionOnSailing();
            model.setData(auctions);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

    public void aucitonQueue(Auction auction){

    }



}
