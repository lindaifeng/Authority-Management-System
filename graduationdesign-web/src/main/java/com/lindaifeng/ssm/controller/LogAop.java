package com.lindaifeng.ssm.controller;

import com.lindaifeng.ssm.domain.Syslog;
import com.lindaifeng.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;


@Component
@Aspect
public class LogAop {
//    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    ISysLogService sysLogService;
//
//    private Date startTime;//访问时间
//    private Class executionClass;//访问时间
//    private Method executionMethod;//访问的方法

//    //获取访问时间，访问时间，访问的方法
//    @Before("execution(* com.lindaifeng.ssm.controller.*.*(..))")
//    public void doBefore(JoinPoint jp) throws Exception{
//        //获取时间
//        startTime = new Date();
//        //获取访问的类
//        executionClass = jp.getTarget().getClass();
//        //获取访问的方法
//        String methodName = jp.getSignature().getName();//获取方法名称
//        Object[] args = jp.getArgs();//获取参数
//        if (args==null||args.length==0) {//无参数
//            executionMethod = executionClass.getMethod(methodName);//只能获取无参数方法
//        }else {
//            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
//            Class[] classArgs = new Class[args.length];
//            for(int i=0;i<args.length;i++){
//                classArgs[i]=args[i].getClass();
//            }
//            executionMethod = executionClass.getMethod(methodName, classArgs);//获取有参方法
//        }
//    }
//    //获取日志信息中的其他信息，时长，ip，url
//    @After("execution(* com.lindaifeng.ssm.controller.*.*(..))")
//    public void doAfter(JoinPoint jp) throws Exception{
//        //获取时长
//        Long executionTime = new Date().getTime() - startTime.getTime();//后置通知当前时间-前置通知的时间=时长
//
//        //获取url
//        String url="";
//        if (executionClass!=null&&executionMethod!=null&&executionClass!=LogAop.class){
//            //1.获取类上的@RequestMapping（“/xxx”）
//            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
//            if (classAnnotation!=null){
//                String[] classValue = classAnnotation.value();//获取注解中的值
//            //2.获取方法上的@RequestMapping（“/xxx”）
//                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
//                if (methodAnnotation!=null){
//                    String[] methodValue = methodAnnotation.value();//获取注解中的值
//                    url = classValue[0]+methodValue[0];
//
//                    //获取ip(通过request获取，web.xml配置一下在诸如就可获取request)
//                    String ip = request.getRemoteAddr();
//
//                    //获取当前用户名
//                    SecurityContext context = SecurityContextHolder.getContext();
//                    User user = (User) context.getAuthentication().getPrincipal();//从上下文获取当前登录的用户
//                    String username = user.getUsername();//获取用户名称
//
//
//                    //-------------------------------------------------------------
//
//                    //将获取到的日志信息封装到实体类Syslog中
//                    Syslog syslog = new Syslog();
//
//                    syslog.setVisitTime(startTime);
//                    syslog.setUsername(username);
//                    syslog.setUrl(url);
//                    syslog.setIp(ip);
//                    syslog.setExecutionTime(executionTime);
//                    syslog.setMethod("[类名] "+executionClass.getName()+"[方法名] "+executionMethod.getName());
//
//                    sysLogService.addSysLog(syslog);
//                }
//            }
//        }
//
//
//    }


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法
//前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
@Before("execution(* com.lindaifeng.ssm.controller.*.*(..))")
public void doBefore(JoinPoint jp) throws NoSuchMethodException {
    visitTime = new Date();//当前时间就是开始访问的时间
    clazz = jp.getTarget().getClass(); //具体要访问的类
    String methodName = jp.getSignature().getName(); //获取访问的方法的名称
    Object[] args = jp.getArgs();//获取访问的方法的参数

    //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];//遍历参数
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);//获取有参数的方法
    }
}

    //后置通知
    @After("execution(* com.lindaifeng.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class&&clazz!=SysLogController.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    Syslog sysLog = new Syslog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用Service完成操作
                    sysLogService.addSysLog(sysLog);
                }
            }
        }

    }
}
