package org.example;

import org.springframework.cache.interceptor.CacheAspectSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CalculatorService calculadora = (CalculatorService) context.getBean("calculatorService");

        Integer resultado = calculadora.sumarUnoyUno();

        System.out.println("La suma de uno y uno dió: "+resultado);

        resultado = calculadora.sumarUnoyUno();

        System.out.println("La segunda suma de uno y uno dió: "+resultado);

        System.out.println("El constructor se ejecutó una sola vez (chequear en log)");
    }
}
