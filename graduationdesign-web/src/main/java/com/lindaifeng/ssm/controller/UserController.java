package com.lindaifeng.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lindaifeng.ssm.domain.Role;
import com.lindaifeng.ssm.domain.UserInfo;
import com.lindaifeng.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    ModelAndView mv;

    @RequestMapping("/findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_King"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size,@RequestParam(name = "fuzzyName",required = false,defaultValue = "") String fuzzyName) throws Exception{
        mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page,size,fuzzyName);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> role = userService.findAllRole(id);
        mv.addObject("user",user);
        mv.addObject("role",role);
        mv.setViewName("user-show");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //添加用户角色权限
    @RequestMapping("/relatedRole.do")
    public ModelAndView relatedRole(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "roleId",required = true) String roleId) throws Exception{
        mv = new ModelAndView();

        userService.relatedRole(userId,roleId);

        //重新调用了findByID（）方法显示页面
        UserInfo user = userService.findById(userId);
        List<Role> role = userService.findAllRole(userId);
        mv.addObject("user",user);
        mv.addObject("role",role);
        mv.setViewName("user-show");
        return mv;
    }
    //删除指定用户与指定角色间的关联
    @RequestMapping("/deleteRoleById.do")
    public ModelAndView deleteRoleById(@RequestParam(name = "roleId",required = true) String id,@RequestParam(name = "userId",required = true)String userId) throws Exception{
        mv = new ModelAndView();

        userService.deleteRoleById(id,userId);

        UserInfo user = userService.findById(userId);
        List<Role> role = userService.findAllRole(userId);
        mv.addObject("user",user);
        mv.addObject("role",role);
        mv.setViewName("user-show");
        return mv;
    }
}
