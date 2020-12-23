# Authority-Management-System
CSND企业权限管理系统教程：https://blog.csdn.net/weixin_45019350/article/details/107418838

# 企业权限管理系统
**完整项目github地址：**
[https://github.com/lindaifeng/Authority-Management-System](https://github.com/lindaifeng/Authority-Management-System)
# 一、前端应用

	主要采用AdminLTE开源模板主题工具

## 1.AdminLTE介绍

AdminLTE是一款建立在bootstrap和jquery之上的开源的模板主题工具，它提供了一系列响应的、 可重复使用的组件，并内置了多个模板页面；同时自适应多种屏幕分辨率，兼容PC和移动端。
通 过AdminLTE，我们可以快速的创建一个响应式的Html5网站。AdminLTE框架在网页架构与设计 上，有很大的辅助作用，尤其是前端架构设计师，用好AdminLTE 不但美观，而且可以免去写很大 CSS与JS的工作量。
## 2.GitHub获取AdminLTE
 [https://github.com/almasaeed2010/AdminLTE](https://github.com/almasaeed2010/AdminLTE)
 我们可以从上面网址获取AdminLTE源代码
汉化版：[https://github.com/itheima2017/adminlte2-itheima](https://github.com/itheima2017/adminlte2-itheima)
 ![image](https://github.com/lindaifeng/Authority-Management-System/blob/master/img/20190609103522307.png)
 ![image](https://github.com/lindaifeng/Authority-Management-System/blob/master/img/20190609103725693.png)

# 二、准备工作

## 1.1 MySql数据库搭建
具体流程点击：[《MySql数据库模块》](https://blog.csdn.net/weixin_45019350/article/details/107426891)
![image](https://github.com/lindaifeng/Authority-Management-System/blob/master/img/20200718115049564.png)

## 1.2 idea环境搭建
具体流程点击：[《idea环境搭建模块》](https://blog.csdn.net/weixin_45019350/article/details/107427080)
![image](https://github.com/lindaifeng/Authority-Management-System/blob/master/img/20200718122016185.png)

# 三、 SSM综合练习介绍

## 1. 功能介绍
主要讲解maven工程搭建，以及基于MySql数据库的商品表信息，并完成SSM整合。

## 1.1 商品功能
具体流程点击：[《商品功能模块》](https://blog.csdn.net/weixin_45019350/article/details/107427721)
基于SSM整合基础上完成商品查询，要掌握主面页面main.jsp及商品显示页面product-list.jsp页面的创建，分页查询，模糊查询，关键字搜索的快捷功能实现。
进一步巩固SSM整合，并完成商品添加，修改，删除功能，注意事务操作以及product-add.jsp页面的相关数据生成。

## 1.2 订单功能
具体流程点击：[《订单功能模块》](https://blog.csdn.net/weixin_45019350/article/details/107428672)
订单的查询操作，它主要完成简单的多表查询操作，查询订单时，需要查询出与订单关联的其它表中信息，所以大家一定要了解订单及其它表关联关系。
订单分页查询，我们使用的是mybatis分页插件PageHelper，要掌握PageHelper的基本使用。
订单详情是用于查询某一个订单的信息，与数据的修改和删除

## 1.3 Spring Security 概述

 Spring Security是 Spring 项目组中用来提供安全认证服务的框架，它的使用很复杂，
spring Security的基本操作，掌握spring Security框架的配置及基本的认证与授权操作。
spring Security的讲解在用户管理模块中有讲解，在资源权限模块中也有讲解。

**用户管理模块地址：**
[https://blog.csdn.net/weixin_45019350/article/details/107428820](https://blog.csdn.net/weixin_45019350/article/details/107428820)

## 1.4 用户管理
具体流程点击：[《用户管理模块》](https://blog.csdn.net/weixin_45019350/article/details/107428820)
用户管理中我会介绍基于spring Security的用户登录、退出操作。以及用户查询、添加、详情有等操作。主要会讲解用户角色关联、用户权限关联，这两个操作是为了后续完成授权操作的基础

## 1.5 角色管理
具体流程点击：[《角色管理模块》](https://blog.csdn.net/weixin_45019350/article/details/107428970)
角色管理主要完成角色查询、角色添加，角色删除，分页展示，角色与用户资源权限间的关联操作，其中涉及到多表查询与删除

## 1.6 资源权限管理
具体流程点击：[《资源权限管理模块》](https://blog.csdn.net/weixin_45019350/article/details/107429065)
资源权限管理主要完成查询、添加操作，它的操作与角色管理类似，角色管理以及资源权限管理都是对权限管理的补充。

## 1.7 AOP日志处理
具体流程点击：[《AOP日志处理模块》](https://blog.csdn.net/weixin_45019350/article/details/107429173)
 AOP日志处理，我使用spring AOP切面通过前后置通知来完成系统级别的日志收集。

