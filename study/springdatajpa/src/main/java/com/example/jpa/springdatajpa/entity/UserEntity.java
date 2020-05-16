package com.example.jpa.springdatajpa.entity;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 10:11
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 10:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String username;    //姓名，username
    private String loginName;   //登录名
    private char sex;   //性别
    private Integer age;    //年龄
}
