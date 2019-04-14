package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.domain.entity.Like;
import cn.hergua.servicemodule.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/saveLike")
    public ResponseModel saveLike(Like like){
        ResponseModel model = new ResponseModel();
        try{
            service.saveLike(like);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/delLike")
    public ResponseModel delLike(Long id){
        ResponseModel model = new ResponseModel();
        try{
            service.delLike(id);
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/countLike")
    public ResponseModel countLike(Long commentId){
        ResponseModel model = new ResponseModel();
        try{
            model.setData(service.countLike(commentId));
        }catch (Exception e){
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

}
