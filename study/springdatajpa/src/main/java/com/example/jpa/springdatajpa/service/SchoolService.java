package com.example.jpa.springdatajpa.service;

import com.example.jpa.springdatajpa.entity.Clazz;
import com.example.jpa.springdatajpa.entity.Stu;
import com.example.jpa.springdatajpa.repository.ClazzRepository;
import com.example.jpa.springdatajpa.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.ClassRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/13 9:57
 **/
@Service
public class SchoolService {
    @Autowired
    StuRepository stuRepository;
    @Autowired
    ClazzRepository clazzRepository;

    //事务管理
    @Transactional
    public void saveClazzAll(List<Clazz> clazzes){
        clazzRepository.saveAll(clazzes);
    }

    @Transactional
    public void saveStuAll(List<Stu> stus){
        stuRepository.saveAll(stus);
    }

    public List<Map<String,Object>> getStuByClazzName(String clazzName){
//        List<Stu> byClazz_name = stuRepository.findByClazz_name(clazzName);
//        List<Stu> byClazz_name = stuRepository.findByClazzName(clazzName);
        List<Stu> byClazz_name = stuRepository.findStuByClazzName(clazzName);
        System.out.println(byClazz_name);
        List<Map<String,Object>> lists = new ArrayList<>();
        for (Stu stu : byClazz_name) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",stu.getName());
            map.put("age",stu.getAge());
            map.put("Sex",stu.getSex());
            lists.add(map);
        }
        return lists;
    }



    public List<String> findNameByClazzNameAndSex(char sex,String clazzName){
        return stuRepository.findNameBySexAndClazzName(sex,clazzName);
    }

    public String findClazzNameByStuName(String stuName){
        return stuRepository.findClazzNameByStuName(stuName);
    }

    public List<Map<String,Object>> find1(String ClazzName){
        List<Stu> stuNameAndSexClazzName = stuRepository.findStuNameAndSexClazzName(ClazzName);
        List<Map<String,Object>> lists = new ArrayList<>();
        for (Stu stu : stuNameAndSexClazzName) {
            Map<String,Object> map = new HashMap<>();
            map.put(stu.getName(),stu.getSex());
            lists.add(map);
        }
        return lists;
    }

    @Transactional
    public Integer deleteStuByStuName(String stuName){
        return stuRepository.deleteStuByStuName(stuName);
    }



}
