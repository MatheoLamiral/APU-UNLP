# Práctica 4 - PMA (Pasaje de Mensajes Asincrónicos)

### Ejercicio 1

- Inciso a
```kotlin
    chan colaBanco(int);
    chan avisoTurno[1..N](text);
    chan avisoFin[1..N](text);

    Process Cliente[id: 1..N]{
        text mensaje;
        send colaBanco(id);
        receive avisoTurno[id](mensaje);
        receive avisoFin[id](mensaje);
    }

    Process Empleado{
        for i: 1..N{
            receive colaBanco(int idCliente);
            send avisoTurno[idCliente]("Es tu turno");
            // Atender al cliente
            send avisoFin[idCliente]("Atención finalizada");
        }
    }
```
- Inciso b
```kotlin
    chan colaBanco(int);
    chan avisoTurno[1..N](text);
    chan avisoFin[1..N](text);

    Process Cliente[id: 1..N]{
        text mensaje;
        send colaBanco(id);
        receive avisoTurno[id](mensaje);
        receive avisoFin[id](mensaje);
    }

    Process Empleado[1..2]{
        for i: 1..N{
            receive colaBanco(int idCliente);
            send avisoTurno[idCliente]("Es tu turno");
            // Atender al cliente
            send avisoFin[idCliente]("Atención finalizada");
        }
    }
```
- Inciso c
```kotlin
    chan colaBanco(int);
    chan avisoTurno[1..N](text);
    chan avisoFin(text);
    chan avisoEmpleados[1..2](int);

    Process Cliente[id: 1..N]{
        text mensaje
        send colaBanco(id);
        receive aviso[id](mensaje);
        receive avisoFin(mensaje);
    }

    Process Coordinador{
        int idCliente;
        int idEmpleado;
        while(true){
            receive disponibles(idEmpleado);
            if(empty(colaBanco)){
                idCliente = -1;
            }else{
                receive colaBanco(idCliente);
            }
            send avisoEmpleados[idEmpleado](idCliente);
        }
    }

    Process Empleado[1..2]{
        int idCliente;
        while(true){
            send disponibles(id);
            receive avisoEmpleados[id](idCliente);
            if(idCliente == -1){
                delay(900) // realiza tareas administrativas por 15 minutos
            }else{
                send avisoTurno[idCliente]("Es tu turno");
                //atender al cliente
                send avisoTurno[idCliente]("Atención finalizada");
            }
        }
    }
```

### Ejercicio 2

```kotlin
    chan esperas[1..5](int);
    chan asignarCaja[1..5](int);
    chan avisoCliente[1..P](text, int);
    chan avisoFin(int);
    chan colaBanco(int);
    chan colaPago(int);

    Process Cliente [id:1..P]{
        text mensaje;
        int idCaja;
        send colaBanco(id); // el cliente llega al banco
        receive asignarCaja[id](idCaja); // espera a que le asignen una caja para usar
        send colaPago[idCaja](id);// va a pagar a la caja asignada
        receive avisoMonto[id](monto); // recibe el monto a pagar
        send pago[idCaja](monto); // realiza el pago
        receive comprobante[id](comprobante); // recibe el comprobante de pago
        send avisoFin(idCaja);// avisa al coordinador que terminó de usar la caja   
    }

    Process Coordinador {
        int esperasCaja[1..5] = ([1..5] 0);
        int colaMasCorta;
        int idCliente;
        int monto;
        text comprobante;
        while (true){
            // if no determinístico, prioridad a los clientes que se van
            if(notEmpty(colaBanco) && empty(avisoFin))-->
                receive colaBanco(idCliente); // espera a que llegue un cliente
                colaMasCorta = calcularEsperaMinima(); // calcula la caja con menos espera
                send asignarCaja[idCliente](colaMasCorta); // asigna la caja al cliente
                esperasCaja[colaMasCorta]++; // incrementa la espera de la caja asignada
            [](notEmpty(avisoFin))-->
                receive avisoFin(idCaja); // espera a que un cliente termine de usar la caja
                esperasCaja[idCaja]--; // decrementa la espera de la caja
            fi
        }
    }

    Process Caja [id:1..5]{
        text mensaje;
        int idCliente;
        while (true){
            receive colaPago[id](idCliente); // espera a que el cliente llegue a pagar
            monto = //calcularMonto(id); // calcula el monto a pagar
            send avisoMonto[idCliente](monto); // envía el monto a pagar
            receive pago[id](monto); // espera a que el cliente realice el pago
            comprobante = generarComprobante(monto); // genera el comprobante de pago
            send comprobante[idCliente](comprobante); // envía el comprobante al cliente
        }
    }
```

