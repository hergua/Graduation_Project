package cn.hergua.servicemodule.controller;


import cn.hergua.servicemodule.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Hergua
 */
@RestController
@RequestMapping("/ImageFile")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * 上传图片文件的接口
     * @param file 需要上传的文件图片
     * @return 保存后的URL路径
     */
    @PostMapping("/upload")
    public String uploadImage(MultipartFile file){
        try{
            if (file == null){
                throw new NullPointerException("文件参数为空！");
            }
            String message = imageService.saveImage(file);
            log.info("图片保存成功！！URL: "+ message);
            return message;
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
