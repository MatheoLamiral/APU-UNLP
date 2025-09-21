package org.example;

public class TemperaturaCelcius extends Decorator {

    public TemperaturaCelcius(WeatherData component) {
        super(component);
    }

    @Override
    public double getTemperatura(){
        return (super.getTemperatura() - 32) / 1.8;
    }

    @Override
    public String displayData() {
        return super.displayData() + "Temperatura C: " + this.getTemperatura() + "; ";
    }
}
