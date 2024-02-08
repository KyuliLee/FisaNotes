package dev.syntax.aop.controller;

import org.springframework.stereotype.Controller;

import dev.syntax.aop.model.Coffee;
import lombok.extern.slf4j.Slf4j;
// Before 버전에 있던 log를 proxy로 뺄 것이다.
@Controller
@Slf4j
public class CoffeeController {

    public void getCoffees() {
        log.info("-- CoffeeController에서 getCoffees() 호출되었음. Target method 호출됨");
    }
    public void saveCoffee(Coffee coffee) {

    }
}
// Before
// log를 proxy로 뺄 것이다.
//public class CoffeeController {
//
//    public void getCoffees() {
//        log.info("-- getCoffees() called");
//    }
//
//    public void saveCoffee(Coffee coffee) {
//        log.info("-- saveCoffee() called");
//        log.info("-- coffee: {} ", coffee);
//    }
//
//
//}
