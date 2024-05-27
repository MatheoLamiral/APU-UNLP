Program P2p18;
const
    valoralto=9999;
    dimF=1;//50
type

    infoDetNac=record
        nro_partida:integer;
        nom:string;
        ape:string;
        direc:string;
        matricula_medico_nac:string;
        ape_y_nom_madre:string;
        dni_madre:integer;
        ape_y_nom_padre:string;
        dni_padre:integer;
    end;

    infoDetFall=record
        nro_partida:integer;
        dni:integer;
        nom:string;
        ape:string;
        matricula_medico_fall:string;
        fecha_y_hora:string;
        lugar:string;
    end;

     infoMaestro = record
        nro_partida: integer;
        nom: string;    
        ape: string;
        direc: string;
        matricula_medico_nac: string;
        ape_y_nom_madre: string;
        dni_madre: integer;
        ape_y_nom_padre: string;
        dni_padre: integer;
        fallecio: boolean;
        matricula_medico_fall: string;
        fecha_y_hora: string;
        lugar: string;
    end;

    archivoDetalleNac = file of infoDetNac;
    archivoDetalleFall = file of infoDetFall;
    archivoMaestro = file of infoMaestro;
    vecDetNac = array[1..dimF] of archivoDetalleNac;
    vecDetFall = array[1..dimF] of archivoDetalleFall;
    vecRegNac = array[1..dimF] of infoDetNac;
    vecRegFall = array[1..dimF] of infoDetFall;

    procedure leerPersona1(var infoDetalle: infoDetNac);
    begin
        writeln('Ingrese el numero de partida:');
        readln(infoDetalle.nro_partida);
        if(infoDetalle.nro_partida <> -1) then
        begin
            writeln('Ingrese el nombre del nacido:');
            readln(infoDetalle.nom);
            writeln('Ingrese el apellido del nacido:');
            readln(infoDetalle.ape);
            writeln('Ingrese la direccion:');
            readln(infoDetalle.direc);
            writeln('Ingrese la matricula de nacimiento:');
            readln(infoDetalle.matricula_medico_nac);
            writeln('Ingrese el nombre y apellido de la madre:');
            readln(infoDetalle.ape_y_nom_madre);
            writeln('Ingrese el DNI de la madre:');
            readln(infoDetalle.dni_madre);
            writeln('Ingrese el nombre y apellido del padre:');
            readln(infoDetalle.ape_y_nom_padre);
            writeln('Ingrese el DNI del padre:');
            readln(infoDetalle.dni_padre);
        end;
    end;
    procedure leerPersona2(var infoDetalle: infoDetFall);
    begin
        writeln('Ingrese el numero de partida:');
        readln(infoDetalle.nro_partida);
        if(infoDetalle.nro_partida <> -1) then
        begin
            writeln('Ingrese el dni del fallecido:');
            readln(infoDetalle.dni);
            writeln('Ingrese el nombre del fallecido:');
            readln(infoDetalle.nom);
            writeln('Ingrese el apellido del fallecido:');
            readln(infoDetalle.ape);
            writeln('Ingrese la matricula de deceso:');
            readln(infoDetalle.matricula_medico_fall);
            writeln('Ingrese la fecha y hora de deceso:');
            readln(infoDetalle.fecha_y_hora);
            writeln('Ingrese el lugar de deceso:');
            readln(infoDetalle.lugar);
        end;
    end;
    procedure crearUnSoloDetalle1(var det: archivoDetalleNac);
    var
        infoDet: infoDetNac;
        nombre: string;
    begin
        writeln('Ingrese el nombre de texto del archivo detalle');
        readln(nombre);
        assign(det, nombre);
        rewrite(det);
        leerPersona1(infoDet);
        while(infoDet.nro_partida <> -1) do
        begin
            write(det, infoDet);
            leerPersona1(infoDet);
        end;
        writeln('Archivo detalle binario creado con exito!');
        close(det);
    end;

    procedure crearUnSoloDetalle2(var det: archivoDetalleFall);
    var
        infoDet: infoDetFall;
        nombre: string;
    begin
        writeln('Ingrese el nombre de texto del archivo detalle');
        readln(nombre);
        Assign(det, nombre);
        rewrite(det);
        leerPersona2(infoDet);
        while(infoDet.nro_partida <> -1) do
        begin
            write(det, infoDet);
            leerPersona2(infoDet);
        end;
        writeln('Archivo detalle binario creado con exito!');
        close(det);
    end;

    procedure crearDetalles1(var vec: vecDetNac);
    var
        i: integer;
    begin
        for i:= 1 to dimF do
            crearUnSoloDetalle1(vec[i]);
    end;

    procedure crearDetalles2(var vec: vecDetFall);
    var
        i: integer;
    begin
        for i:= 1 to dimF do
            crearUnSoloDetalle2(vec[i]);
    end;
    
    procedure leerFall(var det: archivoDetalleFall; var infoDet: infoDetFall);
    begin
        if(not eof(det)) then
            read(det, infoDet)
        else
            infoDet.nro_partida:= valoralto;
    end;

    procedure leerNac(var det: archivoDetalleNac; var infoDet: infoDetNac);
    begin
        if(not eof(det)) then
            read(det, infoDet)
        else
            infoDet.nro_partida:= valoralto;
    end;

    procedure minimoFall(var vecDet: vecDetFall; var vecReg: vecRegFall; var min: infoDetFall);
    var
        i, pos: integer;
    begin
        min.nro_partida:= valoralto;
        for i:= 1 to dimF do
        if(vecReg[i].nro_partida < min.nro_partida) then
            begin
                min:= vecReg[i];
                pos:= i;
            end;
        if(min.nro_partida <> valoralto) then
            leerFall(vecDet[pos], vecReg[pos]);
    end;

    procedure minimoNac(var vecDet: vecDetNac; var vecReg: vecRegNac; var min: infoDetNac);
    var
        i, pos: integer;
    begin
        min.nro_partida:= valoralto;
        for i:= 1 to dimF do
            if(vecReg[i].nro_partida < min.nro_partida) then
            begin
                min:= vecReg[i];
                pos:= i;
            end;
        if(min.nro_partida <> valoralto) then
            leerNac(vecDet[pos], vecReg[pos]);
    end;

    procedure merge(var mae: archivoMaestro; var vecFall: vecDetFall; var vecNac: vecDetNac);
    var
        vecRegsNac: vecRegNac;
        vecRegsFall: vecRegFall;
        minNacido: infoDetNac;
        minFallecido: infoDetFall;
        actual: infoMaestro;
        i: integer;
    begin
        for i:= 1 to dimF do
            begin
                reset(vecFall[i]);
                reset(vecNac[i]);
                leerFall(vecFall[i], vecRegsFall[i]);
                leerNac(vecNac[i], vecRegsNac[i]);
            end;
        Assign(mae, 'ArchivoMaestro');
        rewrite(mae);
        minimoFall(vecFall, vecRegsFall, minFallecido);
        minimoNac(vecNac, vecRegsNac, minNacido);
        while(minNacido.nro_partida <> valoralto) do
        begin
            actual.nro_partida:= minNacido.nro_partida;
            actual.nom:= minNacido.nom; 
            actual.ape:= minNacido.ape;
            actual.direc:= minNacido.direc;
            actual.matricula_medico_nac:= minNacido.matricula_medico_nac;
            actual.ape_y_nom_madre:= minNacido.ape_y_nom_madre;
            actual.dni_madre:= minNacido.dni_madre;
            actual.ape_y_nom_padre:= minNacido.ape_y_nom_padre;
            actual.dni_padre:= minNacido.dni_padre;
            if(minNacido.nro_partida = minFallecido.nro_partida) then
            begin
                actual.fallecio:= true;
                actual.matricula_medico_fall:= minFallecido.matricula_medico_fall;
                actual.fecha_y_hora:= minFallecido.fecha_y_hora;
                actual.lugar:= minFallecido.lugar;
            end
            else
                actual.fallecio:= false;
            write(mae, actual);
            minimoNac(vecNac, vecRegsNac, minNacido);
            if(actual.fallecio) then
                minimoFall(vecFall, vecRegsFall, minFallecido);
        end;
        for i:= 1 to dimF do
        begin
            close(vecFall[i]);
            close(vecNac[i]);
        end;
        close(mae);
    end;

    procedure imprimirMaestro(var mae: archivoMaestro); 
    var
        infoMae: infoMaestro;
    begin
        reset(mae);
        while(not eof(mae)) do
        begin
            read(mae, infoMae);
            writeln();
            write('PARTIDA:', infoMae.nro_partida, ', NOMBRE:', infoMae.nom, ', APELLIDO:', infoMae.ape);
            if(infoMae.fallecio) then
                write(', MATRICULA_FALL:', infoMae.matricula_medico_fall, ', FECHA Y HORA;', infoMae.fecha_Y_Hora, ', LUGAR_', infoMae.lugar);
        end;
        close(mae);
    end;

    procedure exportarTexto(var mae: archivoMaestro);
    var
        txt: text;
        infoMae: infoMaestro;
    begin
        reset(mae);
        Assign(txt, 'personas.txt');
        rewrite(txt);
        while(not eof(mae)) do
            begin
                read(mae, infoMae);
                with infoMae do
                begin
                    writeln(txt, 'Partida=', nro_partida, ' Nombre=', nom, ' Apellido=', ape, ' Direccion=', direc, ' MatNacimiento=', matricula_medico_nac, ' NAMadre=', ape_y_nom_madre, ' DNIMadre=', dni_madre, ' NAPadre=', ape_y_nom_padre, ' DNIPadre=', dni_padre);
                    if(fallecio) then
                        writeln(txt, 'Fallecido', ' MatFallecimiento=', matricula_medico_fall, ' FechaHora=', fecha_y_hora, ' Lugar=', lugar);
                end;
            end;
        close(mae);
        close(txt);
    end;
var
    vecNac: vecDetNac;
    vecFall: vecDetFall;
    mae: archivoMaestro;
BEGIN
    crearDetalles1(vecNac);
    crearDetalles2(vecFall);
    merge(mae, vecFall, vecNac);
    imprimirMaestro(mae);
    exportarTexto(mae);
END.

