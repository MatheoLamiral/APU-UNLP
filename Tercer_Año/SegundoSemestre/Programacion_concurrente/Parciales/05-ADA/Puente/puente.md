# Repaso ADA -Puente

```ada
    Procedure Puente is 
        task type Persona;
        arrPersonas: array(1..P) of Persona;
        task Puente is
            entry llegadaJubilado();
            entry llegadaNormal();
            entry salida();
        end Puente;

        task body Persona is
            jubilado: boolean;
        begin
            jubilado = --soyJubilado();
            if(jubilado)then  
                Puente.llegadaJubilado();
            else
                Puente.lledagaNormal();
            end if;
            --cruzarPuente();
            Puente.salir();
        end Persona;

        task body Puente is
            capacidad:integer := 100;
        begin
            loop
                select 
                    when(capacidad > 0)
                    accept llegadaJubilado();
                    capacidad := capacidad - 1;
                or
                    when(llegadaJubilado'count = 0) and (capacidad > 0)
                    accept llegadaNormal();
                    capacidad := capacidad - 1;
                or
                    accept salida();
                    capacidad := capacidad + 1;
            end loop;   
        end Puente;
    Begin 
        null;
    End Puente;
```