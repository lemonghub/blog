package com.xmgl.blog.model;

import java.io.InputStream;

/**
 * @author 张富华
 * @date 2019/1/29
 */
public class ImageHolder {

    /**
     * 图片的名称
     */
    private String imageName;

    /**
     * 图片的输入流
     */
    private InputStream imageInputStream;

    public ImageHolder(String imageName, InputStream imageInputStream) {
        this.imageName = imageName;
        this.imageInputStream = imageInputStream;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImageInputStream() {
        return imageInputStream;
    }

    public void setImageInputStream(InputStream imageInputStream) {
        this.imageInputStream = imageInputStream;
    }

}
