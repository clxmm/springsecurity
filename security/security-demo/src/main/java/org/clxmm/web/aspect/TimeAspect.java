package org.clxmm.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author clx
 * @date 2020-06-25 15:51
 */
/*@Component
@Aspect*/
public class TimeAspect {


//    @Before()


    @Around("execution(* org.clxmm.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();

        for (Object arg : args) {
            System.out.println("arg: " + arg);
        }


        long vlong = System.currentTimeMillis();

        Object o = pjp.proceed();


        System.out.println("耗时： " + (System.currentTimeMillis() - vlong));

        System.out.println("time aspect end");

        return o;
    }


}
