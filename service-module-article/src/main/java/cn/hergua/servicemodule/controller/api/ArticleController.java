package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.constant.SnowFlake;
import cn.hergua.servicemodule.domain.entity.Article;
import cn.hergua.servicemodule.domain.entity.ArticleType;
import cn.hergua.servicemodule.service.ArticleService;
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
 * @Date 2019/3/27
 * <p>
 * 11
 * </p>
 */
@RefreshScope
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/saveArticle")
    public ResponseModel saveArticle(Long typeId, Article article){
        ResponseModel model = new ResponseModel();
        try {
            if (StringUtils.isAnyBlank(article.getContent(),article.getIntroduction(),article.getTitle()) ||
                    article.getUserId() == null || typeId == null){
                throw new Exception("保存文章，传入参数错误，存在空值");

            }
            article.setId(new SnowFlake().nextId());
            ArticleType type = new ArticleType(typeId);
            article.setCreateTime(new Timestamp(System.currentTimeMillis()));
            article.setArticleType(type);
            articleService.save(article);
            log.info("article: "+article.getId()+" save success by user: "+article.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage()+"  用户ID："+article.getUserId());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @PostMapping("/updateArticle")
    public ResponseModel updateArticle(Long typeId, Article article){
        ResponseModel model = new ResponseModel();
        try {
            ArticleType type = new ArticleType(typeId);
            article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            article.setArticleType(type);
            if (StringUtils.isAnyBlank(article.getContent(),article.getIntroduction(),article.getTitle()) ||
            article.getUserId() == null || typeId == null || article.getId() == null){
                throw new RuntimeException("保存文章，传入参数错误，存在空值");
            }
            articleService.updateArticle(article);
            log.info("article: "+article.getId()+" update success by user: "+article.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/getAllArticle")
    public ResponseModel loadAllArticle(){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(articleService.loadAllArticle());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @PostMapping("/delArticle")
    public ResponseModel delArticle(Long articleId){
        ResponseModel model = new ResponseModel();
        try {
            articleService.delArticleById(articleId);
            model.setMessage("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/getArticleByUserId")
    public ResponseModel getArticleByUserId(Long userId){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(articleService.queryByUserId(userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

}
