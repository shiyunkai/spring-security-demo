package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/28 15:04
 * @Description:
 */
@RestController//使类支持RESTful风格
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors().stream());
        }
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        user.setId("1");
        return user;
    }


    @JsonView(User.UserSimpleView.class)//只会返回name,不会返回password
    @GetMapping
    public List<User> query(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @JsonView(User.UserDatailView.class)//会返回name和password
    @GetMapping(value = "{id:\\d+}")
    public User getInfo(@PathVariable String id){
        User user = new User();
        user.setName("xiaobai");
        return user;
    }

    @JsonView(User.UserDatailView.class)//会返回name和password
    @PutMapping(value = "{id:\\d+}")
    public User update(@PathVariable String id){
        User user = new User();
        user.setName("xiaobai");
        return user;
    }

    @JsonView(User.UserDatailView.class)//会返回name和password
    @DeleteMapping(value = "{id:\\d+}")
    public User delete(@PathVariable String id){
        User user = new User();
        user.setName("xiaobai");
        return user;
    }

}
