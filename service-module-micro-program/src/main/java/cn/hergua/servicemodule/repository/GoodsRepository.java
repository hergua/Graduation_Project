package cn.hergua.servicemodule.repository;

import cn.hergua.servicemodule.domain.Goods;
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
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> queryByUserId(Long userId);
}
