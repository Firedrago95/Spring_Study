package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    // 포인트컷 분리
    @Pointcut("execution(* hello.aop.order..*(..))")
    // 반환타입 void, 다른 곳에서 사용하려면 public 사용
    // 메서드명 + 파라미터 = 포인트컷 시그니처
    private void allOrder() {}

    // 포인트컷 시그니처 호출 -> 재사용 가능
    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
