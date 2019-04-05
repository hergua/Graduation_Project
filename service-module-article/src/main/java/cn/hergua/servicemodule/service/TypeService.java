package cn.hergua.servicemodule.service;

import cn.hergua.servicemodule.domain.entity.ArticleType;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
public interface TypeService {

    void saveType(ArticleType articleType);

    void delType(Long id);

    ArticleType updateType(ArticleType articleType);

    List<ArticleType> queryType();

}
