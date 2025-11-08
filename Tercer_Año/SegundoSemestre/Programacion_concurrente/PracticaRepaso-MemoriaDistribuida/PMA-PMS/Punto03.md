# Ejercicio 3 - PMA

```kotlin
    chan ancianos(int cantBoletas, real dinero);
    chan menos5(int cantBoletas, real dinero);
    chan normal(int cantBoletas, real dinero);
    chan aviso();
    chan resultado[N](real vuelto, string recibo);

    Process Persona [id: 1..P]{
        boolean soyAnciano = //obtenerSiSoyAnciano();
        int cantBoletas = //obtenerCantidadBoletas();
        real dinero = //obtenerDinero();
        real vuelto;
        string recibo;
        if(soyAnciano){
            send ancianos(cantBoletas, dinero, id);
        } else if(dinero < 5){
            send menos5(cantBoletas, dinero, id);
        } else {
            send normal(cantBoletas, dinero, id);
        }
        send aviso();
        receive resultado[id](vuelto, recibo);
    }

    Process CajaCobro{
        int cantBoletas;
        real dinero;
        real vuelto;
        string recibo;
        for i: 1 .. P {
            receive aviso();
            if (not empty(ancianos)) =>
                receive ancianos(cantBoletas, dinero, idPersona);
            [] (empty(ancianos) and not empty(menos5)) =>
                receive menos5(cantBoletas, dinero, idPersona);
            [] (empty(ancianos) and empty(menos5)) =>
                receive normal(cantBoletas, dinero, idPersona);
            vuelto = //procesarPago(cantBoletas, dinero);
            recibo = //generarRecibo(cantBoletas, dinero);
            send resultado[idPersona](vuelto, recibo);
        }
    }