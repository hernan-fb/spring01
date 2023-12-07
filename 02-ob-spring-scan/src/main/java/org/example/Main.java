package org.example;

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
        // Ejemplo 1
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CalculatorService cal = (CalculatorService) context.getBean("calculatorService");

        Integer resultado = cal.sumarUnoyUno();

        System.out.println("La suma de uno y uno dió: "+resultado);

        resultado = cal.sumarUnoyUno();

        System.out.println("La segunda suma de uno y uno dió: "+resultado);
        System.out.println("el constructor se ejecutó una sola vez por usar el mismo bean (chequear en log, pero la segunda que se ve es por el gestorFacturas)");
        // Ejemplo 2
        System.out.println("");
        System.out.println("Llamo a un nuevo bean");
        CalculatorService calculadoraBis = (CalculatorService) context.getBean("calculatorService");
        Integer nuevoResultado = calculadoraBis.sumarUnoyUno();
        System.out.println("Una tercera suma de otro bean da por resultado: "+nuevoResultado);
        if (calculadoraBis==cal) {
            System.out.println("Los dos beans son el mismo objeto");
        }
        else {
            System.out.println("dos beans implican dos llamadas al constructor, porque el scope es prototype (ver beans.xml)");
        }
        System.out.println("Llamo al bean del gestor de facturas");
        GestorFacturas gestor = (GestorFacturas) context.getBean("gestorFacturas");
        if (calculadoraBis==gestor.calcu) {
            System.out.println("La inyección de dependencias puso siempre el mismo singleton porque el scope por defecto es \"singleton\"");
        }
        else {
            System.out.println("La inyección de dependencias está con scope=\"prototyepe\"");
        }
        Integer tercerResultado = gestor.calcu.sumarUnoyUno();
        System.out.println("La cuarta suma sigue siendo la misma: "+ tercerResultado);

        if (calculadoraBis==gestor.calcu) {
            System.out.println("El constructor de calculadora se ejecutó una sola vez, el scope del bean es singleton");
        }
        else {
            System.out.println("El constructor de calculadora se ejecutó tres veces, el scope del bean es prototype");
        }



    }
}
