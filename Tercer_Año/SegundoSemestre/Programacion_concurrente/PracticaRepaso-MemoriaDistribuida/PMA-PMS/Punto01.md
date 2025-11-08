# Ejercicio 1 

## PMA

```kotlin
    chan imprimir(documento text, idEmpleado int);
    chan finImpresion[N](documento text);

    Process Empleado [id: 1..100]{
        text documento;
        while (true){
            documento = //generarDocumento();
            send imprimir(documento, id);
            receive finImpresion[id](documento);
        }
    }

    Process Impresora [id: 1..5]{
        text documento;
        int idEmpleado;
        while (true){
            receive imprimir(documento, idEmpleado);
            //imprimirDocumento(documento);
            send finImpresion[idEmpleado](documento);
        }
    }
```

## PMS

```kotlin
    Process Empleado [id: 1..100]{
        text documento;
        while (true){
            documento = //generarDocumento();
            Administrador!pedido(documento, id);
            Impresora[*]?finImpresion(documento);
        }
    }

    Process Administrador {
        pedidos cola;
        int idImpresora;
        int idEmpleado;
        text documento;
        do Empleado?pedido(documento, idEmpleado) =>
            pedidos.push((documento, idEmpleado));
        [] (pedidos.isNotEmpty()); Impresora[*]?libre(idImpresora) =>
            Impresora[idImpresora]!imprimir(pedidos.pop());
    }

    Process Impresora [id: 1..5]{
        text documento;
        int idEmpleado;
        while (true){
            Administrador!libre(id);
            Administrador?imprimir(documento, idEmpleado);
            //imprimirDocumento(documento);
            Empleado[idEmpleado]!finImpresion(documento);
        }
    }
```