Program P2p13;
uses
    SysUtils;
const

dimF=2;
valoralto='ZZZ';

type

    vuelos_maestro = record
      dest:String;
      fecha:string;
      hora:string;
      asientos_disp:integer;
    end;

    vuelos_detalle = record
      dest:String;
      fecha:string;
      hora:string;
      asientos_comp:integer;
    end;

    archivoMaestro = file of vuelos_maestro;

    archivoDetalle =  file of vuelos_detalle;

    Procedure crearMaestro(var arch:archivoMaestro);
    var
        txt:text;
        v:vuelos_maestro;
    begin
        Assign(txt,'maestro.txt');
        Assign(arch,'archivoVuelos');
        reset(txt);
        rewrite(arch);
        while(not eof(txt))do
        begin
            with v do
            begin
                Readln(txt,asientos_disp,dest);
                Readln(txt,fecha);
                Readln(txt,hora);
                write(arch,v)
            end;
        end;
        writeln('archivo "archivoVuelos" creado con exito!');
        close(txt);
        close(arch);
    end;

    Procedure crearUnDetalle(var arch:archivoDetalle;nombre:string);
    var 
        txt:text;
        v:vuelos_detalle;
    begin
        Assign(txt,nombre + '.txt');
        Assign(arch,nombre);
        reset(txt);
        rewrite(arch);
        while(not eof(txt))do
        begin
            with v do
            begin
                Readln(txt,asientos_comp,dest);
                Readln(txt,fecha);
                Readln(txt,hora);
                write(arch,v)
            end;
        end;
        writeln('archivo "',nombre,'" creado con exito!');
        close(txt);
        close(arch);
    end;


    procedure imprimirMaestro(var arch:archivoMaestro);
    var
        v:vuelos_maestro;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(arch);
        while not eof(arch)do
        begin
            Read(arch,v);
            writeln('DESTINO:',v.dest,', FECHA:',v.fecha,', HORA:',v.hora,', ASIENTOS DISP:',v.asientos_disp);
        end;
        close(arch);
    end;

    procedure imprimirDetalle(var det:archivoDetalle);
    var
        v:vuelos_detalle;
    begin
        writeln();
        writeln('***ARCHIVOS DETALLE***');
        reset(det);
        while not eof(det)do
        begin
            Read(det,v);
            writeln('DESTINO:',v.dest,', FECHA:',v.fecha,', HORA:',v.hora,', ASIENTOS COMP:',v.asientos_comp);
        end;
        close(det);
        writeln('---------------');
    end;

    procedure leer(var arch:archivoMaestro;var reg:vuelos_maestro);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.dest:= valoralto;
    end;

    procedure leerDet(var arch:archivoDetalle;var reg:vuelos_detalle);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.dest:= valoralto;
    end;

    //esta mal actualizado 
    procedure minimo(var det1,det2:archivoDetalle;var r1,r2,min:vuelos_detalle);
    begin
        if(r1.dest<r2.dest)then
        begin
            min:=r1;
            leerDet(det1,r1);
        end
        else begin
            min:=r2;
            leerDet(det2,r2);
        end;    
    end;

    procedure actualizarMaestro(var mae:archivoMaestro;var det1,det2:archivoDetalle;cantidad:integer);
    var
        txt:text;
        regMae:vuelos_maestro;
        regDet1,regDet2,min:vuelos_detalle;
    begin
        Assign(txt,'listado.txt');
        rewrite(txt);
        reset(mae);
        reset(det1);
        reset(det2);
        leer(mae,regMae);
        leerDet(det1,regDet1);
        leerDet(det2,regDet2);
        minimo(det1,det2,regDet1,regDet2,min);
        while(min.dest<>valoralto)do
        begin
            while(regMae.dest<>min.dest)and(regMae.dest<>valoralto)do leer(mae,regMae);
            while(regMae.dest = min.dest)do
            begin
                while(regMae.fecha<>min.fecha)and(regMae.dest<>valoralto)do leer(mae,regMae);
                while(regMae.dest = min.dest)and(regMae.fecha=min.fecha)do
                begin
                    while(regMae.hora<>min.hora)and(regMae.dest<>valoralto)do leer(mae,regMae);
                    while(regMae.dest = min.dest)and(regMae.fecha=min.fecha)and(regMae.hora = min.hora)do
                    begin
                        regMae.asientos_disp:= regMae.asientos_disp - min.asientos_comp;
                        minimo(det1,det2,regDet1,regDet2,min);
                    end;
                    if(regMae.asientos_disp < cantidad)then
                        Writeln(txt, regMae.dest,regMae.fecha,regMae.hora);
                    seek(mae,filepos(mae)-1);
                    Write(mae,regMae);
                end;
            end;
            
        end;
        close(txt);
        close(mae);
        close(det1);
        close(det2);
    end;

var
    maestro:archivoMaestro;
    det1,det2:archivoDetalle;
    cantidad:integer;
BEGIN
    crearMaestro(maestro);
    crearUnDetalle(det1,'det1');
    crearUnDetalle(det2,'det2');
    imprimirMaestro(maestro);
    imprimirDetalle(det1);
    imprimirDetalle(det2);
    writeln('ingrese un numero para listar los vuelos con menor cantidad de asientos disponibles a ese numero: ');
    //readln(cantidad);
    cantidad:= 130;
    actualizarMaestro(maestro,det1,det2,cantidad);
    imprimirMaestro(maestro);
END.