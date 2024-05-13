package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        A target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        A proxy = (A)Proxy.newProxyInstance(
                A.class.getClassLoader(),
                new Class[]{A.class},
                handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        B target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        B proxy = (B)Proxy.newProxyInstance(
                B.class.getClassLoader(),
                new Class[]{B.class},
                handler
        );
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
