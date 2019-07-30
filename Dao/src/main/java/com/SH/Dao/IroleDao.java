package com.SH.Dao;

import com.SH.Domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IroleDao {


    //根据用户id查询角色集
    @Select("select * from role where id in(select roleId from user_role where userId=#{userid})")
    List<Role> selectByUserid(String userid);

    //为注册用户设置默认角色User
    @Insert("insert into user_role(userId,roleId) values(#{userid},2)")
    void insertRoleUser(String userid);
}
