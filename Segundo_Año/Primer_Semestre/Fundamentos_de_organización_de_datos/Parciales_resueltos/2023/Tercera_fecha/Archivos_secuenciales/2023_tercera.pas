program tercera2023;

uses
    sysutils;
const 
    valoralto = 9999;
    dimF = 3;
type

    Producto = record
        cod_p: Integer;
        nombre: String;
        precio: integer;
        stock: Integer;
        stock_min: Integer;
    end;

    venta = record
        cod_p: Integer;
        cantidad: Integer;
    end;

    Maestro = file of Producto;
    Detalle = file of venta;

    vec_detalle = array[1..100] of Detalle;
    vec_registros = array[1..100] of venta;

    Procedure cargarMaestro (var m: Maestro);
    var
        txt:text;
        p: Producto;
    begin
        Assign(m,'productos.dat');
        Assign(txt,'carga_maestro.txt');
        reset(txt);
        rewrite(m);
        while not eof(txt) do begin
            with p do begin
                readln(txt,cod_p,stock,stock_min,precio,nombre);
            end;
            write(m,p);
        end;
        close(m);
        close(txt);
    end;

    Procedure imprimirMaestro (var m: Maestro);
    var
        p: Producto;
    begin
        reset(m);
        while not eof(m)do
        begin
            Read(m,p);
            with p do
            begin
                Writeln('Codigo: ',cod_p,' Nombre: ',nombre,' Precio: ',
                precio,' Stock: ',stock,' Stock Minimo: ',stock_min);
            end;
        end;
        close(m);
    end;

    Procedure cargarUnDetalle (var d: Detalle; nombre: String);
    var
        txt: text;
        v: venta;
    begin
        Assign(d,nombre);
        Assign(txt,'carga_'+nombre+'.txt');
        reset(txt);
        rewrite(d);
        while not eof(txt) do begin
            with v do begin
                readln(txt,cod_p,cantidad);
            end;
            write(d,v);
        end;
        close(d);
        close(txt);
    end;

    Procedure cargarDetalles (var v: vec_detalle);
    var
        i: Integer;
    begin
        for i:=1 to dimF do begin
            cargarUnDetalle(v[i],'det'+IntToStr(i));
        end;
    end;

    Procedure imprimirDetalles (var v: vec_detalle);
    var
        i: Integer;
        r: venta;
    begin
        for i:=1 to dimF do begin
            reset(v[i]);
            while not eof(v[i]) do begin
                read(v[i],r);
                with r do begin
                    writeln('Codigo: ',cod_p,' Cantidad: ',cantidad);
                end;
            end;
            close(v[i]);
        end;
    end;

    Procedure leerDet(var d: Detalle; var r: venta);
    begin
        if not eof(d) then
            read(d,r)
        else
            r.cod_p:=valoralto;
    end;

    Procedure leerMae(var m: Maestro; var p: Producto);
    begin
        if not eof(m) then
            read(m,p)
        else
            p.cod_p:=valoralto;
    end;

    Procedure minimo(var vd: vec_detalle; var vr: vec_registros;var min: venta);
    var
        i,pos: Integer;
    begin
        min.cod_p:=valoralto;
        for i:=1 to dimF do begin
            if(vr[i].cod_p < min.cod_p)then
            begin
                min:=vr[i];
                pos:=i;
            end;
        end;
        if min.cod_p <> valoralto then 
            leerDet(vd[pos],vr[pos]); 
    end;


    Procedure actualizarMaestro(var mae: Maestro; var vd: vec_detalle);
    var
        reg_mae: Producto;
        vr: vec_registros;
        min: venta;
        txt: text;
        i,monto_total:integer;
    begin
        for i := 1 to dimF do 
        begin    
            reset(vd[i]);
            leerDet(vd[i],vr[i]);
        end;
        Assign(txt,'listado_mayor_10000.txt');
        rewrite(txt);
        reset(mae);
        minimo(vd,vr,min);
        leerMae(mae,reg_mae);
        while(min.cod_p <> valoralto)do
        begin
    	    while(min.cod_P<>reg_mae.cod_p)do
                leerMae(mae,reg_mae);
            monto_total:=0;
            while(min.cod_p=reg_mae.cod_p)do
            begin
                monto_total:=monto_total+(min.cantidad*reg_mae.precio);
                reg_mae.stock:=reg_mae.stock-min.cantidad;
                minimo(vd,vr,min);
            end;
            if(monto_total>10000)then
                writeln(txt,reg_mae.cod_p,' ',reg_mae.precio,' ',reg_mae.nombre);
        end;
        close(mae);
        close(txt);
        for i:=1 to dimF do
            close(vd[i]);
    end;



var
    mae: Maestro;
    det: vec_detalle;
begin
    cargarMaestro(mae);
    //imprimirMaestro(mae);
    cargarDetalles(det);
    //imprimirDetalles(det);
    actualizarMaestro(mae,det);
end.
