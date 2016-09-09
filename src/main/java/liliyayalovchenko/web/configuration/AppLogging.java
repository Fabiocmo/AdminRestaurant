package liliyayalovchenko.web.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class AppLogging {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLogging.class);

    @Before("execution(* liliyayalovchenko.dao.hibernate.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        LOGGER.info("************************Get method is starting!************************");
        LOGGER.info("Method " + joinPoint.toString());
        LOGGER.info("Method : " + joinPoint.getSignature().getName());
        LOGGER.info("Arguments : " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* liliyayalovchenko.dao.hibernate.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        LOGGER.info("Method " + joinPoint.toString());
        LOGGER.info("Method : " + joinPoint.getSignature().getName());
        LOGGER.info("Arguments : " + Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("************************Get method finished!************************");
    }

    @AfterThrowing("within(liliyayalovchenko.dao.hibernate.*)")
    public void logExceptions(JoinPoint joinPoint){
        LOGGER.error("Exception thrown in dao implementation Method="+joinPoint.toString());
    }





}