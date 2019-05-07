package cn.hergua.servicemodule.repository.jpa;

import cn.hergua.servicemodule.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/3
 * <p>
 *
 * </p>
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByUserIdOrderByCreateTime(Long userId);

    @Query("FROM Article at where at.status = 1 order by at.createTime desc ")
    List<Article> findAllOrderByCreateTime();

    List<Article> findByStatusOrderByCreateTimeDesc(byte status);
}
