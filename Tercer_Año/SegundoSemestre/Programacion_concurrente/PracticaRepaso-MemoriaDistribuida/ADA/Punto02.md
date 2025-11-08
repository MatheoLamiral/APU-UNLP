# Ejercicio 5

```ada
   Procedure CobroDigital is
        task type Persona;
        arrPersonas : array (1 .. P) of Persona;

        task CajaCobro is
            entry llegadaAnciano(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string);
            entry llegadaMenos5(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string);
            entry llegadaNormal(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string);
        end CajaCobro;

        task body Persona is
            cantBoletas: int;
            anciano: boolean;
            dinero: real;
            vuelto: real;
            recibo: string;
        begin
            dinero := --obtenerDinero();
            cantBoletas := --obtenerCantidadBoletas();
            anciano := --esAnciano();
            if anciano then
                CajaCobro!llegadaAnciano(cantBoletas, dinero, vuelto, recibo);
            else if cantBoletas < 5 then
                CajaCobro!llegadaMenos5(cantBoletas, dinero, vuelto, recibo);
            else
                CajaCobro!llegadaNormal(cantBoletas, dinero, vuelto, recibo);
            end if;
        end Persona;

        task body CajaCobro is
        begin
            for i:= 1 to P loop
                select 
                    accept llegadaAnciano(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string) do
                        --procesarPago(cantBoletas, dinero, vuelto, recibo);
                    end llegadaAnciano;
                or when (llegadaAnciano'count = 0) =>
                    accept llegadaMenos5(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string) do
                        --procesarPago(cantBoletas, dinero, vuelto, recibo);
                    end llegadaMenos5;
                or when (llegadaAnciano'count = 0 and llegadaMenos5'count = 0) =>
                    accept llegadaNormal(cantBoletas: in int, dinero: in real, vuelto: out real, recibo: out string) do
                        --procesarPago(cantBoletas, dinero, vuelto, recibo);
                    end llegadaNormal;
                end select;
            end loop;
        end CajaCobro;
   Begin

   End CobroDigital; 
```