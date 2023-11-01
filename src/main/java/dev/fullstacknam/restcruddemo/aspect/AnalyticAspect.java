package dev.fullstacknam.restcruddemo.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import dev.fullstacknam.restcruddemo.entity.Instructor;

@Aspect
@Component
@Order(1)
public class AnalyticAspect {
    @Before("dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageExceptSetterAndGetter()")
    public void runAnalytics() {
        System.out.println("Running analytic...");
    }

    @Before("dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageExceptSetterAndGetter()")
    public void analyzeParameter(JoinPoint joinpoint) {
        var signature = joinpoint.getSignature();
        var args = joinpoint.getArgs();

        System.out.println("Method signature: " + signature);
        System.out.println("Method args: " + args);
        if (args[0] instanceof Instructor) {
            System.out.println((Instructor) args[0]);
        }
    }

    @Around("dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageFindInstructorById()")
    public Object analyzeGetInstructorExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var begin = System.currentTimeMillis();
        var result = proceedingJoinPoint.proceed();
        var end = System.currentTimeMillis();
        System.out.println("Finding instructor took " + (end - begin) + "ms");
        return result;
    }
}
