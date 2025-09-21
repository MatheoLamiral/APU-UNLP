package org.example;

import java.util.List;

public class HomeWeatherStation implements WeatherData {
    private List<Double> temperaturas;
    private double temperatura;
    private double presion;
    private double radiacionSolar;

    public HomeWeatherStation(List<Double> temperaturas, double temperatura, double presion, double radiacionSolar) {
        this.temperaturas = temperaturas;
        this.temperatura = temperatura;
        this.presion = presion;
        this.radiacionSolar = radiacionSolar;
    }

    public double getTemperatura(){
        return this.temperatura;
    }

    public double getPresion(){
        return this.presion;
    }

    public double getRadiacionSolar(){
        return this.radiacionSolar;
    }


    public List<Double> getTemperaturas(){
        return this.temperaturas;
    }

    public String displayData(){
        return "Temperatura F: " + this.getTemperatura() +
               "Presión atmosf: " + this.getPresion() +
               "Radiación solar: " + this.getRadiacionSolar();
    }
}
