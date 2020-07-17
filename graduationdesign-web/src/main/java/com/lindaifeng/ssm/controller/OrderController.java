package com.lindaifeng.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lindaifeng.ssm.domain.Orders;
import com.lindaifeng.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    //查询所有订单信息
    @RequestMapping("/findAll.do")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_King"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size, @RequestParam(name = "fuzzyName",required = false,defaultValue = "")String fuzzyName) throws Exception {
        ModelAndView mv = new ModelAndView();
        //判断是乱码 (GBK包含全部中文字符；UTF-8则包含全世界所有国家需要用到的字符。)
        if (!(Charset.forName("GBK").newEncoder().canEncode(fuzzyName))) {
            //转码UTF8
            fuzzyName = new String(fuzzyName.getBytes("ISO-8859-1"), "utf-8");
        }
        System.out.println(fuzzyName);
        List<Orders> ordersList = ordersService.findAll(page, size,fuzzyName);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    //查询某一订单详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        System.out.println(orders.toString());
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

    //删除某订单 service层删除了关联表信息
    @RequestMapping("/deleteById")
    public String deleteById(String id) throws Exception{
        ordersService.deleteById(id);
        return "redirect:findAll.do;";
    }


    //批量删除产品信息 service层删除了关联表信息
    @RequestMapping("/deleteByIdStr.do")
    public String deleteByIdStr(@RequestParam(value = "idStr",defaultValue = "",required = false)String idStr) throws Exception {
        if (idStr != null && idStr != "" && idStr.length()>0){
            String[] ids = idStr.split(",");
            for (String id : ids) {
                System.out.println(id);
                ordersService.deleteById(id);
            }
        }
        return "redirect:findAll.do";
    }

}
