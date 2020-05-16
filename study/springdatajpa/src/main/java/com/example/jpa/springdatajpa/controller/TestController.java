package com.example.jpa.springdatajpa.controller;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 10:08
 **/

import com.example.jpa.springdatajpa.entity.UserEntity;
import com.example.jpa.springdatajpa.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 10:08
 **/
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    UserService userService;
    @RequestMapping("/save")
    public String save() {
        UserEntity user = new UserEntity();
        user.setAge(18);
        user.setLoginName("hhy");
        user.setSex('男');
        user.setUsername("dsad");
        userService.save(user);
        return "success~~~~!!!!";
    }
    @RequestMapping("/update")
    public String update(){
        UserEntity user = userService.getById(1);
        userService.update(user);
        return "修改成功";
    }

    @RequestMapping("/findAll")
    public Iterable<UserEntity> selectAll(){
        return userService.findAll();
    }

    @RequestMapping("/delete")
    public String delete(){
        userService.delete(1);
        return "delete success";
    }

}
