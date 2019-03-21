package cn.hergua.servicemodule.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Hergua
 */
public interface ImageService {

    /**
     * @Descrption 保存图片
     * @param file 需要保存的图片  大小不超过1M
     * @return 保存完的URL路径
     * @throws IOException
     */
    String saveImage(MultipartFile file) throws IOException;

}
