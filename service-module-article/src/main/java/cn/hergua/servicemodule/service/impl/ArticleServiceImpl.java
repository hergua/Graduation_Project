package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.entity.Article;
import cn.hergua.servicemodule.repository.jpa.ArticleRepository;
import cn.hergua.servicemodule.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository repository;

    @Override
    public void save(Article article) {
        repository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return repository.saveAndFlush(article);
    }

    @Override
    public void delArticleById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Article> queryByUserId(Long userId) {
        return repository.findArticlesByUserIdOrderByCreateTime(userId);
    }

    @Override
    public Article queryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Article> loadAllArticle() {
        return repository.findAllOrderByCreateTime();
    }
}
