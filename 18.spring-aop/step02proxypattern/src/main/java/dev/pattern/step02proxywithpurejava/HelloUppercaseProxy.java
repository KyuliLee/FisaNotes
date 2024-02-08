package dev.pattern.step02proxywithpurejava;

import dev.pattern.step01beforeproxy.Hello;
import dev.pattern.step01beforeproxy.HelloTarget;

/*
    HelloTarget의 동작 전후에 별도의 부가 기능을 처리하기 위한 프록시 객체
    데코레이터 패턴을 적용해서 부가 기능이 동작하도록 구현
    부가 기능 - sayHello(), sayGoodBye()가 반환하는 문자를 모두 대문자로 변경하는 처리를 수행
 */

// 순수 Java로 구현한 데코레이터 패턴 기반의 Proxy 객체
// HelloTarget이 실행되기 전에 HelloUppercaseProxy의 메서드가 실행되게 만들자.
public class HelloUppercaseProxy implements Hello {
    // 타겟 클래스의 동작을 프록시 내부에서 조작할 수 있도록 만들자

        Hello hello;

    public HelloUppercaseProxy(Hello hello){
        this.hello = hello;
    }

    // HelloTarget에서 Hello의 추상메서드를 오버라이딩했지만 Proxy 객체에서 Hello의 추상메서드를 동일하게 다시 오버라이딩함
    // 왜?
    // main에서 HelloTarget의 메서드를 사용하기 전/후에 Proxy 객체의 메서드를 먼저/나중에 사용할 것인데
    // main에서 Proxy 객체가 있는지 모르고 사용할 것이다.
    // step01 프로젝트 UserController withAOP = (UserController) context.getBean("proxyFactoryBean"); 문장에서
    // getBean에서 proxyFactoryBean 말고 HelloTarget의 빈을 가져오는데
    // 그 때 proxy 객체에 HelloTarget과 동일한 메서드가 있어야 한다. ????????
    @Override
    public String sayHello(String name) {

        // proxy 객체가 실제 target 객체의 sayHello()를 호출
        String helloMessage = hello.sayHello(name);

        // proxy의 부가 기능 수행 - 모든 문자열을 대문자로 변환 처리
        String uppercased = helloMessage.toUpperCase();

        // 부가 기능을 수행 후에 결과값을 반환
        return uppercased;
    }

    @Override
    public String sayGoodBye(String name) {
        return null;
    }
}
