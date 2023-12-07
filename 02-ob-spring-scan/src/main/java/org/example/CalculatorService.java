package org.example;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    public CalculatorService() {
        System.out.println("Ejecutando el constructor de calculator service");
    }
    public Integer sumarUnoyUno() {
        return 2;
    }

}
