package com.lindaifeng.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lindaifeng.ssm.domain.Syslog;
import com.lindaifeng.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    @Secured("ROLE_King")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size,@RequestParam(name = "fuzzyName",required = false,defaultValue = "") String fuzzyName){
        ModelAndView mv = new ModelAndView();
        List<Syslog> sysLog = sysLogService.findAll(page,size,fuzzyName);
        PageInfo sysLogs = new PageInfo(sysLog);
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("/deleteSysLog.do")
    public String deleteSysLog()throws Exception{
        sysLogService.deleteSysLog();
        return "redirect:findAll.do";
    }
}