### Ejercicio 3

```kotlin
    chan ordenes(text, int);
    chan esperaPedido[1..C](plato);
    chan avisoVendedor(int);
    chan asignarCliente(text, int);
    chan cocina(text, int);

    Process Cliente [id: 1..C] {
        text orden = // generar orden;
        send ordenes(orden, id);
        receive esperaPedido[id](plato);
    }

    Process Coordinador{
        int idCliente;
        text orden;
        while (true) {
            receive avisoVendedor(idVendedor); // espera a que un vendedor esté disponible
            if(empty(ordenes)){
                idCliente = -1;
            }
            else{
                receive ordenes(orden, idCliente);
            }
            send asignarCliente[idVendedor](orden, idCliente);
        }
    }

    Process Vendedor [id: 1..3] {
        int idCliente;
        text orden;
        while (true) {
            send avisoVendedor(id); // aviso al coordinador que estoy disponible
            receive asignarCliente[id](orden, idCliente);
            if(idCliente == -1){
                delay(180); // reponer pack de bebidas
            }
            else{
                send cocina(orden, idCliente);
            }
        }
    }

    Process Cocinero [id: 1..2] {
        int idCliente;
        text orden;
        plato platoPreparado;
        while (true) {
            receive cocina(orden, idCliente);
            platoPreparado = // preparar plato según orden;
            send esperaPedido[ idCliente ](platoPreparado);
        }
    }
```

### Ejercicio 4

- Inciso a
```kotlin
    chan colaLlegada(int);
    chan espera[1..N](int);
    chan usoCabina[1..10](audio);
    chan colaPago(int, int);
    chan esperaFactura[1..N](text);
    chan avisoAtencion();

    Process Cliente [id: 1..N] {
        int idCabina;
        text factura;
        send colaLlegada(id); // se pone el la fila
        send avisoAtencion(); // avisa al empleado que llegó un cliente
        receive espera[id](idCabina); // espera a que le asignen una cabina
        usarCabina(idCabina); // usa la cabina
        send colaPago(id, idCabina); // va a pagar
        send avisoAtencion(); // avisa al empleado que llegó un cliente
        receive esperaFactura[id](factura); // recibe la factura
    }

    Process Empleado {
        boolean cabinas[1..10]; // true = libre, false = ocupada
        int idCliente;
        int idCabina;
        int libres = 10;
        while (true) {
            receive avisoAtencion();
            if(libres > 0 && notEmpty(colaLlegada))-->
                idCabina = obtenerCabinaLibre(cabinas);
                receive colaLlegada(idCliente); // espero a que llegue un cliente
                send espera[idCliente](idCabina); // asigno la cabina
                libres --;
            [](notEmpty(colaPago))-->
                receive colaPago(idCliente, idCabina); // espero a que el cliente termine
                factura = cobrar(); // proceso de cobro
                send esperaFactura[idCliente](factura); // envío la factura
                cabinas[idCabina] = true; // libero la cabina
                libres ++;
            [](libres == 0 && empty(colaPago))-->
                receive colaPago(idCliente, idCabina); // espero a que el cliente termine
                factura = cobrar(); // proceso de cobro
                send esperaFactura[idCliente](factura); // envío la factura
                cabinas[idCabina] = true; // libero la cabina
                libres ++;
            fi
        }
    }
```
- Inciso b
```kotlin
    chan colaLlegada(int);
    chan espera[1..N](int);
    chan usoCabina[1..10](audio);
    chan colaPago(int, int);
    chan esperaFactura[1..N](text);
    chan avisoAtencion();

    Process Cliente [id: 1..N] {
        int idCabina;
        text factura;
        send colaLlegada(id); // se pone el la fila
        send avisoAtencion(); // avisa al empleado que llegó un cliente
        receive espera[id](idCabina); // espera a que le asignen una cabina
        usarCabina(idCabina); // usa la cabina
        send colaPago(id, idCabina); // va a pagar
        send avisoAtencion(); // avisa al empleado que llegó un cliente
        receive esperaFactura[id](factura); // recibe la factura
    }

    Process Empleado {
        boolean cabinas[1..10]; // true = libre, false = ocupada
        int idCliente;
        int idCabina;
        int libres = 10;
        while (true) {
            receive avisoAtencion();
            if(libres > 0 && notEmpty(colaLlegada) && empty(colaPago))-->
                idCabina = obtenerCabinaLibre(cabinas);
                receive colaLlegada(idCliente); // espero a que llegue un cliente
                send espera[idCliente](idCabina); // asigno la cabina
                libres --;
            [](notEmpty(colaPago))-->
                receive colaPago(idCliente, idCabina); // espero a que el cliente termine
                factura = cobrar(); // proceso de cobro
                send esperaFactura[idCliente](factura); // envío la factura
                cabinas[idCabina] = true; // libero la cabina
                libres ++;
            [](libres == 0 && empty(colaPago))-->
                receive colaPago(idCliente, idCabina); // espero a que el cliente termine
                factura = cobrar(); // proceso de cobro
                send esperaFactura[idCliente](factura); // envío la factura
                cabinas[idCabina] = true; // libero la cabina
                libres ++;
            fi
        }
    }
```

