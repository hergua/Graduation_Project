package cn.hergua.servicemodule.util;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
public class QiNiuMedia {

    private static QiNiuMedia media = null;
    public static final String ACCESSKEY = "iK_pM9aLioR5k3tB_r2oYNuerxn_hA8kcyE8PBiX";
    public static final String SECRETKEY = "JFwZbldp7sDzAxvxTJSD2yB7-tUdiuLKsEGlsqoi";
    public static final String BUCKETNAME = "picture_server";
    public static final String DOMAIN = "http://file.hergua.cn/";
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String domain;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 实例化一个QiNiuMedia实例
     * @uesr "xinzhifu@knet.cn"
     * @date 2016年11月19日下午2:58:27
     */
    public static synchronized QiNiuMedia getInstance() {
        if (media == null) {
            media = new QiNiuMedia();
            media.setAccessKey(ACCESSKEY);
            media.setSecretKey(SECRETKEY);
            media.setBucketName(BUCKETNAME);
            media.setDomain(DOMAIN);
        }
        return media;
    }

}
