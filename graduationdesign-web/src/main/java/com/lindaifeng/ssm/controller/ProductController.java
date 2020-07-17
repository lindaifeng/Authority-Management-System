package com.lindaifeng.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lindaifeng.ssm.domain.Product;
import com.lindaifeng.ssm.service.IProductService;
import com.lindaifeng.ssm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    //查询产品信息
    @RequestMapping("/findAll.do")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_King"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size,@RequestParam(name = "fuzzyName",required = false,defaultValue = "")String fuzzyName) throws Exception{
        ModelAndView mv = new ModelAndView();
        //判断是乱码 (GBK包含全部中文字符；UTF-8则包含全世界所有国家需要用到的字符。)
        if (!(Charset.forName("GBK").newEncoder().canEncode(fuzzyName))) {
            //转码UTF8
            fuzzyName = new String(fuzzyName.getBytes("ISO-8859-1"), "utf-8");
        }
        System.out.println(fuzzyName);
        List<Product> productList = productService.findAll(page,size,fuzzyName);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("productList",productList);
        mv.addObject("fuzzyName",fuzzyName);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;

    }

    //添加产品信息
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //编辑产品信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/productUpdate.do")
    public String update(Product product)throws Exception{
        productService.update(product);
        return "redirect:findAll.do";
    }
    //删除产品信息
    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception{
        System.out.println(id);
        productService.deleteById(id);
        return "redirect:findAll.do";
    }
    //批量删除产品信息
    @RequestMapping("/deleteByIdStr.do")
    public String deleteByIdStr(@RequestParam(value = "idStr",defaultValue = "",required = false)String idStr) throws Exception {
        if (idStr != null && idStr != "" && idStr.length()>0){
            String[] ids = idStr.split(",");
            for (String id : ids) {
                productService.deleteById(id);
            }
        }
        return "redirect:findAll.do";
    }
}
