package com.lindaifeng.ssm.service;

import com.lindaifeng.ssm.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {

    List<Product> findAll(Integer page,Integer size,String fuzzyName) throws Exception;

    void save(Product product) throws Exception;

    void deleteById(String id) throws Exception;

    Product findById(String id) throws Exception;

    void update(Product product);
}
