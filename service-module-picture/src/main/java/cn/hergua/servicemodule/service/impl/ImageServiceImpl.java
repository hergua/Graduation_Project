package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.service.ImageService;
import cn.hergua.servicemodule.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/21
 * <p>
 * </p>
 */

@Service
@Slf4j
@PropertySource("classpath:/qiniu.properties")
public class ImageServiceImpl implements ImageService {

    private static final String ACCESS_KEY = "iK_pM9aLioR5k3tB_r2oYNuerxn_hA8kcyE8PBiX";

    private static final String SECRET_KEY = "JFwZbldp7sDzAxvxTJSD2yB7";

    private static final String buckName = "picture_server";

    private static final String IMAGE_DOMAIN = "popf2cli9.bkt.clouddn.com";

    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private Configuration configuration = new Configuration(Zone.zone2());
    private UploadManager uploadManager = new UploadManager(configuration);


    private String getUpToken() {
        return auth.uploadToken(buckName);
    }


    /**
     * 进行文件上传七牛云
     * @param file 图片文件
     * @return 七牛云返回的路径或抛出异常
     * @throws IOException 可能发生的异常
     */
    @Override
    public String saveImage(MultipartFile file) throws IOException {

        try {
            int dotPos = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            if (dotPos < 0) {
                throw new RuntimeException("图片文件名非法");
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            if (!FileUtil.isFileAllowed(fileExt)) {
                throw new RuntimeException("仅允许上传图片文件");
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            if (res.isOK() && res.isJson()) {
                return IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                log.error("七牛异常:" + res.bodyString());
                throw new RuntimeException("七牛异常:" + res.bodyString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
