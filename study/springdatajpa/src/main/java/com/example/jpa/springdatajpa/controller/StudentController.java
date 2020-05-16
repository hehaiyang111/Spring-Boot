package com.example.jpa.springdatajpa.controller;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 16:50
 **/

import com.example.jpa.springdatajpa.entity.Student;
import com.example.jpa.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 16:50
 **/
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/saveAll")
    public List<Student> saveAll(){
        Student student1 = new Student();
        student1.setAddress("河南");
        student1.setAge(18);
        student1.setName("hhy");
        student1.setSex('男');

        Student student2 = new Student();
        student2.setAddress("河南");
        student2.setName("lxy");
        student2.setAge(18);
        student2.setSex('女');

        Student student3 = new Student();
        student3.setAddress("河南");
        student3.setName("shr");
        student3.setAge(20);
        student3.setSex('男');

        Student student4 = new Student();
        student4.setAddress("河南");
        student4.setName("mh");
        student4.setAge(20);
        student4.setSex('男');
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        return studentService.saveAll(list);
    }

    /**
     * 通过名字查询
     */
    @RequestMapping("/name/{nameIn}")
    public Student getInfoByName(@PathVariable("nameIn") String name){
        return studentService.getInfoByName(name);
    }

    /**
     * 通过name and address
     */
    @RequestMapping("/nameAndAddress")
    public Student getInfoByName(String name,String address){
        return studentService.getInfoByNameAndAddress(name,address);
    }

    /**
     * 通过名字模糊查询
     */
    @RequestMapping("/nameLike/{nameIn}")
    public List<Student> getInfoByLikeName(@PathVariable("nameIn") String name){
        return studentService.getInfoByNameLike(name);
    }
}
