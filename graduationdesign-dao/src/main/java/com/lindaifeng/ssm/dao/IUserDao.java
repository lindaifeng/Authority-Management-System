package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = ("com.lindaifeng.ssm.dao.IRoleDao.findRoleByUserId"))),
    })
    UserInfo findById(String id) throws Exception;

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = ("com.lindaifeng.ssm.dao.IRoleDao.findRoleByUserId")))
    })
    UserInfo findByUsername(String username);

    @Select("select * from users where username like concat('%',#{fuzzyName},'%')")
    List<UserInfo> findAll(String fuzzyName);

    @Insert("insert into users(username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void relatedRole(@Param("userId")String  userId, @Param("roleId") String roleId) throws Exception;

    @Delete("delete from users_role where roleId=#{id} and userId=#{userId}")
    void deleteRoleById(@Param("id") String id,@Param("userId") String userId);
}
