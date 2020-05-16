package com.example.jpa.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/13 9:36
 **/
@Entity
//@Data
@Table(name="tb_stu")
//@AllArgsConstructor
//@NoArgsConstructor
public class Stu {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Id
    @GeneratedValue
    private Integer id; //主键
    private String name;    //姓名
    private String address;     //地址
    private Integer age;    //年龄
    private char sex;   //性别
    //@JsonIgnore注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉
    @JsonIgnore
    @ManyToOne
    @CreatedBy
    private Clazz clazz;

    public Stu(String name, String address, Integer age, char sex, Clazz clazz) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.clazz = clazz;
    }

    public Stu() {
    }
}
