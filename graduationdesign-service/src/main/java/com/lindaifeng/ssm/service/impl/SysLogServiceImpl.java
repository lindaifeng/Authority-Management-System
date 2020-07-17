package com.lindaifeng.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lindaifeng.ssm.dao.ISysLogDao;
import com.lindaifeng.ssm.domain.Syslog;
import com.lindaifeng.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    ISysLogDao sysLogDao;
    @Override
    public void addSysLog(Syslog syslog) {
        sysLogDao.addSysLog(syslog);
    }

    @Override
    public List<Syslog> findAll(Integer page,Integer size,String fuzzyName) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll(fuzzyName);
    }

    @Override
    public void deleteSysLog() {
        sysLogDao.deleteSysLog();
    }
}
