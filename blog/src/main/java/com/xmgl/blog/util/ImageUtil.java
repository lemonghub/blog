package com.xmgl.blog.util;

import com.xmgl.blog.model.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 张富华
 * @date 2019/1/29
 */
public final class ImageUtil {

    private static final Logger log = LoggerFactory.getLogger(ImageUtil.class);

    //时间格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    /**
     * 存储详情图片 图片质量较高
     * @param img 图片的输入流和名称
     * @param targetAddr 目标主路径
     * @return
     */
    public static String generateNormalImage(ImageHolder img, String targetAddr){
        //获取随机文件名
        String realFileName = getRandomFileName();
        //获取文件扩展名
        String extension = getFileExtension(img.getImageName());
        //创建路径
        makeDirPath(targetAddr);
        //获取文件的保存路径  带文件名
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(img.getImageInputStream()).size(337,640)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片创建失败");
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File file = new File(realFileParentPath);
        if(!file.exists()){
            //递归的创建父目录
            file.mkdirs();
        }
    }

    /**
     * 获取文件的扩展名
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名  当前时间+随机五位数字
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int num = random.nextInt(89999) + 10000;
        String nowTime = dateFormat.format(new Date());
        return nowTime + num;
    }

    /**
     * 删除storePath文件或者目录
     * 是文件可以直接删除
     * 是目录，则删除目录下的所有文件
     */
    public static void deleteFilePath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if(fileOrPath.exists()){
            //目录  递归的删除
            if(fileOrPath.isDirectory()){
                File []files = fileOrPath.listFiles();
                for(File file : files){
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static ByteArrayInputStream compressionImg(InputStream inputStream, String suffix){
        try {
            BufferedImage img = Thumbnails.of(inputStream).size(300,300).outputQuality(0.3f).asBufferedImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, suffix, os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch (IOException e){
            log.error("图片压缩错误");
        }
        return null;
    }
}
