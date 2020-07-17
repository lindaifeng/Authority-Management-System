package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.Permission;
import com.lindaifeng.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所对应的角色  property对应的是该实体类的属性
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = ("com.lindaifeng.ssm.dao.IPermissionDao.findRoleByPermissionId")))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;
    //根据权限id查询所对应的角色
    @Select("select * from role where id in(select roleId from role_permission where permissionId=#{id})")
    List<Role>findRolesByPermissionId(String id);
    //查询出所有的角色
    @Select("select * from role")
    List<Role> findAll();
    //根据角色id查询对应的角色与对应的资源权限
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = ("com.lindaifeng.ssm.dao.IPermissionDao.findRoleByPermissionId")))

    })
    List<Role> findById(String id);
    //查询用户没有的角色
    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findNotRoleByUserId(String id);

    //查询权限没有的角色
    @Select("select * from role where id not in(select roleId from role_permission where permissionId=#{id})")
    List<Role> findRoleByPermissionId(String id);

    //保存角色信息
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    //根据角色id删除角色
    @Delete("delete from role where id=#{roleId}")
    void deleteById(String roleId);


    //根据角色id删除所用户_角色关联表中的联系
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteUserAndRoleById(String roleId);

    //根据角色id删除所角色_资源权限关联表中的联系
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteRole_PermissionById(String roleId);

}
