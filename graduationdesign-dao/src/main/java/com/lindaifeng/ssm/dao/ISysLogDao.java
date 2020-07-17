package com.lindaifeng.ssm.dao;

import com.lindaifeng.ssm.domain.Syslog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {


    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addSysLog(Syslog syslog);

    @Select("select * from syslog where username like concat('%',#{fuzzyName},'%')")
    List<Syslog> findAll(String fuzzyName);

    @Delete("delete from syslog")
    void deleteSysLog();
}
