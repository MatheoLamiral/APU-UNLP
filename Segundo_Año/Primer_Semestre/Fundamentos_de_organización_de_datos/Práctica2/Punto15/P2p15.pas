Program P2p15;
uses
    SysUtils;
const
    dimF = 2;//100
    valoralto = 9999;
type

    emision = record
        fecha:integer;
        cod:integer;
        nom:string;
        desc:string;
        precio:real;
        ejemplares:integer;
        ejemplares_vendidos:integer;
    end;

    venta = record
        fecha:integer;
        cod:integer;
        ejemplares_vendidos:integer;
    end;

    archivoMaestro = file of emision;
    archivoDetalle = file of venta;

    vecDetalles = array [1..dimF] of archivoDetalle;
    vecRegistros = array [1..dimF] of venta;

    Procedure crearMaestro(var arch:archivoMaestro);
    var
        txt:text;
        regMae:emision;
    begin
        Assign(txt,'maestro.txt');
        Assign(arch,'archivoEmisiones');
        reset(txt);
        rewrite(arch);
        while(not eof(txt))do
        begin
            with regMae do
            begin
                Readln(txt,fecha,cod,precio,ejemplares,ejemplares_vendidos,nom);
                Readln(txt,desc);
                Write(arch,regMae);
            end;
        end;
        writeln('Archivo "archivoEmisiones" creado con exito!');
        close(txt);
        close(arch);
    end;

    Procedure crearUnDetalle(var det:archivoDetalle;nombre:string);
    var
        txt:text;
        regDet:venta;
    begin
        Assign(txt,nombre+'.txt');
        Assign(det,nombre);
        reset(txt);
        rewrite(det);
        while(not eof(txt))do
        begin
            with regDet do
            begin
                Readln(txt,fecha,cod,ejemplares_vendidos);
                Write(det,regDet);
            end;
        end;
        writeln('Archivo "',nombre,'" creado con exito!');
        close(txt);
        close(det);
    end;

    Procedure crearDetalles(var vec:vecDetalles);
    var
        i:integer;
    begin
        for i:= 1 to dimF do
        begin
            crearUnDetalle(vec[i],'det'+IntToStr(i));
        end;
    end;

    procedure imprimirMaestro(var arch:archivoMaestro);
    var
        reg:emision;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(arch);
        while not eof(arch)do
        begin
            Read(arch,reg);
            writeln('FECHA:',reg.fecha,', COD_SEM:',reg.cod,
                    ', NOM_SEM:',reg.nom,', DESC:',reg.desc,
                    ', PRECIO:',reg.precio:0:2,', EJEMPLARES:',reg.ejemplares,', EJEMPLARES_VENDIDOS:',reg.ejemplares_vendidos);
        end;
        close(arch);
    end;

    procedure leerMae(var arch:archivoMaestro;var reg:emision);
    begin
        if(not eof(arch))then
        begin
            Read(arch,reg);
        end
        else reg.fecha := valoralto;
    end;

    procedure leerDet(var arch:archivoDetalle;var reg:venta);
    begin
        if(not eof(arch))then
        begin
            Read(arch,reg);
        end
        else reg.fecha := valoralto;
    end;

    procedure imprimirDetalles(var vec:vecDetalles);
    var
        i:integer;
        reg:venta;
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
                    writeln('FECHA:',reg.fecha,', COD_SEM:',reg.cod ,', EJEMPLARES_VENDIDOS:',reg.ejemplares_vendidos);
                end;
            end;
            close(vec[i]);
            writeln('------------');
        end;
    end;

    procedure minimo(var v: vecDetalles; var vecReg: vecRegistros; var min: venta);
    var
        i, pos: integer;
    begin
        min.fecha:= valoralto;
        for i:= 1 to dimF do
            if (vecReg[i].fecha < min.fecha) or ((vecReg[i].fecha = min.fecha) and (vecReg[i].cod < min.cod)) then
            begin
                min:= vecReg[i];
                pos:= i;
            end;
        if(min.fecha <> valoralto) then
            leerDet(v[pos], vecReg[pos]);
    end;

    Procedure actualizarMaestro(var mae:archivoMaestro;var det:vecDetalles);
    var
        reg:vecRegistros;
        reg_mae:emision;
        max_ventas,min_ventas,cod_max,cod_min,total_ventas,fecha_min,fecha_max: integer;
        min:venta;
        i:integer;
    begin
        //minimos
        min_ventas:=9999;
        cod_min:= 0;
        fecha_min:=9999;
        //maximos
        max_ventas:=-1;
        cod_max:=0;
        fecha_max:=-1;
        for i := 1 to dimF do
        begin
            reset(det[i]);
            leerDet(det[i],reg[i]);
        end;
        reset(mae);
        minimo(det,reg,min);
        leerMae(mae,reg_mae);
        //mientras no se termine el archivo 
        while(reg_mae.fecha <> valoralto)do
        begin
            //mientras no encuentre la misma provincia y no se termine el archivo 
            while(reg_mae.fecha <> min.fecha )and(reg_mae.fecha <> valoralto)do leerMae(mae,reg_mae);
            //mientras este en la misma provincia y no se termine el archivo 
            while(reg_mae.fecha = min.fecha)and(reg_mae.fecha<>valoralto)do
            begin
                //mientras no encuentre la localidad y no se termine el archivo 
                while(reg_mae.cod <> min.cod )and(reg_mae.fecha <> valoralto)do leerMae(mae,reg_mae);
                //mientras este en la misma provincia, misma localidad y no se termine el archivo
                total_ventas:=0;
                while(reg_mae.fecha = min.fecha)and(reg_mae.cod = min.cod)and(reg_mae.fecha<>valoralto)do
                begin
                    if(reg_mae.ejemplares>= min.ejemplares_vendidos)then
                    begin
                        reg_mae.ejemplares_vendidos:= reg_mae.ejemplares_vendidos + min.ejemplares_vendidos;
                        reg_mae.ejemplares:= reg_mae.ejemplares - min.ejemplares_vendidos;
                        total_ventas:= total_ventas + min.ejemplares_vendidos;
                    end;
                    minimo(det,reg,min);
                end;
                if(total_ventas>max_ventas)then
                begin
                    max_ventas := total_ventas;
                    fecha_max := reg_mae.fecha;
                    cod_max := reg_mae.cod;
                end;
                if(total_ventas<min_ventas)then
                begin
                    min_ventas := total_ventas;
                    fecha_min := reg_mae.fecha;
                    cod_min := reg_mae.cod
                end;
                seek(mae,(filepos(mae))-1);
                Write(mae,reg_mae);
            end;
        end;
        writeln('La fecha del semanario con menos ventas es ',fecha_min,' y su codigo es ',cod_min);
        writeln('La fecha del semanario con mas ventas es ',fecha_max,' y su codigo es ',cod_max);
        for i := 1 to dimF do
        begin
            close(det[i]);
        end;
        close(mae);
    end;

var
    maestro:archivoMaestro;
    detalles:vecDetalles;
BEGIN
    crearMaestro(maestro);
    crearDetalles(detalles);
    imprimirMaestro(maestro);
    imprimirDetalles(detalles);
    actualizarMaestro(maestro,detalles);
    imprimirMaestro(maestro);
END.
