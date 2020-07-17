package com.lindaifeng.ssm.controller;

import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;
import com.lindaifeng.ssm.service.IPermissionService;
import com.lindaifeng.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IRoleService roleService;

    List<Permission> permission;
    ModelAndView mv;
    List<Role> role;


    @RequestMapping("/findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_King"})
    public ModelAndView findAll(){
        mv = new ModelAndView();
        permission = permissionService.findAll();

        mv.addObject("permissionList",permission);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id){
        mv = new ModelAndView();
        permission = permissionService.findById(id);
        mv.addObject("permissionList",permission);
        role = roleService.findRoleByPermissionId(id);
        mv.addObject("role",role);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/addRoleByPermissionId.do")
    public ModelAndView addRoleByPermissionId(@RequestParam(name = "permissionId",required = true) String permissionId,@RequestParam(name = "roleId",required = true) String roleId){
        mv = new ModelAndView();
        permissionService.addRoleByPermissionId(permissionId,roleId);

        permission = permissionService.findById(permissionId);
        mv.addObject("permissionList",permission);
        role = roleService.findRoleByPermissionId(permissionId);
        mv.addObject("role",role);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //根据资源权限id-删除对应角色_资源权限
    @RequestMapping("/deletePermissionById.do")
    public String deletePermissionById(@RequestParam(name = "permissionId",required = true) String id){
        permissionService.deleteRole_PermissionByPermissionId(id);
        return "redirect:findAll.do";
    }



    @RequestMapping("/deleteRoleById.do")
    public ModelAndView deleteRoleById(@RequestParam(name = "permissionId",required = true) String permissionId,@RequestParam(name = "roleId",required = true) String roleId){
        mv = new ModelAndView();
        permissionService.deleteRoleById(permissionId,roleId);

        permission = permissionService.findById(permissionId);
        mv.addObject("permissionList",permission);
        role = roleService.findRoleByPermissionId(permissionId);
        mv.addObject("role",role);
        mv.setViewName("permission-show");
        return mv;
    }

}
