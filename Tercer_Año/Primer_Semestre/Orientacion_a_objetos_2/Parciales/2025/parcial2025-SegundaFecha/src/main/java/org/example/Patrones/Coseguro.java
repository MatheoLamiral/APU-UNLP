package org.example.Patrones;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Coseguro implements ICoseguro {
    private String nombre;
    private double descuento;
    private LocalDate fechaIngreso;
    private double montoCoberturaViajes;

    public Coseguro(String nombre, double descuento, LocalDate fechaIngreso, double montoCoberturaViajes) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.fechaIngreso = fechaIngreso;
        this.montoCoberturaViajes = montoCoberturaViajes;
    }

    @Override
    public double aplicarDescuento(double valor) {
        return valor * this.descuento;
    }

    @Override
    public double getMontoCoberturaViajes() {
        return this.montoCoberturaViajes;
    }

    @Override
    public int getAntiguedad() {
        return (int) ChronoUnit.YEARS.between(this.fechaIngreso, LocalDate.now());
    }
}
