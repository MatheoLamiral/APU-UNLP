# Ejercicio 6 - ADA

```ada
    Procedure EmpresaIndumentaria is

    task type Sucursal;
    arrSucursales : array (1 .. 100) of Sucursal;
    task Central;

    task body Sucursal is
    begin
        loop
            Central.pedidoProducto(producto);
            cantidad_vendida := --obtenerVentas(producto);
            Central.ventasDeProducto(cantidad_vendida);
            Central.siguienteProducto();
        end loop;
    end Sucursal;

    task body Central is
    begin
        loop
            producto_actual := --generarArticulo();
            cant_ventas_producto_actual := 0;
            for i:= 1 to 200 loop
                select 
                    accept pedidoProducto(producto: out Articulo) do
                        producto := producto_actual;
                    end pedidoProducto;
                or 
                    accept ventasDeProducto(cantidad: in int) do
                        cant_ventas_producto_actual := cant_ventas_producto_actual + cantidad;
                    end ventasDeProducto;
            end loop;
            for i:= 1 to 100 loop
                accept siguienteProducto();
            end loop;
            write("Producto: ", producto_actual.nombre, " Ventas: ", cant_ventas_producto_actual);
        end loop;
    end Central;
    Begin
        null;
    End EmpresaIndumentaria;
```