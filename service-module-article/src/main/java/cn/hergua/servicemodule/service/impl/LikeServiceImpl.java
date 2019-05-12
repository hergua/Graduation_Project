package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.entity.Comment;
import cn.hergua.servicemodule.domain.entity.Like;
import cn.hergua.servicemodule.repository.jpa.LikeRepository;
import cn.hergua.servicemodule.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository repository;

    @Override
    public void saveLike(Like like) {
        repository.save(like);
    }

    @Override
    public void delLike(Long id) {
        repository.deleteById(id);
    }

    @Override
    public int countLike(Long commentId) {
        return repository.countByComment_Id(commentId);
    }

    @Override
    public boolean isLike(Long userId, Long commentId) {
        return !repository.queryByUserIdAndComment_Id(userId,commentId).isEmpty();
    }

    @Override
    public Like queryCurrentLike(Long userId, Comment comment) {
        return repository.queryByCommentAndUserId(comment,userId);
    }
}
