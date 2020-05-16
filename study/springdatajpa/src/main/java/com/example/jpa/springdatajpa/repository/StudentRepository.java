package com.example.jpa.springdatajpa.repository;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 16:42
 **/

import com.example.jpa.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 16:42
 **/
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByName(String name);

    Student findByNameAndAddress(String name,String address);

    List<Student> findByNameLike(String name);
}
