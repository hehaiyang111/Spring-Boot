package com.example.jpa.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/13 9:38
 */
@Entity
//@Data
@Table(name="tb_clazz")
//@AllArgsConstructor
//@NoArgsConstructor
public class Clazz {
    @Id
    @GeneratedValue
    private Integer id; //主键
    private String name;    //班级名称
    //@JsonIgnore注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "clazz")
    private List<Stu> stuList = new ArrayList<>();

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

    public List<Stu> getStuList() {
        return stuList;
    }

    public void setStuList(List<Stu> stuList) {
        this.stuList = stuList;
    }

    public Clazz(String name) {
        this.name = name;
    }

    public Clazz(){

    }
}
