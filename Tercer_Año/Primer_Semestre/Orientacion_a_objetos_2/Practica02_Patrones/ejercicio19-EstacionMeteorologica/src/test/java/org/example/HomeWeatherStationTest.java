package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeWeatherStationTest {
    private Decorator decorator;

    @BeforeEach
    void setUp() {
        this.decorator = new TemperaturaCelcius(new Promedio(new TemperaturaMaximaYMinima(new HomeWeatherStation(List.of(20.0, 22.5, 19.0, 21.5, 25.0),25.0, 1013.25, 200.0))));
    }

    @Test
    void displayData() {
        String expected = "Temperatura C: -3.888888888888889; Presión atmosf: 1013.25; Radiación solar: 200.0; Máxima: 25.0; Mínima: 19.0; Promedio: 21.6; ";
        assertEquals(expected, this.decorator.displayData());
    }
}