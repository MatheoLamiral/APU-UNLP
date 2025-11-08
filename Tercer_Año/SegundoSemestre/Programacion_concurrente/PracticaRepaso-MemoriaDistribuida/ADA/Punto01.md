# Ejercicio4 - ADA

```ada
    Procedure CotizacionDolar is
        task type APIBanco;
        arrAPIs : array (1 .. 20) of APIBanco;
        task ActualizarPagina;

        task body APIBanco is
            cotizacion: real;
        begin
            loop
                accept nuevaCotizacion(cotizacion: out real);
                    --cotizacion := obtenerCotizacionDeBanco();
                end accept;
            end loop;
        end;
        
        task body ActualizarPagina is
            cotizaciones : array (1 .. 20) of real;
            cotizacion : real;
        begin
            loop
                for i:= 1 to 20 loop
                    select
                        arrAPIs(i).nuevaCotizacion(cotizacion);
                        cotizaciones(i) := cotizacion;
                    or delay 5 segundos
                        cotizaciones(i) := -1; -- si no llega la cotizacion en 5 segundos, se considera vacia
                end loop;
            end loop;
        end ActualizarPagina;


    Begin
        null;
    End CotizacionDolar;
```