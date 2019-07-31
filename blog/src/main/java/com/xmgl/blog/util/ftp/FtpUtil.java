package com.xmgl.blog.util.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 张富华
 * @date 2019/2/11
 */
@Component
public class FtpUtil {

    private static final Logger log = LoggerFactory.getLogger(FtpUtil.class);

    private static String ftpAddress;

    private static String ftpPort;

    private static String ftpUsername;

    private static String ftpPassword;

    private static String basePath;

    //图片的前缀
    private static String prefix;

    //product json存储地址
    private static String jsonPrefix;

    @Value("${ftp.address}")
    public void setFtpAddress(String ftpAddress) {
        FtpUtil.ftpAddress = ftpAddress;
    }

    @Value("${ftp.port}")
    public void setFtpPort(String ftpPort) {
        FtpUtil.ftpPort = ftpPort;
    }

    @Value("${ftp.username}")
    public void setFtpUsername(String ftpUsername) {
        FtpUtil.ftpUsername = ftpUsername;
    }

    @Value("${ftp.password}")
    public void setFtpPassword(String ftpPassword) {
        FtpUtil.ftpPassword = ftpPassword;
    }

    @Value("${ftp.prefix}")
    public void setPrefix(String prefix) {
        FtpUtil.prefix = prefix;
    }


    @Value("${ftp.basePath}")
    public void setBasePath(String basePath) {
        FtpUtil.basePath = basePath;
    }

    @Value("${ftp.productjson.prefix}")
    public void setJsonPrefix(String jsonPrefix) {
        FtpUtil.jsonPrefix = jsonPrefix;
    }

    /**
     *图片上传
     * @param pictureName 图片名称
     * @param picturePath 地址
     * @param inputStream 图片输入流
     * @return
     */
    public static String pictureUpload(String pictureName, String picturePath, InputStream inputStream){
        String picHttpPath = null;
        picturePath = "product/img/" + picturePath;
        try {
            boolean flag = uploadFile(picturePath, pictureName, inputStream);
            if(!flag){
                return null;
            }
            picHttpPath = prefix + picturePath + "/" + pictureName;
            return picHttpPath;
        }catch (Exception e){
            log.error("图片上传失败", e);
            return null;
        }
    }

    /**
     * 将json格式的商品存到ftp
     * @param productId 商品id
     * @param productJson josn
     * @return
     */
    public static String productJsonUpload(Long productId, String productJson){
        String picHttpPath = null;
        String fileName = System.currentTimeMillis() + ".txt";
        String productJsonPath = "json/" + productId;
        try {
            boolean flag = uploadFile(productJsonPath, fileName, new ByteArrayInputStream(productJson.getBytes("GBK")));
            if(!flag){
                log.error("上传失败");
                return null;
            }
            picHttpPath = jsonPrefix + productJsonPath + "/" + fileName;
            return picHttpPath;
        }catch (Exception e){
            log.error("图片上传失败", e);
            return null;
        }
    }


    private static boolean uploadFile(String filePath, String fileName, InputStream inputStream){
        FTPClient ftp = new FTPClient();
        try{
            int replay;
            log.info("连接ftp...");
            ftp.connect(ftpAddress, Integer.parseInt(ftpPort));
            ftp.login(ftpUsername, ftpPassword);
            //获取回应状态码
            replay = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replay)){
                ftp.disconnect();
                return false;
            }
            //切换到上传目录
            if(!ftp.changeWorkingDirectory(basePath + filePath)){
                ftp.setControlEncoding("GBK");
                //目录不存在则创建目录
                String []dirs = filePath.split("/");
                String tempPath = basePath;
                for(String dir : dirs){
                    if(dir == null || "".equals(dir)){
                        continue;
                    }
                    tempPath += "/" + dir;
                    if(!ftp.changeWorkingDirectory(tempPath)){
                        if(!ftp.makeDirectory(tempPath)){
                            return false;
                        }else{
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //允许被动连接 访问远程ftp
            ftp.enterLocalPassiveMode();
            //上传文件
            if(!ftp.storeFile(fileName, inputStream)){
                return false;
            }
            inputStream.close();
            ftp.logout();
        }catch (Exception e){
            log.error("上传失败", e);
            throw new RuntimeException("上传失败");
        }finally {
            if(ftp.isConnected()){
                try{
                    ftp.disconnect();
                    log.info("断开ftp连接");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }



}
