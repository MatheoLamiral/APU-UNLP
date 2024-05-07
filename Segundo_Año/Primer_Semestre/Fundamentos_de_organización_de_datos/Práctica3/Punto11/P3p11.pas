Program P3p11;
uses
    SysUtils;
const
    dimF = 3;//5
    valoralto = 9999;
type

    infoDetalle = record
        cod_usuario:integer;
        fecha:String;
        tiempo_sesion:real;
    end;

    infoMaestro = record
        cod_usuario:integer;
        fecha:String;
        tiempo_total_sesiones:real;
    end;

    archivoMaestro = file of infoMaestro;
    archivoDetalle = file of infoDetalle;

    vecDet = array [1 .. dimF] of archivoDetalle;

    procedure crearUnDetalle(var det:ArchivoDetalle;nombre_txt:string;nombre_binario:string);
    var
        txt: text;
        reg:infoDetalle;
    begin
        assign(txt, nombre_txt);
        reset(txt);
        assign(det, nombre_binario);
        rewrite(det);
        while(not eof(txt)) do
            begin
                with reg do
                    begin
                        readln(txt, cod_usuario, tiempo_sesion, fecha);
                        write(det, reg);
                    end;
            end;
        writeln('Archivo binario detalle creado con exito!');
        close(det);
        close(txt);
    end;

    procedure crearDetalles(var vec: vecDet);
    var
        i: integer;
    begin
        for i:= 1 to dimF do 
            crearUnDetalle(vec[i],'det'+IntToStr(i)+'.txt','detalle'+IntToStr(i));
    end;

    procedure crearMaestro(var mae: ArchivoMaestro; var v: vecDet);
    var
        reg_mae:infoMaestro;
        reg_det:infoDetalle;
        i: integer;
        encontre:boolean;
    begin
        Assign(mae, 'ArchivoSesiones');
        rewrite(mae);
        for i:= 1 to dimF do
        begin
            reset(v[i]);
            while(not eof(v[i]))do
            begin
                Read(v[i],reg_det);
                encontre:=false;
                seek(mae,0);
                while(not eof(mae))and(not encontre)do
                begin
                    Read(mae,reg_mae);
                    if(reg_mae.cod_usuario=reg_det.cod_usuario)then encontre:=true;
                end;
                if(encontre)and(reg_mae.fecha=reg_det.fecha)then
                begin
                    reg_mae.tiempo_total_sesiones:=reg_mae.tiempo_total_sesiones+reg_det.tiempo_sesion;
                    seek(mae,filepos(mae)-1);
                end
                else begin
                    reg_mae.cod_usuario:=reg_det.cod_usuario;
                    reg_mae.fecha:=reg_det.fecha;
                    reg_mae.tiempo_total_sesiones:=reg_det.tiempo_sesion;
                end;
                Write(mae,reg_mae);
            end;
            close(v[i]);
        end;
        close(mae);
    end;

    procedure imprimirArchivo(var mae: archivoMaestro);
    var
        reg_mae: infoMaestro;
    begin
        reset(mae);
        while(not eof(mae)) do
            begin
                read(mae, reg_mae);
                writeln('CODIGO:', reg_mae.cod_usuario,', FECHA:',reg_mae.fecha,', TIEMPO_TOTAL_SESIONES:', reg_mae.tiempo_total_sesiones:0:2);
            end;
        close(mae);
    end;

var 
    detalles:vecDet;
    maestro:archivoMaestro;
BEGIN
    crearDetalles(detalles);
    crearMaestro(maestro,detalles);
    imprimirArchivo(maestro);
END.