### Ejercicio 5
- Inciso a
    ```kotlin
        chan pilaPedidos(text);

        Process Impresora [id: 1..3]{
            text pedido;
            impresion imp;
            while (true){
                receive pilaPedidos(pedido);
                imp = //imprimir(pedido);
            }
        }

        Process Administrativo [id: 1..N]{
            text pedido;
            impresion imp;
            while(true){
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidos(pedido);
            }
        }
    ```

- Incsiso b
    ```kotlin
        chan pilaPedidosUrgentes(text);
        chan pilaPedidosNormales(text);
        chan aviso(int);

        Process Impresora [id: 1..3]{
            text pedido;
            impresion imp;
            while (true){
                receive aviso();
                if (notEmpty(pilaPedidosUrgentes))-->
                    receive pilaPedidosUrgentes(pedido);
                    imp = //imprimir(pedido);
                [] (empty(pilaPedidosUrgentes) && notEmpty(pilaPedidosNormales))
                    receive pilaPedidosNormales(pedido);
                    imp = //imprimir(pedido);
            }
        }

        Process Administrativo [id: 1..N]{
            text pedido;
            impresion imp;
            while(true){
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidosNormales(pedido);
                send aviso();
            }
        }

        Process DirectorOficina{
            text pedido;
            impresion imp;
            while(true){
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidosUrgentes(pedido);
                send aviso();
            }
        }
    ```

- Inciso c
    ```kotlin
        chan pilaPedidos(text);
        chan pilaCoordinador(text, boolean)

        Process Impresora [id: 1..3]{
            text pedido;
            impresion imp;
            boolean sigo;
            while (true){
                receive pilaCoordinador(pedido, sigo);
                if(!sigo){
                    break;
                }
                imp = //imprimir(pedido);
            }
        }

        Process Coordinador{
            text pedido;
            for i: 1..N*10{
                receive pilaPedidos(pedido);
                send pilaCoordinador(pedido, true);
            }
            for i:1..3{
                send pilaCoordinador(pedido,false);
            }
        }

        Process Administrativo [id: 1..N]{
            text pedido;
            impresion imp;
            for i: 1..10 {
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidos(pedido);
            }
        }
    ```
- Inciso d
    ```kotlin
        chan pilaPedidosUrgentes(text, int);
        chan pilaPedidosNormales(text, int);
        chan pilaCoordinadorUrgentes(text, int);
        chan pilaCoordinadorNormales(text, int);
        chan aviso();
        chan avisoImpresora(boolean);

        Process Impresora [id: 1..3]{
            text pedido;
            impresion imp;
            boolean sigo;
            while (true){
                receive avisoImpresora(sigo);
                if(!sigo){
                    berak;
                }
                if (notEmpty(pilaCoordinadorUrgentes))-->
                    receive pilaCoordinadorUrgentes(pedido, sigo);
                [] (empty(pilaCoordinadorUrgentes) && notEmpty(pilaCoordinadorNormales))
                    receive pilaCoordinadorNormales(pedido, sigo);
                imp = //imprimir(pedido);
            }
        }

        Process Coordinador{
            text pedido;
            for i: 1..(N+1)*10{
                receive aviso();
                if (notEmpty(pilaPedidosUrgentes))-->
                    receive pilaPedidosUrgentes(pedido);
                    send pilaCoordinadorUrgentes(pedido);
                [] (empty(pilaPedidosUrgentes) && notEmpty(pilaPedidosNormales))
                    receive pilaPedidosNormales(pedido);
                    send pilaCoordinadorNormales(pedido);
                send avisoImpresora();
            }
            for i:1..3{
                send avisoImpresora(false);
            }
        }

        Process Administrativo [id: 1..N]{
            text pedido;
            impresion imp;
            for i: 1..10 {
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidos(pedido);
            }
        }

        Process DirectorOficina{
            text pedido;
            impresion imp;
            for i: 1..10{
                delay(random.time) // trabaja por un tiempo aleatorio
                pedido = //generarPedido()
                send pilaPedidosUrgentes(pedido);
                send aviso();
            }
        }
    ```
- Inciso d
  - La solución anterior no genera busy waiting 