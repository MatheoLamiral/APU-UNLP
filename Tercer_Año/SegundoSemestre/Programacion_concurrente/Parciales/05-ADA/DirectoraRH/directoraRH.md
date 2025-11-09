# Repaso ADA - Directora de Recursos Humanos

```ada
    Procedure DirectoraRH is

    task type Persona is
        entry entregaEnunciado(examen_recibido: in text);
    end Persona;
    arrPersonas: array(1..20) of Persona;
    task Directora is
        entry datosPersonales(datos: in text);
        entry entregaExamen(examenRealizado: in text; calificacion: out integer);
    end Directora;

    task body Persona is
        datos: text;
        examen: text;
        calificacion: integer;
    begin
        datos := --obtenerDatosPersonales();
        Directora.datosPersonales(datos);
        accept entregaEnunciado(examen_recibido: in text) do
            examen := examen_recibido;
        end entregaEnunciado;
        --resolverExamen(examen);
        Directora.entregaExamen(examen, calificacion);
    end Persona;

    task body Directora is
        examen_generado: text;
    begin 
        examen_generado := --generarExamen();
        for i:= 1 to 20 loop
            accept datosPersonales(datos: in text);
            --registrarPersona(datos);
        end loop;
        for j:= 1 to 20 loop
            arrPersonas(j).entregaEnunciado(examen_generado);
        end loop;
        for k:= 1 to 20 loop
            accept entregaExamen(examenRealizado: in text; calificacion: out integer) do
                calificacion := --corregirExamen(examenRealizado);
            end entregaExamen;
        end loop;
    end Directora;

    Begin 
        null;
    End DirectoraRH;
```