package com.SH.Controller;


import com.SH.Domain.UserInfo;
import com.SH.Service.Iservice.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(value = "/userController")

/*指定类内方法都默认需要GM的身份
* @PreAuthorize("hasRole('ROLE_GM')")
 * 或
 * @PreAuthorize("hasAuthority('ROLE_GM')")
 */
public class userController {


    @Autowired
    private IuserService userService;


    //添加用户
@RequestMapping(value = "/addUser")

    public String addUser(UserInfo userInfo) throws Exception {
        userService.addUser(userInfo);

        return "redirect:/userController/findAll.action";
    }


    //查询所有用户
    @RequestMapping(value = "/findAll")
/*
 * 指定此方法GM和USER都可以访问
* @PreAuthorize("hasAnyRole('ROLE_GM','ROLE_USER')")
* 或
 * @PreAuthorize("hasAnyAuthority('ROLE_GM','ROLE_USER')")
**/
    public ModelAndView findAllUser() throws Exception {
        List<UserInfo> users = userService.selectAllUser();
        ModelAndView mv=new ModelAndView();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }

    //修改密码（测试）
    @RequestMapping(value = "/changeP")
    //取方法形参中的username，如果传入参数的值与登录用户的值相同，或者拥有GM权限，便可访问
    @PreAuthorize("#username == authentication.principal.username or hasAuthority('ROLE_GM')")
    public String changeP(@P("username") String username) throws  Exception{
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User principal = (User)authentication.getPrincipal();
//        principal.getUsername();
        return "ok";
    }

}
