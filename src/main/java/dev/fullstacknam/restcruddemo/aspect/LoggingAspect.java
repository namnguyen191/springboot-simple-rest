package dev.fullstacknam.restcruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import dev.fullstacknam.restcruddemo.entity.Instructor;

@Aspect
@Component
@Order(0)
public class LoggingAspect {
    @Before("dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageExceptSetterAndGetter()")
    public void logToCloud() {
        System.out.println("Execution started... Logging to cloud...");
    }

    @After("dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageFindInstructorById()")
    public void logToCloudAfterTriggerFindAnInstructor() {
        System.out.println("Someone was trying to find an instructor");
    }

    @AfterReturning(pointcut = "dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageFindInstructorById()", returning = "result")
    public void logToCloudAfterSuccessfullyFindAnInstructor(JoinPoint joinPoint, Instructor result) {
        var signature = joinPoint.getSignature().toShortString();
        System.out.println("Executing " + signature);
        System.out.println("Successfully found an instructor: " + result);
        result.setName("My name has been modified");
    }

    @AfterThrowing(pointcut = "dev.fullstacknam.restcruddemo.aspect.AOPExpression.forDAOPackageFindInstructorById()", throwing = "err")
    public void logToCloudAfterFailingToFindAnInstructor(JoinPoint joinPoint, Throwable err) {
        System.out.println("Finding instructor " + joinPoint.getArgs()[0]);
        System.out.println("Running into error: " + err.getMessage());
    }
}
