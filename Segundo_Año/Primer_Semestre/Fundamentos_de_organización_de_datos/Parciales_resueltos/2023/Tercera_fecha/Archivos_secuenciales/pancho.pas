program ej1;

uses
    sysutils;
const 
    lim = 3;
    valoralto = 999;
type
    producto = record
        code : integer;
        nom : string;
        precio : real;
        stockA : integer;
        stockM : integer;
        end;

    venta = record
        code : integer;
        cant : integer;
        end;
    
    detalle = file of venta;
    maestro = file of producto;
    vecArc = array[1..lim] of detalle;
    vecReg = array[1..lim] of venta;

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
                readln(txt,code,stockA,stockM,precio,nom);
            end;
            write(m,p);
        end;
        close(m);
        close(txt);
    end;

    Procedure cargarUnDetalle (var d: detalle; nombre: String);
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
                readln(txt,code,cant);
            end;
            write(d,v);
        end;
        close(d);
        close(txt);
    end;

    Procedure cargarDetalles (var v: vecArc);
    var
        i: Integer;
    begin
        for i:=1 to lim do begin
            cargarUnDetalle(v[i],'det'+IntToStr(i));
        end;
    end;

    procedure leer(var al : detalle; var d : venta);
    begin
        if(not eof(al)) then
            read(al, d)
        else
            d.code := valoralto;
    end;

    procedure minimo(var va : vecArc; var vr : vecReg; var min : venta);
    var 
        aux, i : integer;
    begin
        min.code := valoralto;
        for i := 1 to lim do
            begin
                if(min.code > vr[i].code) then  
                    begin   
                        min := vr[i];
                        aux := i;
                    end;
            end;
        leer(va[aux], vr[aux]);
    end;

    procedure merge(var al : maestro; var va : vecArc; var vr : vecReg; var te : text);
    var 
        act, total, i: integer;
        min : venta;
        aux : producto;
    begin
        reset(al);
        rewrite(te);
        for i := 1 to lim do   
            begin  
                reset(va[i]);
                leer(va[i], vr[i]);
            end;
        minimo(va, vr, min);
        while(min.code <> valoralto) do 
            begin   
                act := min.code;
                total := 0;
                while(act = min.code) do   
                    begin  
                        total := total + min.cant;
                        minimo(va, vr, min);
                    end;
                read(al, aux);
                while(aux.code <> act) do   
                    read(al, aux);
                aux.stockA := aux.stockA - total;
                if(total * aux.precio > 10000) then 
                    begin   
                        writeln(te, aux.code,' ', aux.precio, ' ', aux.nom);
                        writeln(te, aux.stockA,' ', aux.stockM);
                    end;
                seek(al, filepos(al) -1);
                write(al, aux);
            end;
        close(al);
        for i := 1 to lim do   
            close(va[i]);
        close(te);
    end;

var
    al : maestro;
    va : vecArc;
    vr : vecReg;  
    te : text;  
begin
    cargarMaestro(al);
    cargarDetalles(va);
    Assign(te, 'productos.txt');
    merge(al, va, vr,te);
    readln;
end.
