package com.example.jpa.springdatajpa.service;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 16:46
 **/

import com.example.jpa.springdatajpa.entity.Student;
import com.example.jpa.springdatajpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 16:46
 **/
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public List<Student> saveAll(List<Student> students){
        return studentRepository.saveAll(students);
    }

    public Student getInfoByName(String name){
        return studentRepository.findByName(name);
    }

    public Student getInfoByNameAndAddress(String name,String address){
        return studentRepository.findByNameAndAddress(name,address);
    }

    public List<Student> getInfoByNameLike(String name){
        return studentRepository.findByNameLike("%" + name +"%");
    }
}
