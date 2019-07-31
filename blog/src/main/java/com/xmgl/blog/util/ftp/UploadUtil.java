package com.xmgl.blog.util.ftp;


import java.util.UUID;

/**
 * @author 张富华
 * @date 2019/2/11
 */
public class UploadUtil {

    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取随机文件名
     * @param fileName 原文件名
     * @return
     */
    public static String generateRandomFileName(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + ext;
    }


    /**
     * 根据fileName获取随机目录
     * @param uidFileName
     * @return
     */
    public static String generateRandomDir(String uidFileName){
        int hashCode = uidFileName.hashCode();
        int d1 = hashCode & 0xf;
        int d2 = (hashCode >> 4) & 0xf;
        return d1 + "/" + d2;
    }
}
