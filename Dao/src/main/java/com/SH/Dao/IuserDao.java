package com.SH.Dao;

import com.SH.Domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IuserDao {

//按照用户名查找单个用户，验证登录
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.SH.Dao.IroleDao.selectByUserid"))
    })
    UserInfo selectUserByName(String username);


    //查询所有用户
    @Select("select * from users")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.SH.Dao.IroleDao.selectByUserid"))
    })
    List<UserInfo> selectAllUser() throws Exception;

    //添加用户(没有角色)
    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})" )
    void insertOne(UserInfo userInfo) throws Exception;


}
