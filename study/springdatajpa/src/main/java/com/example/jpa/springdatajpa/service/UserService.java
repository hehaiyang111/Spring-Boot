package com.example.jpa.springdatajpa.service;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 10:17
 **/

import com.example.jpa.springdatajpa.entity.UserEntity;
import com.example.jpa.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 10:17
 **/
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    /**
     * update delete 需要绑定事务
     *
     * */
    @Transactional
    public UserEntity save(UserEntity user){
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    /**
     * 查询所有数据
     */
    public Iterable<UserEntity> findAll(){
        return userRepository.findAll();
    }

    /**
     * 根据ID查询
     */
    public UserEntity getById(Integer id){
        Optional<UserEntity> byId = userRepository.findById(id);
        return byId.get();
    }

    @Transactional
    public void update(UserEntity user){
        user.setAge(22);
    }
}
