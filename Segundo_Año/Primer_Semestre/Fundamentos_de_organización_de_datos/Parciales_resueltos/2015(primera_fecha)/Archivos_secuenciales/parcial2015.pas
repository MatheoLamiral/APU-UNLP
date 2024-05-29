Program parcial;
Uses
 sysUtils;
const
    dimF =  3;
    valorAlto = 9999;
type 
    Producto = record
        codp:integer;
        nom:string;
        desc:string;
        cod_barra:string;
        cat:string;
        stock:integer;
        stock_min:integer;
    end;

    Pedido = record
        codp:integer;
        cant:integer;
        desc:string;
    end;

    ArchivoMaestro = file of Producto;
    ArchivoDetalle = file of Pedido;

    vecDet = array [1 .. dimF] of ArchivoDetalle;
    vecReg = array [1 .. dimF] of Pedido;

    //CREAR MAESTRO
    procedure crearMastro(var mae:ArchivoMaestro);
    var
        txt:text;
        p:Producto;
    begin
        Assign(mae,'archivoMaestro.dat');
        Assign(txt,'carga_maestro.txt');
        rewrite(mae);
        reset(txt);
        while not eof(txt)do
        begin
            with p do
            begin
                Readln(txt,p.codp,p.stock,p.stock_min,p.nom);
                Readln(txt,p.desc);
                Readln(txt,p.cod_barra);
                Readln(txt,p.cat);
            end;
            Write(mae,p);
        end;
        close(mae);
        close(txt);
    end;

    //CREAR UN DETALLE
    procedure creaarUnDetalle(var det:ArchivoDetalle;nombre:string);
    var
        txt:text;
        p:Pedido;
    begin
        Assign(det,nombre+'.dat');
        Assign(txt,'carga_'+nombre+'.txt');
        rewrite(det);
        reset(txt);
        while not eof(txt)do
        begin
            with p do
            begin
                Readln(txt,p.codp,p.cant,p.desc);
            end;
            Write(det,p);
        end;
        close(det);
        close(txt);
    end;

    //CREAR DETALLES
    procedure crearDetalles(var v:vecDet);
    var
        i:integer;
    begin
        for i:= 1 to dimF do 
        begin
            creaarUnDetalle(v[i],'det'+IntToStr(i));
        end;
    end;

    //IMPIRMIR DETALLE
    procedure imprimirMaestro(var arch:ArchivoMaestro);
    var
        reg:Producto;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(arch);
        while not eof(arch)do
        begin
            Read(arch,reg);
            writeln('CODP:',reg.codp,', NOMBRE:',reg.nom,
                    ', DESC:',reg.desc,', COD_BARRA:',reg.cod_barra,
                    ', CAT:',reg.cat,', STOCK:',reg.stock,', STOCK_MIN:',reg.stock_min);
        end;
        close(arch);
    end;

    //IMPRIMIR MAESTRO
    procedure imprimirDetalles(var vec:vecDet);
    var
        i:integer;
        reg:Pedido;
    begin
        writeln('***ARCHIVOS DETALLE***');
        for i:= 1 to dimF do 
        begin
            reset(vec[i]);
            while(not eof(vec[i]))do
            begin
                with reg do
                begin
                    Read(vec[i],reg);
                    writeln('CODP:',reg.codp,', CANT:',reg.cant ,', DESC:',reg.desc);
                end;
            end;
            close(vec[i]);
            writeln('------------');
        end;
    end;

    //LEER DETALLE
    procedure leerDet(var arch:archivoDetalle;var reg:Pedido);
    begin
        if(not eof(arch))then
        begin
            Read(arch,reg);
        end
        else reg.codp := valorAlto;
    end;

    //MINIMO
    Procedure minimo(var v:vecDet;var r:vecReg;var min:Pedido);
    var 
        i:integer;
        pos:integer;
    begin
        min.codp:= valorAlto;
        for i:= 1 to dimF do
            if (r[i].codp < min.codp)then
            begin
                min:= r[i];
                pos:= i;
            end;
        if(min.codp <> valorAlto) then
            leerDet(v[pos], r[pos]);
    end;

    //ACTUALIZAR MAESTRO
    procedure actualizarMaestro(var mae:archivoMaestro;var vdet:vecDet);
    var 
        min:Pedido;
        reg_mae:Producto;
        i:integer;
        vreg:vecReg;
        dif:integer;
    begin
        for i:= 1 to dimF do
        begin
            reset(vdet[i]);
            leerDet(vdet[i],vreg[i]);
        end;
        reset(mae);
        minimo(vdet,vreg,min);
        if(not eof(mae))then
            Read(mae,reg_mae);
        while(min.codp <> valorAlto)do
        begin
            while(not eof(mae))and(reg_mae.codp <> min.codp)do
                Read(mae,reg_mae);
            while(reg_mae.codp = min.codp)do
            begin
                dif := reg_mae.stock - min.cant;
                if(dif < 0)then
                begin
                    writeln('el pedido con código de producto ',min.codp,
                    ' y descripcion ',min.desc,' no pudo satisfacerse completamente por falta de ',
                    dif*-1,' unidades del producto');
                    reg_mae.stock:=0;
                end
                else reg_mae.stock:=dif;
                minimo(vdet,vreg,min);
            end;
            if(reg_mae.stock < reg_mae.stock_min)then
                writeln ('!! el stock del producto con codigo ',reg_mae.codp,
                ' se encuentra por debajo del stock minimo');
            seek(mae,filePos(mae)-1);
            Write(mae,reg_mae);
        end;
        close(mae);
        for i:= 1 to dimF do
        begin
            close(vdet[i]);
        end;
    end;

var
    maestro:ArchivoMaestro;
    detalles:vecDet;
BEGIN 
    crearMastro(maestro);
    crearDetalles(detalles);
    //imprimirMaestro(maestro);
    //imprimirDetalles(detalles);
    actualizarMaestro(maestro,detalles);
    imprimirMaestro(maestro);
END.