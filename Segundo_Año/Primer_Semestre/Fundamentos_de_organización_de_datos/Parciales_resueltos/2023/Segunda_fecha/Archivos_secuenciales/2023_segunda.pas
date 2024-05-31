program segunda2023;

const
    valoralto = 9999;
type

  partido = record 
    cod_equipo: integer;
    nom:string;
    anio:integer;
    cod_torneo:integer;
    cod_rival:integer;
    goles_f:integer;
    goles_c:integer;
    puntos:integer;
  end;

  ArchivoPartidos = file of partido;

  Procedure cargarPartidos(var archivo:ArchivoPartidos);
  var 
    txt:text;
    reg:partido;
  begin
    assign(txt,'carga.txt');
    assign(archivo,'partidos.dat');
    reset(txt);
    rewrite(archivo);
    while not eof(txt) do begin
      with reg do begin
        Readln(txt,anio,cod_torneo,cod_equipo,
        cod_rival,goles_f,goles_c,puntos,nom);
        write(archivo,reg);
      end;
    end;
    close(txt);
    close(archivo);
  end;

  Procedure imprimirArchivo(var archivo:ArchivoPartidos);
  var
    reg:partido;
  begin
    reset(archivo);
    while not eof(archivo) do begin
      read(archivo,reg);
      Writeln('codigo de equipo:', reg.cod_equipo,'nombre:',reg.nom,'anio:',reg.anio,
      'codigo de torneo:',reg.cod_torneo,'codigo de rival:',reg.cod_rival,'goles a favor:',
      reg.goles_f,'goles en contra:',reg.goles_c,'puntos:',reg.puntos);
    end;
    close(archivo);
  end;
  
  Procedure leer (var archivo:ArchivoPartidos; var reg:partido);
  begin
    if not eof(archivo) then
      read(archivo,reg)
    else
      reg.anio:=valoralto;
  end;

  Procedure ProcesarEquipo(var total_goles_f,total_goles_c,partidos_ganados,
                        partidos_perdidos,partidos_empatados,total_puntos:integer;var reg:partido);
  begin
    total_goles_f:=total_goles_f+reg.goles_f;
    total_goles_c:=total_goles_c+reg.goles_c;
    case reg.puntos of
        3:partidos_ganados:=partidos_ganados+1;
        1:partidos_empatados:=partidos_empatados+1;
        0:partidos_perdidos:=partidos_perdidos+1;
    end;
    total_puntos:=total_puntos+reg.puntos;
  end;

  Procedure imprimirPartido(anio_actual,torneo_actual,equipo_actual,total_goles_f,total_goles_c,partidos_ganados,
                            partidos_perdidos,partidos_empatados,total_puntos,diferencia_goles:integer);
  begin
    writeln('                       Total goles a favor:',total_goles_f);
    writeln('                       Total goles en contra:',total_goles_c);
    writeln('                       Diferencia de goles:',diferencia_goles);
    writeln('                       Partidos ganados:',partidos_ganados);
    writeln('                       Partidos perdidos:',partidos_perdidos);
    writeln('                       Partidos empatados:',partidos_empatados);
    writeln('                       Total puntos::',total_puntos);
  end;

  Procedure inicializarEn0(var total_goles_f,total_goles_c,partidos_ganados,
                            partidos_perdidos,partidos_empatados,total_puntos,diferencia:integer);
  begin
    total_goles_f:=0;
    total_goles_c:=0;
    partidos_ganados:=0;
    partidos_perdidos:=0;
    partidos_empatados:=0;
    total_puntos:=0;
    diferencia:=0;
  end;

  Procedure corteDeControl (var archivo:ArchivoPartidos);
  var
    reg:partido;
    campeon,nom_act:string;
    max_puntos:integer;
    anio_actual,torneo_actual,equipo_actual:integer;
    total_goles_f,total_goles_c,partidos_ganados,partidos_perdidos,partidos_empatados,total_puntos,diferencia_goles:integer;
  begin
    reset(archivo);
    leer(archivo,reg);
    while(reg.anio<>valoralto)do
    begin
        anio_actual:=reg.anio;
        writeln('anio ',anio_actual);
        while(anio_actual=reg.anio)do
        begin
            max_puntos:=-1;
            torneo_actual:=reg.cod_torneo;
            Writeln('       cod_torneo ',torneo_actual);
            while(anio_actual=reg.anio)and(torneo_actual=reg.cod_torneo)do
            begin
                equipo_actual:=reg.cod_equipo;
                nom_act:=reg.nom;
                writeln('               cod_equipo ',equipo_actual, 'nombre equipo ',nom_act);
                inicializarEn0(total_goles_f,total_goles_c,partidos_ganados,partidos_perdidos,partidos_empatados,total_puntos,diferencia_goles);
                while(anio_actual=reg.anio)and(torneo_actual=reg.cod_torneo)and(equipo_actual=reg.cod_equipo)do
                begin
    	            ProcesarEquipo(total_goles_f,total_goles_c,partidos_ganados,
                                    partidos_perdidos,partidos_empatados,total_puntos,reg);
                    leer(archivo,reg);
                end;
                diferencia_goles:=total_goles_f-total_goles_c;
                if(diferencia_goles<0)then
                    diferencia_goles:=diferencia_goles*(-1);
                imprimirPartido(anio_actual,torneo_actual,equipo_actual,total_goles_f,total_goles_c,partidos_ganados,
                                partidos_perdidos,partidos_empatados,total_puntos,diferencia_goles);
                if(total_puntos>max_puntos)then
                begin
                    max_puntos:=total_puntos;
                    campeon:=nom_act;
                end;
            end;
            Writeln('El equipo',campeon,'fue campeon del torneo',torneo_actual,'del anio',anio_actual);
            writeln('--------------------------------------------------------------------------------');
        end;
        writeln('--------------------------------------------------------------------------------');
    end;
  end;

var
    archivo:ArchivoPartidos;
begin
  cargarPartidos(archivo);
  corteDeControl(archivo);
end.
