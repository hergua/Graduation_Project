package cn.hergua.servicemodule.repository;

import cn.hergua.servicemodule.domain.Goods;
import cn.hergua.servicemodule.domain.GoodsDescPicUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/28
 * <p>
 *
 * </p>
 */
@Repository
public interface GoodsDescPicUrlRepository extends JpaRepository<GoodsDescPicUrl, Long> {

    public List<GoodsDescPicUrl> queryByGoods(Goods goods);
}
