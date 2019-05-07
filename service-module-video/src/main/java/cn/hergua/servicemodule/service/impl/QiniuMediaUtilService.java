package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.util.QiNiuMedia;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */

@Service
@Slf4j
public class QiniuMediaUtilService {

    private static final String ACCESS_KEY = "iK_pM9aLioR5k3tB_r2oYNuerxn_hA8kcyE8PBiX";

    private static final String SECRET_KEY = "JFwZbldp7sDzAxvxTJSD2yB7-tUdiuLKsEGlsqoi";

    private static final String BUCK_NAME = "picture_server";

    private static final String IMAGE_DOMAIN = "file.hergua.cn/";


    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private Configuration configuration = new Configuration(Zone.zone2());
    private UploadManager uploadManager = new UploadManager(configuration);


    private String getUpToken() {
        return auth.uploadToken(BUCK_NAME);
    }


    /**
     * 进行文件上传七牛云
     *
     * @param file 视频文件
     * @return 七牛云返回的路径或抛出异常
     * @throws IOException 可能发生的异常
     */
    public String saveVideo(MultipartFile file) throws IOException {

        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            throw new RuntimeException("视频文件名非法");
        }
        //重命名视频文件--为了不重复
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
        //进行文件上传到七牛云服务器
        Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
        if (res.isOK() && res.isJson()) {
            return IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
        } else {
            log.error("七牛异常:" + res.bodyString());
            throw new RuntimeException("七牛异常:" + res.bodyString());
        }
    }

}
