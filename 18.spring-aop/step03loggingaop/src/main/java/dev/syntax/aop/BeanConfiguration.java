package dev.syntax.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "dev.syntax.aop") // 해당 패키지 내 모든 컴포넌트 스캔
@EnableAspectJAutoProxy // proxy 객체를 내부에서 생성하는 어노테이션. 내부에서 만들어지고 작동하기 때문에 내 눈에 안 보인다.
public class BeanConfiguration {
}
