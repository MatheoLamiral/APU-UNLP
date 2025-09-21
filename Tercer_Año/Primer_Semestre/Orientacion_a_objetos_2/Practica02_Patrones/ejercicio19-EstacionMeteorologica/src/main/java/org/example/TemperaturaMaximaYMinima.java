package org.example;

public class TemperaturaMaximaYMinima extends Decorator{

    public TemperaturaMaximaYMinima(WeatherData component) {
        super(component);
    }

    public double getMaxima(){
        return this.getTemperaturas().stream()
                                      .mapToDouble(Double::doubleValue)
                                      .max()
                                      .orElse(0.0);
    }

    public double getMinima(){
        return this.getTemperaturas().stream()
                                      .mapToDouble(Double::doubleValue)
                                      .min()
                                      .orElse(0.0);
    }

    @Override
    public String displayData() {
        return super.displayData() + "Maxima: " + this.getMaxima() + "; Minima: " + this.getMinima() + "; ";
    }
}
