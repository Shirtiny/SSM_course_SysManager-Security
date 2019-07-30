package com.SH.Service.Iservice;

import com.SH.Domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IuserService extends UserDetailsService {


    //添加用户
    void addUser(UserInfo userInfo) throws Exception;


    //查询所有用户
    List<UserInfo> selectAllUser() throws Exception;


}
