package com.example.springbootdemo.validate.code.image;

import com.example.springbootdemo.validate.code.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 20:47
 * @Description:
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    // 将图片验证码写到响应流中
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
