program P2p11;

const
    valoralto = 9999;
type
    acceso = record  
        anio: integer;
        mes: integer;
        dia: integer;
        id: integer;
        tiempo: real;
    end;
    archivoMaestro = file of acceso;
procedure crearMaestro(var mae: archivoMaestro);
var
    txt: text;
    a: acceso;
begin
    assign(txt, 'accesos.txt');
    reset(txt);
    assign(mae, 'ArchivoAccesos.dat');
    rewrite(mae);
    while(not eof(txt)) do
        begin
            with a do
                begin
                    readln(txt, anio, mes, dia, id, tiempo);
                    write(mae, a);
                end;
        end;
    writeln('Archivo binario maestro creado con éxito!');
    close(mae);
    close(txt);
end;
procedure leer(var mae: archivoMaestro; var a: acceso);
begin
    if(not eof(mae)) then
        read(mae, a)
    else
        a.anio:= valoralto;
end;
procedure informarAnio(var mae: archivoMaestro);
var
    a: acceso;
    anio, mesActual, diaActual, idActual: integer;
    tiempoAnio, tiempoMes, tiempoDia, tiempoUsuario: real;
begin
    writeln('Ingrese el anio para realizar el informe');
    readln(anio);
    reset(mae);
    leer(mae, a);
    if(a.anio <> valoralto) then
        begin
            while(a.anio <> valoralto) and (a.anio < anio) do
                leer(mae, a);
            if(a.anio = anio) then
                begin
                    tiempoAnio:= 0;
                    writeln('Anio: ', a.anio);
                    while(anio = a.anio) do
                        begin
                            mesActual:= a.mes;
                            tiempoMes:= 0;
                            writeln('-------------------------------');
                            writeln('Mes: ', a.mes);
                            while((anio = a.anio) and (a.mes = mesActual)) do
                                begin
                                    diaActual:= a.dia;
                                    tiempoDia:= 0;
                                    writeln();
                                    writeln('Dia ', a.dia);
                                    while((anio = a.anio) and (a.mes = mesActual) and (diaActual = a.dia)) do
                                        begin
                                            idActual:= a.id;
                                            tiempoUsuario:= 0;
                                            while((anio = a.anio) and (a.mes = mesActual) and (diaActual = a.dia) and (idActual = a.id)) do
                                                begin
                                                    tiempoUsuario:= tiempoUsuario + a.tiempo;
                                                    leer(mae, a);
                                                end;
                                            writeln('idUsuario ', idActual, ' Tiempo total de acceso en el dia ', diaActual, ' del mes ', mesActual, ': ', tiempoUsuario:0:2);
                                            tiempoDia:= tiempoDia + tiempoUsuario;
                                        end;
                                    writeln('Tiempo total acceso dia ', diaActual, ' mes ', mesActual, ': ', tiempoDia:0:2);
                                    tiempoMes:= tiempoMes + tiempoDia;
                                end;
                            writeln();
                            writeln('Total tiempo de acceso de mes ', mesActual, ': ', tiempoMes:0:2);
                            tiempoAnio:= tiempoAnio + tiempoMes;
                        end;
                    writeln('-------------------------------');
                    writeln('Total tiempo de acceso anio: ', tiempoAnio:0:2);
                end
            else
                writeln('Anio no encontrado!');
        end;
    close(mae);
end;
var
    mae: archivoMaestro;
begin
    crearMaestro(mae);
    informarAnio(mae);
end.