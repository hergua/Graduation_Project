package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.entity.Article;
import cn.hergua.servicemodule.domain.entity.Comment;
import cn.hergua.servicemodule.service.ArticleService;
import cn.hergua.servicemodule.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    /**
     * 通过文章ID获取所有评论信息，接口
     * @param articleId 文章ID
     * @return 接口返回信息
     */
    @GetMapping("/getArticleComments")
    public ResponseModel getArticleComments(Long articleId){
        ResponseModel model = new ResponseModel();
        try{
            List<Comment> comments = commentService.queryByArticle(articleId);
            comments.sort((o1, o2) -> o1.getCreateTime().getTime() > o2.getCreateTime().getTime() ? 0 : 1);
            model.setData(comments);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage("服务器内部出错，错误信息："+ e.getMessage());
        }
        return model;
    }

    @PostMapping("/saveComment")
    public ResponseModel saveComment(Comment comment, Long referId, Long articleId){
        ResponseModel model = new ResponseModel();
        try{
            if (articleId == null)
                throw new RuntimeException("传入文章ID为空，数据无法匹配");
            comment.setArticle(articleService.queryById(articleId));
            comment.setId(new SnowFlake().nextId());
            comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
            if (referId != null && referId != 0){
                comment.setReferComment(commentService.queryById(referId));
            }
            commentService.saveComment(comment);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage("服务器内部出错，错误信息："+ e.getMessage());
        }
        return model;
    }

    @PostMapping("/delComment")
    public ResponseModel delComment(Long id){
        ResponseModel model = new ResponseModel();
        try{
            if (id != null){
                articleService.delArticleById(id);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage("服务器内部出错，错误信息："+ e.getMessage());
        }
        return model;
    }


}
