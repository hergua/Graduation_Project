package cn.hergua.servicemodule.repository;

import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
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
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    public Auction queryByGoods(Goods goods);

    public List<Auction> queryAuctionsByType(String type);



}
