package com.example.springbootdemo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 15:10
 * @Description:
 */
public class User {

    public interface UserSimpleView{};

    public interface UserDatailView extends UserSimpleView{};

    private String id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank
    private String password;

    @Past//表示必须是一个过去的时间
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDatailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserDatailView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
