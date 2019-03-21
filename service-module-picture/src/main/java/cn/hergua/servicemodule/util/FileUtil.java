package cn.hergua.servicemodule.util;

public class FileUtil {


    private static final String[] IMAGE_FILE_ALLOWED = new String[] { "png", "bmp", "jpg", "jpeg","pdf" };

    public static boolean isFileAllowed(String fileName) {
        for (String ext : IMAGE_FILE_ALLOWED) {
            if (ext.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

}
