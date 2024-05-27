Program P2p8;
const
    valoralto=-1;
type
    venta=record
        cod:integer;
        nom:string;
        ape:string;
        anio:integer;
        mes:integer;
        dia:integer;
        monto:real;
    end;

    ArchivoMaestro=file of venta;

    procedure crearMaestro(var mae:ArchivoMaestro);
    var
        txt:text;
        v:venta;
    begin
        Assign(mae,'ArchivoVentas');
        Assign(txt,'ventas.txt');
        rewrite(mae);
        reset(txt);
        while not eof(txt)do
        begin
            with v do
            begin
                Readln(txt,cod,anio,mes,dia,monto,nom);
                Readln(txt,ape);
                Write(mae,v);
            end;
        end;
        writeln('Archivo "ArchivoVentas" creado con exito!');
        close(txt);
        close(mae);
    end;

    procedure leer (var mae:ArchivoMaestro;var reg:venta);
    begin
        if(not eof(mae))then Read(mae,reg)
        else reg.cod := valoralto;
    end;

    procedure informarMaestro(var mae:ArchivoMaestro);
    var
        tot_anual,tot_mes,tot_monto_ventas:real;
        anio_act,cod_act,mes_act:integer;
        v:venta;
    begin
        reset(mae);
        tot_monto_ventas:=0;
        leer(mae,v);
        while(v.cod<>valoralto)do
        begin
            writeln();
            writeln('CLIENTE:',v.cod,', NOMBRE:',v.nom,', APELLIDO:',v.ape);
            cod_act:=v.cod;
            while(cod_act = v.cod)do
            begin
                writeln('ANIO: ',v.anio);
                anio_act:=v.anio;
                tot_anual:=0;
                while(cod_act = v.cod)and(anio_act = v.anio)do
                begin
                    mes_act:=v.mes;
                    tot_mes:=0;
                    while(cod_act = v.cod)and(anio_act = v.anio)and(mes_act = v.mes)do
                    begin
                        tot_mes:=tot_mes + v.monto;
                        leer(mae,v);
                    end;
                    if(tot_mes<>0)then
                    begin
                        writeln('mes:',mes_act, ', monto:',tot_mes:0:2);
                        tot_anual:=tot_anual+tot_mes;
                    end;
                end;
                writeln('monto anual: ',tot_anual:0:2);
                tot_monto_ventas:=tot_monto_ventas+tot_anual;
            end;
        end;
        writeln('MONTO TOTAL DE VENTAS DE LA EMPRESA: ',tot_monto_ventas:0:2);
        close(mae);
    end;

var
    maestro:ArchivoMaestro;
BEGIN
    crearMaestro(maestro);
    informarMaestro(maestro);
END.