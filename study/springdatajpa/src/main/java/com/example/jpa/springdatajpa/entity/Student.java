package com.example.jpa.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 16:40
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String name;    //姓名
    private String address;     //地址
    private Integer age;    //年龄
    private char sex;   //性别
}
