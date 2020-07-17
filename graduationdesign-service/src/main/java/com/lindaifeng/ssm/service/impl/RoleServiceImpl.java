package com.lindaifeng.ssm.service.impl;

import com.lindaifeng.ssm.dao.IPermissionDao;
import com.lindaifeng.ssm.dao.IRoleDao;
import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;
import com.lindaifeng.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleDao roleDao;
    @Autowired
    IPermissionDao permissionDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void deleteById(String roleId) {
        roleDao.deleteById(roleId);
    }

    @Override
    public List<Permission> findUserByIdAndAllRole(String id) {
        return permissionDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addpermission(String permissionId, String roleId) {
        permissionDao.addpermission(permissionId,roleId);
    }

    @Override
    public void deleteRole_PermissionById(String roleId) {
        roleDao.deleteRole_PermissionById(roleId);
    }

    @Override
    public void deleteUserAndRoleById(String roleId) {
        roleDao.deleteUserAndRoleById(roleId);
    }

    @Override
    public void deletePermissionById(String permissionId,String roleId) {
        permissionDao.deleteRole_PermissionByPermissionAndRoleId(permissionId,roleId);
    }

    @Override
    public List<Role> findRoleByPermissionId(String id) {
        return roleDao.findRoleByPermissionId(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return permissionDao.findUserByIdAndAllRole(id);
    }


}
