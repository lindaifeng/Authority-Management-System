package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {

    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

    //查询所有商品信息
    @Select("select * from product where productName like concat('%',#{fuzzyName},'%')")
    List<Product> findAll(String fuzzyName) throws Exception;


    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);


    @Delete("delete from product where id=#{id}")
    void deleteById(String id);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void update(Product product);
}
