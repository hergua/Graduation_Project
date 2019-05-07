package cn.hergua.servicemodule.service;

import cn.hergua.servicemodule.domain.entity.VideoArticle;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/30
 * <p>
 *
 * </p>
 */
public interface VideoArticleService {

    void save(VideoArticle videoArticle);

    void update(VideoArticle videoArticle);

    void del(Long id);

    List<VideoArticle> queryAllVideoArticle();

    List<VideoArticle> queryVideoByUserId(Long userId);

    VideoArticle queryById(Long id);

    List<VideoArticle> queryWithoutPermit();

}
