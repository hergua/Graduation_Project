package cn.hergua.servicemodule.repoistory.jpa;

import cn.hergua.servicemodule.domain.entity.ArticleType;
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
public interface TypeRepository extends JpaRepository<ArticleType, Long> {
}
