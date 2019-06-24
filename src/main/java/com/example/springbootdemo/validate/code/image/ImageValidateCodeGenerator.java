package com.example.springbootdemo.validate.code.image;

import com.example.springbootdemo.config.properties.SecurityProperties;
import com.example.springbootdemo.validate.code.ValidateCode;
import com.example.springbootdemo.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 15:15
 * @Description:
 */
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;


    /**
     * 生成随机背景条纹
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        // 先从请求中获取验证码的宽高信息，如果请求中没有，则从配置文件中读取
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, 60);
    }
}
