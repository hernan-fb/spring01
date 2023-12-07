package org.example;

import org.springframework.stereotype.Component;

@Component
public class GestorFacturas {
    CalculatorService calcu;

    public GestorFacturas(CalculatorService atributo) {
        System.out.println("Ejecutando constructor del Gestor de Facturas");
        this.calcu = atributo;
    }
}
