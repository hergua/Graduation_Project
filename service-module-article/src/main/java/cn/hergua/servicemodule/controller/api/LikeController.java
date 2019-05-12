package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.CountLikeVo;
import cn.hergua.servicemodule.domain.entity.Comment;
import cn.hergua.servicemodule.domain.entity.Like;
import cn.hergua.servicemodule.service.CommentService;
import cn.hergua.servicemodule.service.LikeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/4/8
 * <p>
 *
 * </p>
 */

@RefreshScope
@RestController
@RequestMapping("/like")
@Slf4j
public class LikeController {

    @Autowired
    LikeService service;

    @Autowired
    CommentService commentService;

    @PostMapping("/saveLike")
    public ResponseModel saveLike(Long userId, Long commentId){
        ResponseModel model = new ResponseModel();
        try{
            if (userId == null || commentId == null){
                throw new RuntimeException("点赞失败！用户ID或评论ID为空");
            }
            Like like = new Like();
            like.setId(new SnowFlake().nextId());
            like.setCreateTime(new Timestamp(System.currentTimeMillis()));
            like.setUserId(userId);
            like.setComment(commentService.queryById(commentId));
            service.saveLike(like);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/delLike")
    public ResponseModel delLike(Long commentId, Long userId){
        ResponseModel model = new ResponseModel();
        try{
            Comment comment = commentService.queryById(commentId);
            Like like = service.queryCurrentLike(userId,comment);
            service.delLike(like.getId());
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/countLike")
    public ResponseModel countLike(Long commentId, Long userId){
        ResponseModel model = new ResponseModel();
        try{
            if (userId == null){
                model.setData(service.countLike(commentId));
            }else {
                int countNumber = service.countLike(commentId);
                boolean isLike = service.isLike(userId, commentId);
                model.setData(new CountLikeVo(isLike,countNumber));
            }
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

}
