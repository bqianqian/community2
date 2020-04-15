package com.demo.community2.mapper;

import com.demo.community2.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    //这个注释将参数user对象的属性分别填充到#{}中，实现插入语句
    void insert(User user);

    @Select("select * from user where token=#{token}")
    //如果函数的参数不是类对象，需要加@Param("token")，将值传入#{}中
    User findByToken(@Param("token") String token);
}
