package com.lindaifeng.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lindaifeng.ssm.dao.IRoleDao;
import com.lindaifeng.ssm.dao.IUserDao;
import com.lindaifeng.ssm.domain.Role;
import com.lindaifeng.ssm.domain.UserInfo;
import com.lindaifeng.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional

public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //根据用户名查询出用户信息（多表查询，角色和访问权限）
        UserInfo userInfo = null;

            userInfo = userDao.findByUsername(username);
        //获取到角色封装到User类中做角色参数权限（有多个角色，需要自定义一个方法封装成UserDetails中，因UserDetails是接口所以用他的子类SimpleGrantedAuthority封装）
            List<Role> roles = userInfo.getRoles();
            List<SimpleGrantedAuthority> authoritys = getAuthority(roles);

        //处理自己的对象封装成UserDetails对象返回（因为UserDetails是接口需要用他的实体类User返回 ）User封装的参数：用户名，密码，状态，账户是否过期，认证是否过期，账户是否锁定，角色
        User user = new User(userInfo.getUsername(),  userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, authoritys);

        return user;
    }
    //角色封装成UserDetails自定义方法
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        for (Role role:roles){
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    @Override
    public List<UserInfo> findAll(Integer page,Integer size,String fuzzyName){
        PageHelper.startPage(page,size);
        return userDao.findAll(fuzzyName);
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //spring-security.xml配置的加密方式，可通过BCryptPasswordEncoder类实现加密
        //获取userinfo中的密码getPassword()进行加密encode设置回去setPassword存入数据库
       userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findAllRole(String id) throws Exception{
        return roleDao.findNotRoleByUserId(id);
    }

    @Override
    public void relatedRole(String userId, String roleId) throws Exception {
        userDao.relatedRole(userId,roleId);
    }

    @Override
    public void deleteRoleById(String id,String userId) {
        userDao.deleteRoleById(id,userId);
    }


}
