package org.example;

public class Promedio extends Decorator{

    public Promedio(WeatherData component) {
        super(component);
    }

    public double getPromedio(){
        return this.getTemperaturas().stream()
                                     .mapToDouble(Double::doubleValue)
                                     .average()
                                     .orElse(0.0);
    }

    @Override
    public String displayData() {
        return super.displayData() + "Promedio: " + this.getPromedio() + "; ";
    }
}

