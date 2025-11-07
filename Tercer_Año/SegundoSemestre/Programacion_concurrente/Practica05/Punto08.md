# Ejercicio 8

```ada
    Procedure Huellas is
        -- task servidor
        task type Servidor;
        arrServidores: array (1 .. 8) of Servidor;

        -- task especialista
        task Especialista is
            entry pedidoHuella(huella: out huella);
            entry resultadoAnalisis(codigo: in integer, valor: in real);
            entry sigo();
        end Especialista;

        -- SERVIDOR
        task body Servidor is
            huella: huella;
            codigo: integer;
            valor: real;
        begin
            loop
                Especialista.pedidoHuella(huella);
                -- Buscar(huella, codigo, valor);
                Especialista.resultadoAnalisis(codigo, valor);
                Especialista.sigo();
            end loop;
        end;

        -- ESPECIALISTA
        task body Especialista is
            test: huella;
            codigoAux: integer;
            codigoMax: integer:= -1;
            valorAux: real;
            valorMax: real:= -1;
        begin
            loop
                test := -- ObtenerHuella(huella);
                for i := 1 to 16 loop
                    select -- hay pedido de huella de un servidor
                        -- le mando la huella al servidor
                        accept pedidoHuella(huella: out huella) do
                            huella := test;
                        end pedidoHuella;
                    or -- hay resultado disponible
                        -- tomo el resultado del analisis
                        accept resultadoAnalisis(codigo: in integer, valor: in real) do
                            codigoAux := codigo;
                            valorAux := valor;
                        end resultadoAnalisis;
                        if valorAux > valorMax then
                            valorMax := valorAux;
                            codigoMax := codigoAux;
                        end if;
                    end select;
                end for;
                for i := 1 to 8 loop
                    accept sigo();
                end loop;
            end loop;
        end Especialista;

    Begin
        null;
    End Huellas;
```