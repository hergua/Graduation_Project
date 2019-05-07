package cn.hergua.servicemodule.service;

import cn.hergua.servicemodule.domain.entity.Article;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
public interface ArticleService {

    void save(Article article);

    Article updateArticle(Article article);

    void delArticleById(Long id);

    List<Article> queryByUserId(Long userId);

    Article queryById(Long id);

    List<Article> loadAllArticle();

    List<Article> getWithoutPermissionArticles();

}
