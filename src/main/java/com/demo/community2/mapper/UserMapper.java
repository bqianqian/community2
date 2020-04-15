package com.demo.community2.mapper;

import com.demo.community2.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    //这个注释将参数user对象的属性分别填充到#{}中，实现插入语句
    void insert(User user);
}
