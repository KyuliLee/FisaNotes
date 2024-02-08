package dev.syntax.aop;

import dev.syntax.aop.controller.CoffeeController;
import dev.syntax.aop.model.Coffee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        CoffeeController coffeeController = ctx.getBean(CoffeeController.class);

//        CoffeeController coffeeController = new CoffeeController();
//        System.out.println("bean 안 만들고 CoffeeController 인스턴스 직접 접근");

        coffeeController.getCoffees();

//        coffeeController.saveCoffee(new Coffee(1, "peppermint tea"));
    }
}
