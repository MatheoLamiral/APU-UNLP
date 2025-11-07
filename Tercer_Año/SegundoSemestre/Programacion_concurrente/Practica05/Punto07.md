# Ejercicio 7

```ada
    Procedure PromedioVector is
    -- task Coordinador
    task type Coordinador is
        entry informe(valor: in real);
    end Coordinador; 

    -- task Worker
    task type worker is
        entry iniciarTrabajo();
    end worker;
    arrWorkers: array (1 .. 10) of worker;

    -- COORDINADOR
    task body Coordinador is
        suma: real := 0;
        promedio: real;
    begin
        for i := 1 to 10 loop
            worker(i).iniciarTrabajo;
        end loop;
        for i := 1 to 10 loop
            accept informe(valor: in real)do
                suma := suma + valor;
            end informe;
        end loop;
        promedio := suma / 1000000;
    end Coordinador;

    -- WORKER
    task body worker is
        valor: real := 0;
        vec: array (1 .. 100000) of real; -- ya viene cargado
    begin
        accept iniciarTrabajo();
        for i := 1 to 100000 loop
            valor := valor + vec(i);
        end loop;
        Coordinador.informe(valor);
    end worker;

    Begin
        null;
    End;
```