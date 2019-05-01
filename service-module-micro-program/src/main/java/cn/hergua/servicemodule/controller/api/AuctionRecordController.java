package cn.hergua.servicemodule.controller.api;


import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.AuctionRecord;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.service.impl.AuctionRecordService;
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

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/auctionRecord")
@RefreshScope
public class AuctionRecordController {

    @Autowired
    AuctionRecordService recordService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    AuctionService auctionService;


    @PostMapping("/saveRecord")
    public ResponseModel saveRecord(AuctionRecord record, Long goodsId){
        ResponseModel model = new ResponseModel();
        try{

            record.setId(new SnowFlake().nextId());
            Goods goods = goodsService.queryByGoodsId(goodsId);
            Auction auction = auctionService.queryAuctionByGoodsId(goods);
            record.setAuction(auction);
            recordService.saveAuctionRecord(record);
            log.info("record saved success by user : " + record.getUserId());
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @GetMapping("/queryRecordByGoods")
    public ResponseModel queryRecordByGoods(Long goodsId){
        ResponseModel model = new ResponseModel();
        try{
            Goods goods = goodsService.queryByGoodsId(goodsId);
            Auction auction = auctionService.queryAuctionByGoodsId(goods);
            List<AuctionRecord> record = recordService.queryByGoods(auction);
            model.setData(record);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @GetMapping("/queryRecordByUser")
    public ResponseModel queryRecordByUser(Long userId){
        ResponseModel model = new ResponseModel();
        try{
            model.setData(recordService.queryByUserId(userId));
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }


}
