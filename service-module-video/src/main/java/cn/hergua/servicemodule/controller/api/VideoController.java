package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.service.impl.QiniuMediaUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Hergua
 * @version: 1.0     2018/10/21
 * <p>
 * </p>
 */

@RestController
@Slf4j
public class VideoController {

    @Autowired
    QiniuMediaUtilService service;

    /**
     * 进行视频文件上传的路径映射
     * @param file
     * @return
     */
    @PostMapping("/uploadVideo")
    public String uploadVideo(MultipartFile file) {

        try {
            log.info(file.getOriginalFilename());
            return service.saveVideo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }



}
