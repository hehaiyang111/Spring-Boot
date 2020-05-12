package com.example.thymeleaf.controller;

import com.example.thymeleaf.dao.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/11 21:43
 **/
@Controller
public class HelloController {
    @RequestMapping("/")
    public String login(){
        System.out.println("进入。。");
        return "login";
    }

    @RequestMapping("/loginForm")
    public String login1(String username,String password){
        System.out.println("登陆页面输入账号密码");
        return "hello";
    }

    @RequestMapping("testParm")
    public String test1(HttpServletRequest request, HttpSession session){
        request.setAttribute("book","Java");
        session.setAttribute("shuju",123456);
        request.getServletContext().setAttribute("ss","sss");
        return "success";
    }

    @RequestMapping("iftest")
    public String test2(WebRequest webRequest, ModelMap modelMap){
        // 将数据保存到request 推荐使用WebRequest
        webRequest.setAttribute("username","Ssssssss",webRequest.SCOPE_REQUEST);
        webRequest.setAttribute("age",123,webRequest.SCOPE_REQUEST);
        modelMap.put("role","admin");
        return "success2";
    }

    @RequestMapping("eachlist")
    public String test3(ModelMap modelMap){
        List<Book> book = new ArrayList<>();
        book.add(new Book(1, "HTML5+CSS3", "张三", "20"));
        book.add(new Book(2, "JavaScript", "李四", "30"));
        book.add(new Book(3, "java编程思想", "王五", "40"));
        modelMap.put("book",book);
        return "success3";
    }
}
