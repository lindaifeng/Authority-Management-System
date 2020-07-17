package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {
    //根据角色id查出所有的权限
    @Select("select * from permission")
    List<Permission> findAll();

    //根据角色id查询对应权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByPermissionId(String roleId);

    //根据角色id查出没有的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findUserByIdAndAllRole(String id);


    //根据权限id查出所有权限并根据权限id查出所对应的角色
    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "permissionName",property = "permissionName"),
            @Result(column = "url",property = "url"),
            @Result(column = "id",property = "roles",javaType = List.class,many = @Many(select =("com.lindaifeng.ssm.dao.IRoleDao.findRolesByPermissionId")))
    })
    List<Permission> findById(String id);

    //添加权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    //关联角色_权限表
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addpermission(@Param("permissionId") String permissionId, @Param("roleId") String roleId);



    //根据角色与权限id删除角色_权限表
    @Delete("delete from role_permission where permissionId=#{permissionId} and roleId=#{roleId}")
    void deleteRole_PermissionByPermissionAndRoleId(@Param("permissionId") String permissionId,@Param("roleId") String roleId);


    //根据权限id删除对应权限
    @Delete("delete from permission where id=#{id}")
    void deletePermissionById(String id);

    //根据权限id删除对应角色_权限
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteRole_PermissionByPermissionId(String id);

}
