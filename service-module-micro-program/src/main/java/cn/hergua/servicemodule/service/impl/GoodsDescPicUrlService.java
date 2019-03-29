package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.domain.GoodsDescPicUrl;
import cn.hergua.servicemodule.repository.GoodsDescPicUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GoodsDescPicUrlService {

    @Autowired
    GoodsDescPicUrlRepository repository;

    public List<GoodsDescPicUrl> queryByGoodsIs(Goods goods){
        return repository.queryByGoods(goods);

    }

}
