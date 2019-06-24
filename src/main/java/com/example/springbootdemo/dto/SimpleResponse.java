package com.example.springbootdemo.dto;

import lombok.Data;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 20:17
 * @Description: 响应的封装
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(){}

    public SimpleResponse(Object content){
        this.content = content;
    }

}
