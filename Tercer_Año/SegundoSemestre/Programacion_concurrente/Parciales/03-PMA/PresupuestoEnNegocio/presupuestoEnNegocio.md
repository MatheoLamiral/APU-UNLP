# Repaso PMA - Presupuesto en Negocio

```kotlin
    chan llegada(int idPersona);
    chan atencion[N](int idEmpleado);
    chan listado[N](list<text> productos);
    chan presupuesto[N](real presupuesto);

    Process Persona [id: 1..N]{
        int idEmpleado;
        list<text> productos = //generarListaDeProductos();
        real presupuesto;
        send llegada(id);
        receive atencion[id](idEmpleado);
        send listado[idEmpleado](productos);
        receive presupuesto[id](presupuesto);
    }

    Process Empleado[id: 1..5]{
        int idPersona;
        list<text> productos;
        real presupuesto;
        while true {
            receive llegada(idPersona);
            send atencion[idPersona](id);
            receive listado[id](productos);
            presupuesto = //hacerPresupuesto(productos);
            send presupuesto[idPersona](presupuesto);
        }
    }
```