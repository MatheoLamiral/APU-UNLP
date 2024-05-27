program P2p7;
uses
    SysUtils;
const
    valoralto = 999;
    dimF = 3;//= 10;
type
    subRango = 1..dimF;

    casos_mun=record
        cod_loc:integer;
        cod_cepa:integer;
        activos:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;

    casos_minis=record
        cod_loc:integer;
        nom_loc:string;
        cod_cepa:integer;
        nom_cepa:string;
        activos:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;

    ArchivoMaestro = file of casos_minis;
    ArchivoDetalle = file of casos_mun;
    vecDetalles = array [subRango] of ArchivoDetalle;
    vecRegistros = array [subRango] of casos_mun;

    procedure crearUnDetalle(var det:ArchivoDetalle;nombre_txt:string;nombre_binario:string);
    var
        t: text;
        c: casos_mun;
    begin
        assign(t, nombre_txt);
        reset(t);
        assign(det, nombre_binario);
        rewrite(det);
        while(not eof(t)) do
            begin
                with c do
                    begin
                        readln(t, cod_loc, cod_cepa, activos, nuevos, recuperados, fallecidos);
                        write(det, c);
                    end;
            end;
        writeln('Archivo binario detalle creado con exito!');
        close(det);
        close(t);
    end;

    procedure crearDetalles(var v: vecDetalles);
    var
        i: integer;
    begin
        for i:= 1 to dimF do 
            crearUnDetalle(v[i],'det'+IntToStr(i)+'.txt','detalle'+IntToStr(i));
    end;

    procedure crearMaestro(var mae: ArchivoMaestro);
    var
        txt: text;
        c: casos_minis;
    begin
        assign(txt, 'maestro.txt');
        reset(txt);
        assign(mae, 'Archivo_casos_ministerio');
        rewrite(mae);
        while(not eof(txt)) do
            begin
                with c do
                    begin
                        readln(txt, cod_loc, cod_cepa, activos, nuevos, recuperados, fallecidos, nom_cepa);
                        readln(txt, nom_loc);
                        write(mae, c);
                    end;
            end;
        writeln('Archivo binario "Archivo_casos_ministerio" creado con exito!');
        close(txt);
        close(mae);
    end;

    procedure leer(var det: ArchivoDetalle; var reg: casos_mun);
    begin
        if(not eof(det)) then
            read(det, reg)
        else
            reg.cod_loc := valoralto;
    end;

    procedure minimo(var v: vecDetalles; var vecReg: vecRegistros; var min: casos_mun);
    var
        i, pos: subrango;
    begin
        min.cod_loc:= valoralto;
        for i:= 1 to dimF do
        begin
            if (vecReg[i].cod_loc < min.cod_loc) or ((vecReg[i].cod_loc = min.cod_loc) and (vecReg[i].cod_cepa < min.cod_cepa)) then
            begin
                min:= vecReg[i];
                pos:= i;
            end;
        end;
        if(min.cod_loc <> valoralto) then
            leer(v[pos], vecReg[pos]);
    end;

    procedure actualizarMaestro(var mae: ArchivoMaestro; var v: vecDetalles);
    var
        min:casos_mun;
        reg_mae: casos_minis;
        vecReg: vecRegistros;
        i: subrango;
        cant_casos,cant_loc:integer;
    begin
        reset(mae);
        for i:= 1 to dimF do
            begin
                reset(v[i]);
                leer(v[i], vecReg[i]);
            end;
        minimo(v, vecReg, min);
        cant_loc:=0;
        if(not eof(mae))then 
            Read(mae,reg_mae);
        while(min.cod_loc <> valoralto)do
        begin
            cant_casos:=0;
            //mientras no encuentre la localidad 
            while(reg_mae.cod_loc <> min.cod_loc)and(not eof(mae))do 
                Read(mae,reg_mae);
            //mientras estoy en la misma localidad 
            while(reg_mae.cod_loc = min.cod_loc)do
            begin
                //mientras no encuantre la sepa 
                while(reg_mae.cod_cepa <> min.cod_cepa)and(not eof(mae))do 
                    Read(mae,reg_mae);
                while(reg_mae.cod_loc = min.cod_loc)and(reg_mae.cod_cepa = min.cod_cepa)do
                begin
                    writeln('holaaaaa');
                    reg_mae.fallecidos:= reg_mae.fallecidos + min.fallecidos;

                    reg_mae.recuperados:= reg_mae.recuperados + min.recuperados;

                    cant_casos:= cant_casos + min.activos;

                    reg_mae.activos:= min.activos;

                    reg_mae.nuevos:= min.nuevos;

                    minimo(v, vecReg, min);
                    writeln('sigooo');
                end;
                writeln('cantidad de casos en la localidad: ',cant_casos);
                seek(mae,filepos(mae)-1);
                write(mae, reg_mae);
            end;
            if(cant_casos>50)then cant_loc:=cant_loc+1;
        end;
        close(mae);
        for i:= 1 to dimF do
            close(v[i]);
        writeln('la cantidad de localidades con mas de 50 casos activos es ',cant_loc);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:casos_minis;
	begin
		reset(arch);
        writeln('ARCHIVO MAESTRO');
		while(not eof(arch))do
		begin
			Read(arch,reg);
			writeln('CODLOC:',reg.cod_loc,', CODCEPA:',reg.cod_cepa,', LOC:',reg.nom_loc,', CEPA:',reg.nom_cepa,
                    ', ACTIVOS:',reg.activos,', NUEVOS:',reg.nuevos,', RECUPERADOS:',reg.recuperados,', FALLECIDOS:',reg.fallecidos);
		end;
		close(arch);
	end;

    Procedure imprimirUnArchivoDetalle(var arch:ArchivoDetalle);
	var
		reg:casos_mun;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			writeln('CODLOC:',reg.cod_loc,', CODCEPA:',reg.cod_cepa,', ACTIVOS:',reg.activos,', NUEVOS:',reg.nuevos,', RECUPERADOS:',reg.recuperados,', FALLECIDOS:',reg.fallecidos);
		end;
		close(arch);
	end;

    Procedure imprimirDetalles(var v:vecDetalles);
    var
        i:integer;
    begin
        for i:=1 to dimF do
        begin
            writeln('DETALLE ', i);
            imprimirUnArchivoDetalle(v[i]);
        end;
    end;

var
    maestro:ArchivoMaestro;
    detalles:vecDetalles;
BEGIN
    crearMaestro(maestro);
    crearDetalles(detalles);
    imprimirDetalles(detalles);
    actualizarMaestro(maestro,detalles);
    imprimirArchivo(maestro);
END.
