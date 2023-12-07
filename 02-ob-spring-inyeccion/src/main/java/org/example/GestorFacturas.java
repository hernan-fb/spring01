package org.example;

public class GestorFacturas {
    CalculatorService calcu;
    String name;

    public GestorFacturas(String name, CalculatorService atributo) {
        System.out.println("Ejecutando constructor del Gestor de Facturas");
        this.calcu = atributo;
        this.name = name;
    }
}
