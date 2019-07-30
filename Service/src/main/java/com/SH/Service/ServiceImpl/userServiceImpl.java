package com.SH.Service.ServiceImpl;

import com.SH.ConvertUtils.DateAndString;
import com.SH.Dao.IroleDao;
import com.SH.Dao.IuserDao;
import com.SH.Domain.Role;
import com.SH.Domain.UserInfo;
import com.SH.Service.Iservice.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userService")//起个名字，供xml中配置
@Transactional
public class userServiceImpl implements IuserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IuserDao userDao;
    @Autowired
    private IroleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询出对应用户信息
        UserInfo userInfo = userDao.selectUserByName(username);
            //获取用户的角色集合
            List<Role> roles = userInfo.getRoles();
            //遍历权限并放入SimpleGrantedAuthority集合
            SimpleGrantedAuthority authority;
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
                String roleName = role.getRoleName();
                authority = new SimpleGrantedAuthority("ROLE_" + roleName);
                authorities.add(authority);
            }
            //获取用户密码
            String uPwd = userInfo.getPassword();
            //使用Security提供的User类，至少需要用户名、密码、权限
            User user;
            if (uPwd.length() <= 50) {//判断密码长度，以区分是否是加密的密码，因为加密后的密码比较长
                //此时密码未进行加密，所以要加"{noop}"前缀，让框架识别
                user = new User(userInfo.getUsername(), "{noop}" + uPwd, authorities);
            } else {
                //此时密码已经加密(正常情况下密码用户的密码应该不会大于50吧)，加"{bcrypt}"前缀，因为加密方式为bcrypt，让框架识别
                user = new User(userInfo.getUsername(), "{bcrypt}" + uPwd, authorities);
                //若在xml中配置了加密方式，{id}就会失效，只使用配置的加密方式匹配密码，加密类的bean也不能靠前
            }
            //获取用户ip、登录时间
            String ip = request.getRemoteAddr();
//          Date logintime=new Date();
            String logintime=DateAndString.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
            System.out.println("用户："+username+"登陆ip"+ip+"；时间："+logintime);
            //直接返回User类对象，因为User类实现了UserDetails
            return user;

    }
//添加用户，加密测试
    @Override
    public void addUser(UserInfo userInfo) throws Exception {
            //获取用户密码
            String password=userInfo.getPassword();
            //加密密码
            String encode = passwordEncoder.encode(password);
            //修改用户密码
            userInfo.setPassword(encode);
            //打印，以查看加密结果
            System.out.println("原密码："+password+";加密后："+encode+";长度："+encode.length());
            //插入数据库
            userDao.insertOne(userInfo);
            //设为User角色
            roleDao.insertRoleUser(userInfo.getId());


    }



    //查询所有用户
    @Override
    public List<UserInfo> selectAllUser() throws Exception {

        return userDao.selectAllUser();
    }
}
