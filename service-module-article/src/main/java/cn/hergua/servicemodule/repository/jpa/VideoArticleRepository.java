package cn.hergua.servicemodule.repository.jpa;

import cn.hergua.servicemodule.domain.entity.VideoArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/30
 * <p>
 *
 * </p>
 */
@Repository
public interface VideoArticleRepository extends JpaRepository<VideoArticle, Long> {

    List<VideoArticle> queryByUserId(Long userId);

    List<VideoArticle> queryByStatusOrderByCreateTimeDesc(byte status);

    List<VideoArticle> queryByStatusAndUserIdOrderByCreateTimeDesc(byte status, Long userId);
}
