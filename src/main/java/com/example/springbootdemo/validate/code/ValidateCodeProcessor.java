package com.example.springbootdemo.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/30 17:26
 * @Description: 验证码处理器接口
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";


    /* * @Auther: shiyunkai
     * @Description: 创建验证码
     * @Param: ServletWebRequest是request和response的封装
     * @Date:  17:27 2019/3/30
     * @return:
     **/
    void create(ServletWebRequest request) throws Exception;

    /* * @Auther: shiyunkai
     * @Description: 校验验证码
     * @Param: [request]
     * @Date:  17:29 2019/3/30
     * @return: void
     **/
    void validate(ServletWebRequest request);
}
