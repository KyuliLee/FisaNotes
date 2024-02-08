package dev.syntax.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // 1. bean을 관리하는 스프링 컨테이너 생성
        // 생성자의 인수로 bean 설정 정보를 xml 파일의 형태로 전달
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // getBean을 통해 UserController  빈 객체 가져오기
        UserController withoutAOP = (UserController) context.getBean("userController");
//        System.out.println(withoutAOP);

//        // 싱글톤 확인
//        UserController userController2 = (UserController) context.getBean("userController");
//        System.out.println(userController2);

        // AOP를 적용하지 않을 경우 - system 출력 로직이 분리가 어려움
//        withoutAOP.getUsers();
        // AOP를 적용할 경우
        UserController withAOP = (UserController) context.getBean("proxyFactoryBean");
        withAOP.getUsers();

    }
}
