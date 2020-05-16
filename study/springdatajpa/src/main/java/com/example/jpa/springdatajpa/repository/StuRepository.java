package com.example.jpa.springdatajpa.repository;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/13 9:41
 **/

import com.example.jpa.springdatajpa.entity.Stu;
import com.example.jpa.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/13 9:41
 **/
public interface StuRepository extends JpaRepository<Stu,Integer> {
    /**
     * 根据班级名称查询学生列表
     * @param clazzName 班级名称
     * @return 学生列表
     */
    List<Stu> findByClazzName(String clazzName);
    List<Stu> findByClazz_name(String clazzName);

    /**
     *
     * @param clazzName 班级名称
     * @return 根据班级名称查询学生
     */
    @Query("select s from Stu s where s.clazz.name = ?1")
    List<Stu> findStuByClazzName(String clazzName);

    /**
     *
     * @param clazzName 班级名称
     * @return 根据班级名称查询学生的名字和性别
     */
    @Query("select s from Stu s where s.clazz.name=?1")
    List<Stu> findStuNameAndSexClazzName(String clazzName);

    /**
     *
     * @param sex 性别
     * @param clazzName 班级名称
     * @return 根据性别和班级名称查询学生的姓名
     */
    @Query("select s.name from Stu s where s.sex=:sex and s.clazz.name=:clazzName")
    List<String> findNameBySexAndClazzName(char sex,String clazzName);

    /**
     *
     * @param stuName 学生姓名
     * @return 根据学生学名查询班级名称
     */
    @Query("select c.name from Clazz c inner join c.stuList s where s.name=?1")
    String findClazzNameByStuName(String stuName);

    /**
     * 执行更新查询，使用@Query与@Modifying可以执行更新操作
     * 例如根据姓名删除学生
     * @param stuName 姓名
     */
    @Modifying
    @Query("delete from Stu s where s.name = ?1")
    Integer deleteStuByStuName(String stuName);

}
