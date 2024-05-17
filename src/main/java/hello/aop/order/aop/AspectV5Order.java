package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {
    /**
     * 어드바이스 순서 정하려면 -> 클래스 분리해야한다. @Order는 클래스 단위로 적용하기때문
     */

    @Aspect
    @Order(2) // 숫자 작을수록 먼저 실행된다.
    static class LogAspect {

        @Pointcut("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1) // 숫자 작을수록 먼저 실행된다.
    static class TxAspect {

        @Pointcut("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature()); throw e;
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
