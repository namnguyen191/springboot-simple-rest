package dev.fullstacknam.restcruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpression {
    // @Before("execution(public void
    // dev.fullstacknam.restcruddemo.dao.*.save(..))")
    // public void beforeSaveInstructor() {
    // System.out.println("Adding instructor...");
    // }

    // @Before("execution(* save(dev.fullstacknam.restcruddemo.entity.Instructor))")
    // public void beforeSaveInstructor() {
    // System.out.println("Adding instructor...");
    // }

    // @Before("execution(* save(dev.fullstacknam.restcruddemo.entity.Instructor,
    // ..))")
    // public void beforeSaveInstructor() {
    // System.out.println("Adding instructor...");
    // }

    // @Before("execution(public void dev.fullstacknam.restcruddemo.dao.*.*(..))")
    // public void beforeSaveInstructor() {
    // System.out.println("Adding instructor...");
    // }

    // @Before("forDAOPackage()")
    // public void beforeSaveInstructor() {
    // System.out.println("Adding instructor...");
    // }

    @Pointcut("execution(* dev.fullstacknam.restcruddemo.dao.*.*(..))")
    public void forDAOPackage() {
    }

    @Pointcut("execution(* dev.fullstacknam.restcruddemo.dao.*.set*(..))")
    public void forDAOPackageSetter() {
    }

    @Pointcut("execution(* dev.fullstacknam.restcruddemo.dao.*.get*(..))")
    public void forDAOPackageGetter() {
    }

    @Pointcut("execution(* dev.fullstacknam.restcruddemo.dao.*.findInstructorById(int))")
    public void forDAOPackageFindInstructorById() {
    }

    @Pointcut("forDAOPackage()  && !(forDAOPackageSetter() || forDAOPackageGetter())")
    public void forDAOPackageExceptSetterAndGetter() {
    }
}
