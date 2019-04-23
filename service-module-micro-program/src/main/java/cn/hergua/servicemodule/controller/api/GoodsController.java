package cn.hergua.servicemodule.controller.api;


import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.repository.GoodsRepository;
import cn.hergua.servicemodule.service.impl.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
@RefreshScope
public class GoodsController {

    @Autowired
    private GoodsService service;

    @PostMapping("/saveGoods")
    public ResponseModel saveGoods(Goods goods){
        ResponseModel model = new ResponseModel();
        try{
            goods.setStatus("1");
            goods.setId(new SnowFlake().nextId());
            service.saveGoods(goods);
            log.info("goods save success by user: "+goods.getUserId());
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @PostMapping("/updateGoods")
    public ResponseModel updateGoods(Goods goods){
        ResponseModel model = new ResponseModel();
        try{
            if (goods.getId() == null){
                throw new NullPointerException("id cannot be empty");
            }
            service.updateGoods(goods);
            log.info("goods update success by user: "+goods.getUserId());
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

    @PostMapping("/delGoods")
    public ResponseModel delGoods(Long id){
        ResponseModel model = new ResponseModel();
        try{
            service.delGoods(id);
            log.info("goods delete success ");
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }


    @PostMapping("/queryGoodsByUser")
    public ResponseModel queryGoodsByUser(Long userId){
        ResponseModel model = new ResponseModel();
        try{
            List goods = service.queryByUserId(userId);
            model.setData(goods);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return model;
    }

}
