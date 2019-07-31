package com.xmgl.blog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 张富华
 * @date 2019/1/29
 */
@Component
public final class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    private static String winPath;

    private static String unixPath;

    @Value("${win.path}")
    public void setWinPath(String winPath) {
        PathUtil.winPath = winPath;
    }

    @Value("${unix.path}")
    public void setUnixPath(String unixPath) {
        PathUtil.unixPath = unixPath;
    }

    /**
     * 获取不同操作系统的图片存放的基本路径
     * @return
     */
    public static String getImgBasePath(){
        //获取操作系统
        String os = System.getProperty("os.name");
        String basePath = null;
        if(os.toLowerCase().startsWith("win")){
            basePath = winPath;
        }else{
            basePath = unixPath;
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 将商品图片放在商品的分类目录下
     * @param categoryId 商品分类
     * @return
     */
    public static String getImagePathWithCategory(String categoryId){
        String path = "/photo/" + categoryId + "/";
        return path.replace("/", seperator);
    }
}
