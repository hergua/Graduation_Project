package cn.hergua.servicemodule.controller.api;


import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.AuctionRecord;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.domain.GoodsDescPicUrl;
import cn.hergua.servicemodule.domain.vo.GoodsAuctionVo;
import cn.hergua.servicemodule.repository.GoodsRepository;
import cn.hergua.servicemodule.service.impl.AuctionRecordService;
import cn.hergua.servicemodule.service.impl.AuctionService;
import cn.hergua.servicemodule.service.impl.GoodsDescPicUrlService;
import cn.hergua.servicemodule.service.impl.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/goods")
@RefreshScope
public class GoodsController {

    @Autowired
    private GoodsService service;

    @Autowired
    private GoodsDescPicUrlService urlService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRecordService recordService;

    @PostMapping("/saveGoods")
    public ResponseModel saveGoods(Goods goods, String urlStrings) {
        ResponseModel model = new ResponseModel();
        try {
            goods.setStatus("1");
            goods.setId(SnowFlake.FLAKE.nextId());
            List<GoodsDescPicUrl> picUrls = new ArrayList<>();
            Arrays.asList(urlStrings.split(",")).forEach(url -> picUrls.add(new GoodsDescPicUrl(SnowFlake.FLAKE.nextId(),new Goods(goods.getId()), url)));
            service.saveGoods(goods);
            picUrls.forEach(url->urlService.saveGoodsPicUrl(url));
            log.info("goods save success by user: " + goods.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @PostMapping("/updateGoods")
    public ResponseModel updateGoods(Goods goods) {
        ResponseModel model = new ResponseModel();
        try {
            if (goods.getId() == null) {
                throw new NullPointerException("id cannot be empty");
            }
            Goods oldGoods = service.queryByGoodsId(goods.getId());
            oldGoods.setIntroduce(goods.getIntroduce());
            oldGoods.setGoodsName(goods.getGoodsName());
            oldGoods.setGoodsHeadPictureUrl(goods.getGoodsHeadPictureUrl());
            oldGoods.setType(goods.getType());
            oldGoods.setGoodsMount(goods.getGoodsMount());
            service.updateGoods(oldGoods);
            log.info("goods update success by user: " + goods.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @PostMapping("/delGoods")
    public ResponseModel delGoods(Long id) {
        ResponseModel model = new ResponseModel();
        try {
            service.delGoods(id);
            log.info("goods delete success ");
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }


    @PostMapping("/queryGoodsByUser")
    public ResponseModel queryGoodsByUser(Long userId) {
        ResponseModel model = new ResponseModel();
        try {
            List<Goods> goods = service.queryByUserId(userId);
            model.setData(goods);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @PostMapping("/queryGoodsStatus")
    public ResponseModel queryGoodsStatus(Long userId) {
        ResponseModel model = new ResponseModel();
        try {
            List<Goods> goods = service.queryByUserId(userId);
            List<Goods> unsail = new ArrayList<>();
            List<Goods> sailing = new ArrayList<>();
            List<Goods> sailed = new ArrayList<>();
            List<Goods> failed = new ArrayList<>();
            goods.forEach(goodsVal -> {
                switch (goodsVal.getStatus()){
                    case "1": unsail.add(goodsVal); break;
                    case "2": sailing.add(goodsVal); break;
                    case "3": sailed.add(goodsVal); break;
                    default: failed.add(goodsVal); break;
                }
            });
            Map<String,List> result = new HashMap<>();
            result.put("unsail", unsail);
            result.put("sailing", sailing);
            result.put("sailed", sailed);
            result.put("failed", failed);
            model.setData(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @GetMapping("/queryAuctionInfoByGoods")
    public ResponseModel queryAuctionInfoByGoods(Long goodsId){
        ResponseModel model = new ResponseModel();
        try {
            Goods goods = service.queryByGoodsId(goodsId);
            Auction auction = auctionService.queryAuctionByGoodsId(goods);
            GoodsAuctionVo vo = new GoodsAuctionVo(auction);
            vo.setAuctionTimes(recordService.queryByGoods(auction).size());
            vo.setBidderNumber(recordService.queryCountOfPayer(auction.getId()));
            vo.setDealPrice(recordService.getLastestPrice(auction.getId()));
            model.setData(vo);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

}
