package com.lindaifeng.ssm.service.impl;

import com.lindaifeng.ssm.dao.IPermissionDao;
import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.service.IPermissionService;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deleteRole_PermissionByPermissionId(String id) {
        permissionDao.deleteRole_PermissionByPermissionId(id);
        permissionDao.deletePermissionById(id);
    }

    @Override
    public List<Permission> findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void addRoleByPermissionId(String permissionId, String roleId) {
        permissionDao.addpermission(permissionId,roleId);
    }

    @Override
    public void deleteRoleById(String permissionId, String roleId) {
        permissionDao.deleteRole_PermissionByPermissionAndRoleId(permissionId,roleId);
    }
}
