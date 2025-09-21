# Ejercicio 3

En el caso de que los lenuajes no manejen excepciones, como el caso de la versión original de C, tenemos que nosotros gestionar esos manejadores, y estar prevenidos a todas las situaciones de error que se podrían presentar, es decir que tenemos que simular el manejo de excecpciones de alguna forma. Hacer esto implica una complejidad muy superior respecto del menejo de errores que proporciona un lenguaje.
Se podría implementar utilizando procesos que funcionen como manejadores, como en este ejemplo:
- Programa en ADA
```ADA
    procedure A is
    x, y, a : integer;
    e1, e   : exception;

    procedure B (x : integer; y : integer) is
        m : integer;
        e : exception;
    begin
        -- ...
        if m <= 0 then
            if m = 0 then
                raise e;
            else
                raise e1;
            end if;
        end if;
        -- ...
        exception
            when e * Manejador3;
    end B;

    begin
    -- ...
    B(x, y);
    exception
        when e * Manejador1;
        when others * Manejador2;
    end A;


    procedure Manejador1 is
    begin
    -- ...
    end;

    procedure Manejador2 is
    begin
    -- ...
    end;

    procedure Manejador3 is
    begin
    -- ...
    end;
```