package com.example.springbootdemo.validate.code.image;

import com.example.springbootdemo.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 12:39
 * @Description: 图形验证码类
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
