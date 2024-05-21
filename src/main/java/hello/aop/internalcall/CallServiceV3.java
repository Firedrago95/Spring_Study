package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 가장 추천되는 방식, 내부호출 되지 않도록 클래스를 분리하자
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;
    public void external() {
        log.info("call external");
        internalService.internal();
    }
}
