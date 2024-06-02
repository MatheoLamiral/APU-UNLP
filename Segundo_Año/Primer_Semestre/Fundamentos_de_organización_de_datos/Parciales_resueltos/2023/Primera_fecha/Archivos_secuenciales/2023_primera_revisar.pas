program primera2023;

type
  
    producto = record
      cod:integer;
      nom:string;
      desc:string;
      precio_compra:real;
      precio_venta:real;
      ubicacion_en_depo:integer;
    end;

    ArchivoProductos = file of producto;

    Procedure cargarArchivo(var archivo:ArchivoProductos);
    var
      txt:text;
      reg:producto;
    begin
        Assign(txt,'carga.txt');
        Assign(archivo,'productos.dat');
        reset(txt);
        rewrite(archivo);
        //inicializo la cabecera
        reg.cod:=0;
        write(archivo,reg);
        while(not eof(txt)) do begin
          with reg do begin
            Readln(txt,cod,precio_compra,precio_venta,ubicacion_en_depo,nom);
            Readln(txt,desc);
          end;
          write(archivo,reg);
        end;
        close(txt);
        close(archivo);
    end;

    Procedure imprimirArchivo(var archivo:ArchivoProductos);
    var
      reg:producto;
    begin
        reset(archivo);
        while(not eof(archivo)) do begin
          read(archivo,reg);
          with reg do begin
            writeln('CODIGO: ', cod, ' NOMBRE: ',nom, ' DESCRIPCION: ', desc,
             ' PRECIO DE COMPRA: ', precio_compra:2:2, ' PRECIO DE VENTA: ', precio_venta:2:2,
              ' UBICACION EN DEPOSITO: ', ubicacion_en_depo);
          end;
        end;
        close(archivo);
    end;

    Procedure leerProducto(var reg:producto);
    begin
        with reg do begin
          writeln('Ingrese el codigo del producto');
          readln(cod);
          if(cod <> -1) then begin
            writeln('Ingrese el nombre del producto');
            readln(nom);
            writeln('Ingrese la descripcion del producto');
            readln(desc);
            writeln('Ingrese el precio de compra del producto');
            readln(precio_compra);
            writeln('Ingrese el precio de venta del producto');
            readln(precio_venta);
            writeln('Ingrese la ubicacion en el deposito del producto');
            readln(ubicacion_en_depo);
          end;
        end;
    end;

    Function existeProducto(var archivo:ArchivoProductos; cod:integer):boolean;
    var 
        reg:producto;
    begin
        reset(archivo);
        while(not eof(archivo)) do begin
          read(archivo,reg);
          if(reg.cod = cod) then begin
            existeProducto:=true;
          end;
        end;
        existeProducto:=false;
        close(archivo);
    end;

    Procedure darDeAlta(var arch:ArchivoProductos);
    var
        reg,cab,aux:producto;
    begin
        reset(arch);
        leerProducto(reg);
        if(not(existeProducto(arch,reg.cod))) then
        begin
            //leo la cabecera
            Read(arch,cab);
            if(cab.cod = 0) then
            begin
                seek(arch,filesize(arch));
                Write(arch,reg);
            end
            else begin
                //me posiciono
                seek(arch,cab.cod*(-1));
                //leo lo que hay y lo guardo en aux
                read(arch,aux);
                //vuelvo uno para atras desoues de la lectura
                seek(arch,filepos(arch)-1);
                //escribo el registro nuevo
                write(arch,reg);
                //vuelvo a la cabecera
                seek(arch,0);
                //escribo en la cabecera con aux
                Write(arch,aux);
            end;
        end
        else writeln('El producto ya existe!');
        close(arch);
    end;

var
    archivo:ArchivoProductos;
begin
    cargarArchivo(archivo);
    imprimirArchivo(archivo);
    darDeAlta(archivo);
end.
