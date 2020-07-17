package com.lindaifeng.ssm.domain;

import com.lindaifeng.ssm.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//AOP日志类
public class Syslog {

    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date visitTime;//开始时间
    private String visitTimeStr;
    private String username;//当前用户名称
    private String ip;//访问地址ip
    private String url;//访问资源
    private Long executionTime;//执行时长
    private String method;//执行方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        if(visitTime!=null){
            visitTimeStr= DateUtils.date2String(visitTime,"yyyy-MM-dd HH:mm");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
