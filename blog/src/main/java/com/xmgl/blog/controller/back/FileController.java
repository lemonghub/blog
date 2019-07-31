package com.xmgl.blog.controller.back;

import com.xmgl.blog.util.ImageUtil;
import com.xmgl.blog.util.ServerResponse;
import com.xmgl.blog.util.ftp.FtpUtil;
import com.xmgl.blog.util.ftp.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张富华
 * @date 2019/1/29
 */
@RequestMapping("file")
@Controller
public class FileController {


    @RequestMapping("upload")
    @ResponseBody
    public ServerResponse<Map<String, String>> uploadImg(MultipartFile file){
        if(file == null){
            return ServerResponse.createByFailure("未选择图片");
        }
        Map<String, String> map = new HashMap<>();
        try {
//            imageHolder = new ImageHolder(file.getOriginalFilename(), file.getInputStream());
            String newPicName = UploadUtil.generateRandomFileName(file.getOriginalFilename());
            String newPath = UploadUtil.generateRandomDir(newPicName);
            String suffix = newPicName.substring(newPicName.lastIndexOf(".") + 1);
            String imageAddr = FtpUtil.pictureUpload(newPicName, newPath, file.getInputStream());
            if(imageAddr == null){
                return ServerResponse.createByFailure("原图上传失败，请重试");
            }
            //获取压缩过的文件流
            ByteArrayInputStream compressInputStream = ImageUtil.compressionImg(file.getInputStream(), suffix);
            String compressImgName = newPicName.substring(0, newPicName.lastIndexOf(".")) + "_ys." + suffix;
            String compressImgAddr = FtpUtil.pictureUpload(compressImgName, newPath, compressInputStream);
            if(compressImgAddr == null){
                return ServerResponse.createByFailure("缩略图上传失败，请重试");
            }
            map.put("httpAddr", imageAddr);
            map.put("originName", file.getOriginalFilename());
            return ServerResponse.createBySuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("originName", file.getOriginalFilename());
            //上传图片失败
            return ServerResponse.createByFailure(map ,"图片上传失败，请重试");
        }
    }


}
