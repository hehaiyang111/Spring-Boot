package com.example.jpa.springdatajpa.repository;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/13 9:41
 **/

import com.example.jpa.springdatajpa.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/13 9:41
 **/
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
}
