package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.constant.ResponseModel;
import cn.hergua.servicemodule.domain.entity.ArticleType;
import cn.hergua.servicemodule.service.TypeService;
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
 * @Date 2019/4/1
 * <p>
 *
 * </p>
 */

@RefreshScope
@Slf4j
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("/saveType")
    public ResponseModel saveType(ArticleType type){
        ResponseModel model = new ResponseModel();
        try {
            typeService.saveType(type);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @PostMapping("/delType")
    public ResponseModel delType(Long id){
        ResponseModel model = new ResponseModel();
        try {
            typeService.delType(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @GetMapping("/getTypes")
    public ResponseModel queryType(){
        ResponseModel model = new ResponseModel();
        try {
            model.setData(typeService.queryType());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setMessage(e.getMessage());
        }
        return model;
    }

}
