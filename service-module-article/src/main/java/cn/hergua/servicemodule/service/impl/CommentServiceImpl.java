package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.entity.Comment;
import cn.hergua.servicemodule.repository.jpa.CommentRepository;
import cn.hergua.servicemodule.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    @Override
    public List<Comment> queryByArticle(Long articleId) {
        return repository.findByArticle_IdOrderByCreateTimeDesc(articleId);
    }
}
