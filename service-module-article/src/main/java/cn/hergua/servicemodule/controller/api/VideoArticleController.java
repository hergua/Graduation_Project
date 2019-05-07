package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.entity.ArticleType;
import cn.hergua.servicemodule.domain.entity.VideoArticle;
import cn.hergua.servicemodule.service.VideoArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/4/1
 * <p>
 *
 * </p>
 */
@RestController
@RefreshScope
@RequestMapping("/videoArticle")
@Slf4j
public class VideoArticleController {

    private final VideoArticleService service;

    @Autowired
    public VideoArticleController(VideoArticleService service) {
        this.service = service;
    }

    @PostMapping("/saveVideoArticle")
    public ResponseModel saveVideoArticle(Long typeId, VideoArticle videoArticle){
        ResponseModel model = new ResponseModel();
        try {
            if (StringUtils.isAnyBlank(videoArticle.getIntroduction(),videoArticle.getTitle(),videoArticle.getVideoUrl()) ||
                    videoArticle.getUserId() == null || typeId == null){
                throw new Exception("保存视频文章：传入参数错误，存在空值");

            }
            if (videoArticle.getUserId() == 1L){
                videoArticle.setStatus((byte) 1);
            }else {
                videoArticle.setStatus((byte) 0);
            }
            videoArticle.setId(new SnowFlake().nextId());
            videoArticle.setCreateTime(new Timestamp(System.currentTimeMillis()));
            videoArticle.setType(new ArticleType(typeId));
            service.save(videoArticle);
            log.info("article: "+videoArticle.getId()+" save success by user: "+videoArticle.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage()+"  用户ID："+videoArticle.getUserId());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @PostMapping("/updateVideoArticle")
    public ResponseModel updateVideoArticle(Long typeId, VideoArticle videoArticle){
        ResponseModel model = new ResponseModel();
        try {
            VideoArticle oldArticle = service.queryById(videoArticle.getId());
            oldArticle.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            oldArticle.setType(new ArticleType(typeId));
            if (StringUtils.isAnyBlank(videoArticle.getVideoUrl(),videoArticle.getIntroduction(),videoArticle.getTitle()) ||
                     typeId == null || videoArticle.getId() == null){
                throw new RuntimeException("保存文章，传入参数错误，存在空值");
            }
            oldArticle.setHeadPictureUrl(videoArticle.getHeadPictureUrl());
            oldArticle.setIntroduction(videoArticle.getIntroduction());
            oldArticle.setTitle(videoArticle.getTitle());
            oldArticle.setVideoUrl(videoArticle.getVideoUrl());
            service.update(oldArticle);
            log.info("article: "+videoArticle.getId()+" update success by user: "+videoArticle.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @PostMapping("/delVideoArticle")
    public ResponseModel delVideoArticle(Long id){
        ResponseModel model = new ResponseModel();
        try {
            service.del(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/queryAllVideoArticle")
    public ResponseModel queryAllVideoArticle(){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(service.queryAllVideoArticle());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/queryVideoArticleByUserId")
    public ResponseModel queryVideoArticleByUserId(Long userId){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(service.queryVideoByUserId(userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/getWithoutPermitVideo")
    public ResponseModel getWithoutPermitVideo(){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(service.queryWithoutPermit());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/queryVideoArticleById")
    public ResponseModel queryVideoArticleById(Long id){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(service.queryById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }




}
