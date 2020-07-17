package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.Member;
import com.lindaifeng.ssm.domain.Orders;
import com.lindaifeng.ssm.domain.Product;
import com.lindaifeng.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders where orderNum like concat('%',#{fuzzyName},'%')")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.lindaifeng.ssm.dao.IProductDao.findById")),
    })
    List<Orders> findAll(String fuzzyName) throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class ,one = @One(select = "com.lindaifeng.ssm.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.lindaifeng.ssm.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.lindaifeng.ssm.dao.ITravellerDao.findByOrdersId")),

    })
    Orders findById(String id) throws Exception;

    @Delete("delete from orders where id=#{id}")
    void deleteById(String id);
    @Delete("delete from order_traveller where orderId=#{id}")
    void deleteOrders_travellerByOrdersId(@Param("id") String id);
    //根据用户id和旅游id删除用户_旅游关联表
    @Delete("delete from orders_traveller where orderId=#{id}")
    void deleteOrders_travellerByOrdersIdAndTravellerId(@Param("id") String id,@Param("travellerId") String travellerId);
}
