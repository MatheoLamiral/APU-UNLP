## Ejercicio 2

- Inciso a 
```ada
    Procedure Banco1 is

        task Empleado is
            entry pedido(pago: in integer; comprobante: out String);
        end Empleado;

        task type Cliente;
        arrClientes: array (1..N) of Cliente;

        task body Empleado is
        begin
            loop 
                accept pedido(pago: in integer; comprobante: out String) do
                    -- procesar pago
                    comprobante := "Comprobante de pago";
                end pedido;
            end loop;
        end;

        task body Cliente is
            pago: integer;
            comprobante: String;
        begin
            pago := --monto a pagar;
            Empleado.pedido(pago, comprobante);
        end;

    Begin
        null;
    End Banco1;
```

- Inciso b 
```ada 
    Procedure Banco2 is

        task Empleado is
            entry pedido(pago: in integer; comprobante: out String);
        end Empleado;

        task type Cliente;
        arrClientes: array (1..N) of Cliente;

        task body Empleado is
        begin
            loop 
                accept pedido(pago: in integer; comprobante: out String) do
                    -- procesar pago
                    comprobante := "Comprobante de pago";
                end pedido;
            end loop;
        end;

        task body Cliente is
            pago: integer;
            comprobante: String;
            tiempo_espera: integer := 600; -- 10 minutos
        begin
            pago := --monto a pagar;
            select
                Empleado.pedido(pago, comprobante);
            or delay tiempo_espera;
                null; -- si pasa el tiempo de espera no hace el pago y se va
            end select;
        end;

    Begin
        null;
    End Banco2;
```

- Inciso c
```ada
    Procedure Banco3 is

        task Empleado is
            entry pedido(pago: in integer; comprobante: out String);
        end Empleado;

        task type Cliente;
        arrClientes: array (1..N) of Cliente;

        task body Empleado is
        begin
            loop 
                accept pedido(pago: in integer; comprobante: out String) do
                    -- procesar pago
                    comprobante := "Comprobante de pago";
                end pedido;
            end loop;
        end;

        task body Cliente is
            pago: integer;
            comprobante: String;
        begin
            pago := --monto a pagar;
            select
                Empleado.pedido(pago, comprobante);
            else
                null; -- si no lo atienden inmediatamente no hace el pago y se va
            end select;
        end;

    Begin
        null;
    End Banco3;
```

Inciso d
```ada
    Procedure Banco4 is

        task Empleado is
            entry pedido(pago: in integer; comprobante: out String);
        end Empleado;

        task type Cliente;
        arrClientes: array (1..N) of Cliente;

        task body Empleado is
        begin
            loop 
                accept pedido(pago: in integer; comprobante: out String) do
                    -- procesar pago
                    comprobante := "Comprobante de pago";
                end pedido;
            end loop;
        end;

        task body Cliente is
            pago: integer;
            comprobante: String;
            tiempo_espera: integer := 600; -- 10 minutos
        begin
            pago := --monto a pagar;
            select
                Empleado.pedido(pago, comprobante);
            or delay tiempo_espera;
                select
                    Empleado.pedido(pago, comprobante); -- si pasa el tiempo reitera el pedido
                else
                    null; -- si luego de reiterar el pedido no lo atienden inmediatamente no hace el pago y se va
            end select;
        end;

    Begin
        null;
    End Banco4;
```