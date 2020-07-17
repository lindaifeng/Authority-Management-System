package com.lindaifeng.ssm.service;

import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;

import javax.swing.*;
import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    List<Role> findById(String id);

    void deleteById(String roleId);


    List<Permission> findUserByIdAndAllRole(String id);

    void addpermission(String permissionId, String roleId);

    void deleteRole_PermissionById(String roleId);

    void deleteUserAndRoleById(String roleId);

    void deletePermissionById(String permissionId,String roleId);

    List<Role> findRoleByPermissionId(String id);

    List<Permission> findPermissionByRoleId(String id);
}
