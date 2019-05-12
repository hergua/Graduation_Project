package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.entity.VideoArticle;
import cn.hergua.servicemodule.repository.jpa.VideoArticleRepository;
import cn.hergua.servicemodule.service.VideoArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/30
 * <p>
 *
 * </p>
 */
@Service
public class VideoArticleServiceImpl implements VideoArticleService {

    @Autowired
    VideoArticleRepository repository;

    @Override
    public void save(VideoArticle videoArticle) {
        repository.save(videoArticle);
    }

    @Override
    public void update(VideoArticle videoArticle) {
        repository.saveAndFlush(videoArticle);
    }

    @Override
    public void del(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<VideoArticle> queryAllVideoArticle() {
        return repository.queryByStatusOrderByCreateTimeDesc((byte) 1);
    }

    @Override
    public List<VideoArticle> queryVideoByUserId(Long userId) {
        return repository.queryByUserId(userId);
    }

    @Override
    public VideoArticle queryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<VideoArticle> queryWithoutPermit() {
        return repository.queryByStatusOrderByCreateTimeDesc((byte) 0);
    }

    @Override
    public List<VideoArticle> queryWithoutPermit(Long userId) {
        return repository.queryByStatusAndUserIdOrderByCreateTimeDesc((byte) 0,userId);
    }
}
