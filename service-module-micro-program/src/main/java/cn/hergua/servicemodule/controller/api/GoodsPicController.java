package cn.hergua.servicemodule.controller.api;


import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.domain.GoodsDescPicUrl;
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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/goodsPic")
@RefreshScope
public class GoodsPicController {

    @Autowired
    GoodsDescPicUrlService picUrlService;

    @Autowired
    GoodsService goodsService;

    @PostMapping("/savePicUrl")
    public ResponseModel savePic(Long goodsId, String url){
        ResponseModel model = new ResponseModel();
        try {
            Goods goods = goodsService.queryByGoodsId(goodsId);
            if (StringUtils.isNotBlank(url) || goods == null){
                throw new RuntimeException("goodsId or picture url cannot be null");
            }
            String [] urls = url.split(",");
//            for (String singleUrl : urls) {
//                GoodsDescPicUrl picUrl = new GoodsDescPicUrl(new SnowFlake().nextId(), singleUrl);
//                picUrlService.saveGoodsPicUrl(picUrl);
//            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

    @GetMapping("/delPicUrl")
    public ResponseModel delPic(Long id){
        ResponseModel model = new ResponseModel();
        try {
            picUrlService.delGoodsPicUrl(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
        return model;
    }

}
