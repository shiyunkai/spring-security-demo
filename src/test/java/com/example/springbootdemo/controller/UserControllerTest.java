package com.example.springbootdemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)//如何来运行来运行测试用例
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        // 模拟发送get请求
        String result = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())//期望请求返回的状态码是200
                .andExpect(jsonPath("$.length()").value(3))//期望请求返回的是json数据，长度3个部分,jsonpath详细搜索github
                .andReturn().getResponse().getContentAsString();
        System.out.println("返回值为:"+result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        // 模拟发送get请求
        String result = mockMvc.perform(get("/user/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())//期望请求返回的状态码是200
                .andExpect(jsonPath("$.length()").value(2))//期望请求返回的是json数据，长度3个部分,jsonpath详细搜索github
                .andReturn().getResponse().getContentAsString();//将返回结果转换为字符串

        System.out.println("返回结果为："+result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        // 模拟发送get请求
        String result = mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())//期望请求返回的状态码是200
                //.andExpect(jsonPath("$.length()").value(2))//期望请求返回的是json数据，长度3个部分,jsonpath详细搜索github
                .andReturn().getResponse().getContentAsString();//将返回结果转换为字符串

        System.out.println("返回结果为："+result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        // 模拟发送post请求
        Date date = new Date();
        //String content = "{\"name\":\"xiaobai\",\"password\":null\",\"birthday\":"+date.getTime()+"}";
        String content = "{\"name\":\"\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())//期望请求返回的状态码是200
                .andExpect(jsonPath("$.id").value(1))//期望请求返回的是json数据，内容中包含的id的值是1，,jsonpath详细搜人索github
                .andReturn().getResponse().getContentAsString();//将返回结果转换为字符串

        System.out.println("返回结果为："+result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        // 模拟发送post请求
        Date date = new Date();
        //String content = "{\"name\":\"xiaobai\",\"password\":null\",\"birthday\":"+date.getTime()+"}";
        String content = "{\"id\":1\"xiaozhang\",\"name\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())//期望请求返回的状态码是200
                //.andExpect(jsonPath("$.id").value(1))//期望请求返回的是json数据，内容中包含的id的值是1，,jsonpath详细搜人索github
                .andReturn().getResponse().getContentAsString();//将返回结果转换为字符串

        System.out.println("返回结果为："+result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        // 模拟发送post请求
        Date date = new Date();
        //String content = "{\"name\":\"xiaobai\",\"password\":null\",\"birthday\":"+date.getTime()+"}";
        String content = "{\"id\":1\"xiaozhang\",\"name\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())//期望请求返回的状态码是200
                //.andExpect(jsonPath("$.id").value(1))//期望请求返回的是json数据，内容中包含的id的值是1，,jsonpath详细搜人索github
                .andReturn().getResponse().getContentAsString();//将返回结果转换为字符串

        System.out.println("返回结果为："+result);
    }


}
