# Repaso PMS - Estación de servicio

```kotlin
    Process Cliente[id: 1..N]{
        text tipo;
        real cantidad;
        Administrador!llegada(id);
        Empleado?atencion();
        Empleado!carga(tipo, cantidad);
        Empleado?termino();
    }

    Process Administrador{
        cola clientes;
        int idCliente;
        int faltan = N;
        do (faltan > 0); Cliente[*]?llegada(idCliente) =>
            clientes.push(idCliente);
        [] (clientes.isNotEmpty); Empleado?libre() =>
            Empleado!siguiente(clientes.pop());
            faltan --;
    }

    Process Empleado{
        int idCliente;
        text tipo;
        real cantidad;
        for i: 1..N{
            Administrador!libre();
            Administrador?siguiente(idCliente);
            Cliente[idCliente]!atencion();
            Cliente[idCliente]?carga(tipo, cantidad);
            //cargarTipoYCantidadSolicitada(tipo, cantidad);
            Cliente[idCliente]!termino();            
        }
    } 
```