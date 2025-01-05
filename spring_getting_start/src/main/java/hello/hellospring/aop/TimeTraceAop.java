package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect       // AOP 기능 표현
@Component    // 스프링 컨테이너에 빈으로 등록 (SpringConfig @Bean 으로도 가능)
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")   // AOP 적용 범위 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();                // 적용되는 메서드 핵심기능 실행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
