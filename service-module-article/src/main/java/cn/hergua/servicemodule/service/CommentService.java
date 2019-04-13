package cn.hergua.servicemodule.service;

import cn.hergua.servicemodule.domain.entity.Comment;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
public interface CommentService {

    List<Comment> queryByArticle(Long articleId);

}
