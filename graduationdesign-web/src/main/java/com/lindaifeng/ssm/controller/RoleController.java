package com.lindaifeng.ssm.controller;

import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;
import com.lindaifeng.ssm.service.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService roleService;
    //查询所有角色


    @RequestMapping("/findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_King"})
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> role = roleService.findAll();
        mv.addObject("roleList",role);
        mv.setViewName("role-list");
        return mv;
    }
    //根据角色id查询对应的角色与对应的资源权限
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
    //根据角色查询对应的资源权限
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permission = roleService.findUserByIdAndAllRole(id);
        mv.addObject("permission",permission);
        List<Role> role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-addpermission");
        return mv;
    }
    //关联角色与资源权限
    @RequestMapping("/addpermission.do")
    public ModelAndView addpermission(@RequestParam(name = "permissionId",required = true) String permissionId,@RequestParam(name = "roleId",required = true) String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        roleService.addpermission(permissionId,id);

        List<Permission> permission = roleService.findPermissionByRoleId(id);
        mv.addObject("permission",permission);
        mv.addObject("role",id);
        mv.setViewName("role-addpermission");
        return mv;
    }
    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
    //根据角色id-删除角色，与关联表
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        roleService.deleteRole_PermissionById(roleId);
        roleService.deleteUserAndRoleById(roleId);
        roleService.deleteById(roleId);
        return "redirect:findAll.do";
    }
    //根据角色id和资源权限id-删除角色_资源权限的关联
    @RequestMapping("/deletePermissionById.do")
    public ModelAndView deletePermissionById(@RequestParam(name = "permissionId",required = true) String permissionId,@RequestParam(name = "roleId",required = true)String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();

        roleService.deletePermissionById(permissionId,roleId);

        List<Role> role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

}
