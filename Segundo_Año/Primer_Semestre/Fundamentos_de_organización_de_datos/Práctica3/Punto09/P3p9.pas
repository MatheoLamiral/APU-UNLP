Program P3p9;
const
    valoralto = -1;
type

    producto = record
        cod:integer;
        nom:string;
        precio:real;
        stock_act:integer;
        stock_min:integer;
    end;

    venta = record
        cod:integer;
        cant_unidades:integer;
    end;

    ArchivoMaestro = file of producto;
    ArchivoDetalle = file of venta;

    Procedure crearMaestro(var mae:ArchivoMaestro);
    var
        txt:text;
        p:producto;
    begin 
        Assign(txt,'maestro.txt');
        Assign(mae,'archivoProductos');
        reset(txt);
        rewrite(mae);
        while(not eof(txt))do
        begin
            with p do
            begin 
                Readln(txt,p.cod,p.precio,p.stock_act,p.stock_min,p.nom);
                Write(mae,p);
            end;
        end;
        close(mae);
        close(txt);
    end;

    Procedure crearDetalle(var det:ArchivoDetalle);
    var
        txt:text;
        v:venta;
    begin
        Assign(txt,'detalle.txt');
        Assign(det,'archivoDetalle');
        reset(txt);
        rewrite(det);
        while(not eof(txt))do
        begin
            with v do
            begin 
                Readln(txt,v.cod,v.cant_unidades);
                Write(det,v);
            end;
        end;
        close(det);
        close(txt);
    end;

    Procedure imprimirMaestro(var arch:ArchivoMaestro);
    var
        p:producto;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,p);
            writeln('NOMBRE:',p.nom,', CODIGO:',p.cod,', PRECIO:',p.precio:0:2,
                        ', STOCK_ACT:',p.stock_act,', STOCK_MIN:',p.stock_min);
        end;
        close(arch);
    end;

    Procedure imprimirDetalle(var arch:ArchivoDetalle);
    var
        v:venta;
    begin
        writeln('***ARCHIVO DETALLE***');
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,v);
            writeln('CODIGO:',v.cod,',UNIDADES_VENDIDAS:',v.cant_unidades);
        end;
        close(arch);
    end;

    Procedure leerDetalle(var arch:ArchivoDetalle;var v:venta);
    begin
        if(not eof(arch))then
            Read(arch,v)
        else
            v.cod:=valoralto;
    end;

     Procedure leerMestro(var arch:ArchivoMaestro;var p:producto);
    begin
        if(not eof(arch))then
            Read(arch,p)
        else
            p.cod:=valoralto;
    end;
    {Si el registro pudiese ser actualizado por 0 o 1 registro del detalle en lugar
    de 0 1 o más, cambiaria que en lugr de recorrer todo el detalle cada vez que avanzo
    en el maestro, avanzaria en el detalle solo hasta encontrar un registro igual}
    Procedure actualizarMaestro(var det:ArchivoDetalle;var mae:ArchivoMaestro);
    var
        p:producto;
        v:venta;
        cant_act:integer;
    begin
        reset(mae);
        reset(det);
        while(not eof(mae))do
        begin
            Read(mae,p);
            cant_act:=0;
            while(not eof(det))do 
            begin
                Read(det,v);
                if(p.cod=v.cod)then cant_act:= cant_act + v.cant_unidades;
            end;
            seek(det,0);
            if(cant_act>0)then
            begin
                p.stock_act:=p.stock_act - cant_act;
                seek(mae,filepos(mae)-1);
                Write(mae,p);
            end;
        end;
        writeln('Archivo maetsro actualizado!');
        close(mae);
        close(det);
    end;

var 
    maestro:ArchivoMaestro;
    detalle:ArchivoDetalle;
BEGIN
    crearMaestro(maestro);
    crearDetalle(detalle);
    imprimirMaestro(maestro);
    imprimirDetalle(detalle);
    actualizarMaestro(detalle,maestro);
    imprimirMaestro(maestro);
END.