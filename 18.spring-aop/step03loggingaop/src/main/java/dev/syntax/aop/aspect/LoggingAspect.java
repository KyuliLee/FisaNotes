package dev.syntax.aop.aspect;

import dev.syntax.aop.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// Logging에 대한 관심사를 별도의 모듈인 하나의 Aspect로 지정

/*
 * Advice: 횡단 관심사를 구현하는 Aspect의 메서드, 이 코드에서는 logBefore()
 * JoinPoint: 어드바이스를 적용할 메서드
 * Pointcut: 각 어드바이스에서 그 어드바이스를 적용할 메서드를 구별시켜주는 구별자(표현식)
 */

@Aspect
@Component // 스프링 컨테이너에게 LoggingAspect클래스를 하나의 빈으로 등록해서 스프링 AOP 프레임워크가 이 Aspect를 알 수 있도록
@Slf4j // 로깅을 위한 롬복 Annotation
public class LoggingAspect {

// 포인트컷 식 ↓
// execution: 메서드의 이름 패턴을 사용하겠다
// execution( *(Asterisk) ~ : 메서드의 반환 타입은 무엇을 사용하든 상관없음
// dev.syntax.aop.controller : 해당 패키지 하위에 속해야함
// *Controller : 클래스 이름이 ~Controller로 끝나야함
// *( ) : 메서드로 전달되는 인수도 상관없음
// (..) : 대상 메서드가 인수를 받을 수도 있고, 하나 이상의 인수를 받아도 상관없음
    @Before(value = "execution(* dev.syntax.aop.controller.*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("aspect에서 INFO 레벨 로그 출력"); // target method 실행되기 전에 aspect 실행됨
//         호출된 클래스 및 메서드의 이름 로기
        log.info("-- "
            + joinPoint.getTarget().getClass().getSimpleName() + "'s "
                + joinPoint.getSignature().getName()
        );
        log.info("aspect 로그끝");
        
//        // 호출된 메서드의 인수로 전달된 파라미터 값 로깅
//        Object[] args = joinPoint.getArgs();
//        // getArgs() : target method에 전달된 인수를 얻는다.
//
//        for(int i=0; i<args.length; i++){
//            log.info("args[" + i + "] --> " + args[i]);
//        }
    }
}