package cn.hergua.servicemodule.repository.jpa;

import cn.hergua.servicemodule.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/3
 * <p>
 *
 * </p>
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    int countByComment_Id(Long commentId);

}
