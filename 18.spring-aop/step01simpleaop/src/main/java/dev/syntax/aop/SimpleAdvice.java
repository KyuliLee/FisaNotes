package dev.syntax.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

@Slf4j
public class SimpleAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    // MethodBeforeAdvice : UserController의 getUsers() 같은 target method가
    // 실제로 호출되기 전에 다른 내용을 미리 처리하는 기능을 제공하는 인터페이스

    // AfterReturningAdvice : 대상 메서드의 수행이 완료된 후에 호출되도록 동작하는 인터페이스
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable{
        log.info("-- before");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("-- after: {}", method);
    }
}
