 Repaso ADA - Banco

```ada
    Procedure Banco is
        task type Persona;
        arrPersona: array(1..N) of Persona;
        task Caja is
            entry llegadaJubilado(pago: in real, resultado: out resultado);
            entry llegadaNormal(pago: in real, resultado: out resultado);
        end Caja;
        task ResponsableReclamo is
            entry DejarReclamo(reclamo: in reclamo);
        end ResponsableReclamo;

        task body Persona is
            jubilado: boolean;
            pago: real;
            resultado: resultado;
        begin
            jubilado = --SoyJubilado();
            pago = --generarMontoAPagar();
            if(jubilado)then
                select 
                    Caja.llegadJubilado(pago, resultado);
                or delay 15 
                    relcamo = --nuevoReclamo(); 
                    ResponsableInformes.DejarReclamo(reclamo);  
                end select;
            else
                select
                    Caja.llegadaNormal(pago, resultado);
                or delay 15 
                    reclamo = --nuevoReclamo();  
                    ResponsableInformes.DejarReclamo(reclamo); 
                end select;
            end if;
        end Persona;

        task body Caja is

        begin
            loop
                select 
                    accept llegadaJubilado(pago: in real, resultado: out resultado)do
                        resultado = --atenderTramite();
                    end llegadaJubilado;
                or
                    when(llegadaJubilado'count = 0)
                    accept llegadaNormal(pago: in real, resultado: out resultado)do
                        resultado = --atenderTramite();
                    end llegadaNormal;
            end loop;
        end Caja;

        task body ResponsableReclamo is
            reclamos: cola;
        begin
            loop
                accept DejarReclamo(reclamo: in reclamo) do
                    reclamos.push(reclamo);
                end DejarReclamo;
            end loop;
        end;
    Begin
        null
    End Banco;
```