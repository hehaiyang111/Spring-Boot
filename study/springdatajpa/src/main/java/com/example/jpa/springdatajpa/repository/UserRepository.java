package com.example.jpa.springdatajpa.repository;

import com.example.jpa.springdatajpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 10:14
 **/

/**
 * UserEntity 对应的实体类
 * Integer 实体类中主键的类型
 */
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
}
