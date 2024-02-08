package dev.pattern;


import dev.pattern.step01beforeproxy.Hello;
import dev.pattern.step01beforeproxy.HelloTarget;
import dev.pattern.step02proxywithpurejava.HelloUppercaseProxy;
import dev.pattern.step03dynamicproxy.UppercaseHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest {
//    // step01 패키지
//    @Test
//    @DisplayName("Hello 인터페이스를 통해 HelloTarget 객체 사용 테스트")
//    public void helloTargetTest(){
//        HelloTarget helloTarget = new HelloTarget();
////        Hello hello = new HelloTarget();
//
//        // target에 직접 접근
//        assertEquals(helloTarget.sayHello("Lee"), "Hello Lee");
////        assertEquals(hello.sayHello("Lee"), "Hello Lee");
////        assertEquals(hello.sayGoodBye("Lee"), "Bye Lee");
//    }

    // step02 패키지
    @Test
        @DisplayName("순수 Java로 구현한 데코레이터 패턴 기반의 Proxy 객체 생성 및 동작 테스트")
        public void simpleProxyTestWithPureJava() {

            // 참조 변수의 타입은 구현체가 아닌 인터페이스
            Hello proxiedHello = new HelloUppercaseProxy(new HelloTarget());


        // proxy 객체에 의해 대문자로 변환되는 부가기능이 적용되어 결과 값이 반환됨
        assertEquals(proxiedHello.sayHello("Lee"), "HELLO LEE");
    }

//
//    // step03 패키지
//
//    @Test
//    @DisplayName("")
//    public void proxyTestWithDynamicProxy(){
//
//        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
//                getClass().getClassLoader(),
//                new Class[]{Hello.class},
//                new UppercaseHandler(new HelloTarget())
//        );
//
//        assertEquals(proxyiedHello.sayHello("Lee"), "HELLO LEE");
//    }
}
