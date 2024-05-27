program P2p6;
uses
    SysUtils;
const
    valoralto = 999;
    dimF = 3;//= 5;
type
    subRango = 1..dimF;

    sesion = record
        cod: integer;
        tiempo: real;
        fecha: string;
    end;

    ArchivoMaestro = file of sesion;
    ArchivoDetalle = file of sesion;
    vecDetalles = array [subRango] of ArchivoDetalle;
    vecRegistros = array [subRango] of sesion;

    procedure crearUnDetalle(var det:ArchivoDetalle;nombre_txt:string;nombre_binario:string);
    var
        t: text;
        s: sesion;
    begin
        assign(t, nombre_txt);
        reset(t);
        assign(det, nombre_binario);
        rewrite(det);
        while(not eof(t)) do
            begin
                with s do
                    begin
                        readln(t, cod, tiempo, fecha);
                        write(det, s);
                    end;
            end;
        writeln('Archivo binario detalle creado con exito!');
        close(det);
        close(t);
    end;

    procedure crearDetalles(var vec: vecDetalles);
    var
        i: integer;
    begin
        for i:= 1 to dimF do 
            crearUnDetalle(vec[i],'det'+IntToStr(i)+'.txt','detalle'+IntToStr(i));
    end;


    procedure leer(var det: ArchivoDetalle; var reg: sesion);
    begin
        if(not eof(det)) then
            read(det, reg)
        else
            reg.cod := valoralto;
    end;

    procedure minimo(var v: vecDetalles; var vecReg: vecRegistros; var min: sesion);
    var
        i, pos: subrango;
    begin
        min.cod:= valoralto;
        min.fecha:= 'ZZZZ';
        for i:= 1 to dimF do
            if (vecReg[i].cod < min.cod) or ((vecReg[i].cod = min.cod) and (vecReg[i].fecha < min.fecha)) then
                begin
                    min:= vecReg[i];
                    pos:= i;
                end;
        if(min.cod <> valoralto) then
            leer(v[pos], vecReg[pos]);
    end;

    procedure crearMaestro(var mae: ArchivoMaestro; var v: vecDetalles);
    var
        min, aux: sesion;
        vecReg: vecRegistros;
        i: subrango;
    begin
        Assign(mae, 'ArchivoSesiones');
        rewrite(mae);
        for i:= 1 to dimF do
            begin
                reset(v[i]);
                leer(v[i], vecReg[i]);
            end;
        minimo(v, vecReg, min);
        while(min.cod <> valoralto) do
            begin
                aux.cod:= min.cod;
                while(aux.cod = min.cod) do
                    begin
                        aux.fecha:= min.fecha;
                        aux.tiempo:= 0;
                        while(aux.cod = min.cod) and (aux.fecha = min.fecha) do
                            begin
                                aux.tiempo:= aux.tiempo + min.tiempo;
                                minimo(v, vecReg, min);
                            end;
                        write(mae, aux);
                    end;
            end;
        close(mae);
        for i:= 1 to dimF do
            close(v[i]);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:sesion;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			writeln('COD:',reg.cod,', TOTAL_HORAS:',reg.tiempo:0:2,', FECHA:',reg.fecha);
		end;
		close(arch);
	end;

var
    detalles: vecDetalles;
    maestro: ArchivoMaestro;
BEGIN
    crearDetalles(detalles);
    crearMaestro(maestro, detalles);
    imprimirArchivo(maestro);
    writeln('holaa');
END.
