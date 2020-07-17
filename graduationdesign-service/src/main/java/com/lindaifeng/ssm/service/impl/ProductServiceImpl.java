package com.lindaifeng.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lindaifeng.ssm.dao.IProductDao;
import com.lindaifeng.ssm.domain.Product;
import com.lindaifeng.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(Integer page,Integer size,String fuzzyName) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll(fuzzyName);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(String id) {
        productDao.deleteById(id);
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }
}
