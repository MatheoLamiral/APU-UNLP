program tercera2017;

Uses
    sysUtils;
const
    valoralto = 9999;
    dimF = 2; // N
type
    alumno = record
        dni: integer;
        cod_carrera: integer;
        monto_pagado: real;
    end;

    pago = record
        dni: integer;
        cod_carrera: integer;
        monto_cuota: real;
    end;

    maestro = file of alumno;
    detalle = file of pago;

    vector_detalle = array [1..dimF] of detalle;
    vector_registros = array [1..dimF] of pago;

    Procedure cargarMaestro(var m: maestro);
    var
        a: alumno;
        txt: text;
    begin
        Assign(m, 'archivo_alumnos.dat');
        Assign(txt, 'carga_maestro.txt');
        reset(txt);
        rewrite(m);
        while not eof(txt) do 
        begin
            with a do
            begin
                readln(txt, dni, cod_carrera, monto_pagado);
            end;
            write(m, a);
        end;
        close(m);
        close(txt);
    end;

    Procedure cargarUnDetalle(var d: detalle; nombre: string);
    var
        txt: text;
        p: pago;
    begin
        Assign(d, nombre+'.dat');
        Assign(txt, 'carga_'+nombre+'.txt');
        reset(txt);
        rewrite(d);
        while not eof(txt) do
        begin
            with p do
            begin
                readln(txt, dni, cod_carrera, monto_cuota);
            end;
            write(d, p);
        end;
        close(d);
        close(txt);
    end;

    Procedure cargarDetalles(var v: vector_detalle);
    var
        i: integer;
    begin
        for i := 1 to dimF do
        begin
            cargarUnDetalle(v[i], 'det'+IntToStr(i));
        end;
    end;

    Procedure imprimirDetalles(var v: vector_detalle);
    var
        i: integer;
        p: pago;
    begin
        for i := 1 to dimF do
        begin
            WriteLn('DETALLE ', i);
            reset(v[i]);
            while not eof(v[i]) do
            begin
                read(v[i], p);
                with p do
                begin
                    writeln('DNI: ', dni, ' Carrera: ', cod_carrera, ' Monto cuota: ', monto_cuota:0:2);
                end;
            end;
            close(v[i]);
            WriteLn('-----------------');
        end;
    end;

    Procedure imprimirMaestro(var m: maestro);
    var
        a: alumno;
    begin
        reset(m);
        while not eof(m) do 
        begin
            Read(m, a);
            with a do
            begin
                writeln('DNI: ', dni, ' Carrera: ', cod_carrera, ' Monto pagado: ', monto_pagado:0:2);
            end;
        end;
        close(m);
    end;

    Procedure leerDet(var d: detalle; var p: pago);
    begin
        if not eof(d) then
            read(d, p)
        else begin
            p.dni := valoralto;
        end;
    end;

    Procedure leerMae(var m: maestro; var a: alumno);
    begin
        if not eof(m) then
            read(m, a)
        else begin
            a.dni := valoralto;
        end;
    end;

    Procedure minimo(var vd: vector_detalle; var vr:vector_registros; var min: pago);
    var
        i, pos: integer;
    begin
        min.dni := valoralto;
        for i := 1 to dimF do
        begin
            if (vr[i].dni < min.dni) then
            begin
                min:=vr[i];
                pos:=i;	
            end;
        end;
        if (min.dni <> valoralto) then
        begin
            //leo el siguiente registro del detalle que tenia el minimo
            leerDet(vd[pos], vr[pos]);
        end;
    end;

    Procedure actualizarMaestro(var m: maestro; var vd: vector_detalle);
    var
        reg_mae: alumno;
        vr: vector_registros;
        min: pago;
        i:integer;
    begin
        for i := 1 to dimF do
        begin
            reset(vd[i]);
            //cargo el primer registro de cada detalle
            leerDet(vd[i], vr[i]);
        end;
        reset(m);
        minimo(vd, vr, min);
        leerMae(m, reg_mae);
        while(min.dni <> valoralto) do
        begin
            while(min.dni <> reg_mae.dni)do
                leerMae(m, reg_mae);
            while(min.dni = reg_mae.dni) do
            begin
                while(min.cod_carrera <> reg_mae.cod_carrera) do
                    leerMae(m, reg_mae);
                while(min.dni = reg_mae.dni)and(min.cod_carrera = reg_mae.cod_carrera) do
                begin
                    reg_mae.monto_pagado:=reg_mae.monto_pagado + min.monto_cuota;
                    minimo(vd, vr, min);
                end;
                seek(m, filepos(m)-1);
                write(m, reg_mae);
            end;
        end;
        close(m);
        for i := 1 to dimF do
        begin
            close(vd[i]);
        end;
    end;

    Procedure listarEnTxt (var m: maestro);
    var
        a: alumno;
        txt: text;
    begin
        Assign(txt, 'listado.txt');
        reset(m);
        rewrite(txt);
        while not eof(m) do
        begin
            read(m, a);
            if(a.monto_pagado = 0) then
            begin
                with a do
                begin
                    writeln(txt, dni, ' ',cod_carrera, ' alumno moroso');
                end;
            end;
        end;
        close(m);
        close(txt);
    end;


var
    mae:maestro;
    vec_det:vector_detalle;
begin
    cargarMaestro(mae);
    //imprimirMaestro(mae);
    cargarDetalles(vec_det);
    //imprimirDetalles(vec_det);
    actualizarMaestro(mae, vec_det);
    //imprimirMaestro(mae);
    listarEnTxt(mae);
end.
