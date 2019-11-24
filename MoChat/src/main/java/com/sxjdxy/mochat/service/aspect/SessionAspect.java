package com.sxjdxy.mochat.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
@Component
public class SessionAspect {

    private static final String TAG = "SessionAspect";

    /**
     * 环绕通知
     * @param joinPoint 切入点
     * @return obj
     */
    public Object around(ProceedingJoinPoint joinPoint){
        //--显式的调用目标方法
        Object obj = null;
        try {
//            Object[] args = joinPoint.getArgs();
//            if (args.length > 0 && args[0] == null){
//                    args[0] = session;
//            }
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

}
