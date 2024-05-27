Program P2p14;
uses
    SysUtils;
const
    dimF = 2;//10
    valoralto = 9999;
type
    //ordenados por cod_prov y cod_loc
    infoMaestro = record
      cod_prov:integer;
      nom_prov:string;
      cod_loc:integer;
      nom_loc:string;
      sin_luz:integer;
      sin_gas:integer;
      sin_agua:integer;
      chapa:integer;
      sin_sanitarios:integer;
    end;
    //ordenados por cod_prov y cod_lo
    infoDetalle = record
      cod_prov:integer;
      cod_loc:integer;
      con_luz:integer;
      construidas:integer;
      con_gas:integer;
      con_agua:integer;
      entrega_sanitarios:integer;
    end;

    archivoMaestro=file of infoMaestro;
    archivoDetalle=file of infoDetalle;
    vecDetalles=array[1..dimF]of archivoDetalle;
    vecRegistros=array[1..dimF]of infoDetalle;

    Procedure crearMaestro(var arch:archivoMaestro);
    var
        txt:text;
        regMae:infoMaestro;
    begin
        Assign(txt,'maestro.txt');
        Assign(arch,'archivoProvincias');
        reset(txt);
        rewrite(arch);
        while(not eof(txt))do
        begin
            with regMae do
            begin
                Readln(txt,cod_prov,nom_prov);
                Readln(txt,cod_loc,sin_luz,sin_gas,chapa,sin_agua,sin_sanitarios,nom_loc);
                Write(arch,regMae);
            end;
        end;
        writeln('Archivo "archivoProvincias" creado con exito!');
        close(txt);
        close(arch);
    end;

    Procedure crearUnDetalle(var det:archivoDetalle;nombre:string);
    var
        txt:text;
        regDet:infoDetalle;
    begin
        Assign(txt,nombre+'.txt');
        Assign(det,nombre);
        reset(txt);
        rewrite(det);
        while(not eof(txt))do
        begin
            with regDet do
            begin
                Readln(txt,cod_prov,cod_loc,con_luz,construidas,con_agua,con_gas,entrega_sanitarios);
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
        reg:infoMaestro;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(arch);
        while not eof(arch)do
        begin
            Read(arch,reg);
            writeln('CODPROV:',reg.cod_prov,', NOMPROV:',reg.nom_prov,
                    ', CODLOC:',reg.cod_loc,', NOMLOC:',reg.nom_loc,
                    ', SIN LUZ:',reg.sin_luz,', SIN GAS:',reg.sin_gas,', SIN AGUA:',reg.sin_agua,
                    ', CHAPA:',reg.chapa,', SIN SANITARIOS:',reg.sin_sanitarios);
        end;
        close(arch);
    end;

    procedure leerMae(var arch:archivoMaestro;var reg:infoMaestro);
    begin
        if(not eof(arch))then
        begin
            Read(arch,reg);
        end
        else reg.cod_prov := valoralto;
    end;

    procedure leerDet(var arch:archivoDetalle;var reg:infoDetalle);
    begin
        if(not eof(arch))then
        begin
            Read(arch,reg);
        end
        else reg.cod_prov := valoralto;
    end;

    procedure imprimirDetalles(var vec:vecDetalles);
    var
        i:integer;
        reg:infoDetalle;
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
                    writeln('CODPROV:',reg.cod_prov,', CODLOC:',reg.cod_loc,
                            ', CON LUZ:',reg.con_luz,', CON GAS:',reg.con_gas,', CON AGUA:',reg.con_agua,
                            ', CONSTRUIDAS:',reg.construidas,',ENTREGA SANITARIOS:',reg.entrega_sanitarios);
                end;
            end;
            close(vec[i]);
            writeln('------------');
        end;
    end;

    procedure minimo(var v: vecDetalles; var vecReg: vecRegistros; var min: infoDetalle);
    var
        i, pos: integer;
    begin
        min.cod_prov:= valoralto;
        for i:= 1 to dimF do
            if (vecReg[i].cod_prov < min.cod_prov) or ((vecReg[i].cod_prov = min.cod_prov) and (vecReg[i].cod_loc < min.cod_loc)) then
            begin
                min:= vecReg[i];
                pos:= i;
            end;
        if(min.cod_prov <> valoralto) then
            leerDet(v[pos], vecReg[pos]);
    end;

    Procedure actualizarMaestro(var mae:archivoMaestro;var det:vecDetalles;var reg:vecRegistros);
    var
        reg_mae:infoMaestro;
        min:infoDetalle;
        i,cant_sin_chapa:integer;
    begin
        cant_sin_chapa := 0;
        for i := 1 to dimF do
        begin
            reset(det[i]);
            leerDet(det[i],reg[i]);
        end;
        reset(mae);
        minimo(det,reg,min);
        leerMae(mae,reg_mae);
        //mientras no se termine el archivo 
        while(reg_mae.cod_prov <> valoralto)do
        begin
            //mientras no encuentre la misma provincia y no se termine el archivo 
            while(reg_mae.cod_prov <> min.cod_prov )and(reg_mae.cod_prov <> valoralto)do leerMae(mae,reg_mae);
            //mientras este en la misma provincia y no se termine el archivo 
            while(reg_mae.cod_prov = min.cod_prov)and(reg_mae.cod_prov<>valoralto)do
            begin
                //mientras no encuentre la localidad y no se termine el archivo 
                while(reg_mae.cod_loc <> min.cod_loc )and(reg_mae.cod_prov <> valoralto)do leerMae(mae,reg_mae);
                //mientras este en la misma provincia, misma localidad y no se termine el archivo
                while(reg_mae.cod_prov = min.cod_prov)and(reg_mae.cod_loc = min.cod_loc)and(reg_mae.cod_prov<>valoralto)do
                begin
                    reg_mae.sin_luz := reg_mae.sin_luz - min.con_luz;
                    reg_mae.sin_agua := reg_mae.sin_agua - min.con_agua;
                    reg_mae.sin_gas := reg_mae.sin_gas - min.con_gas;
                    reg_mae.sin_sanitarios := reg_mae.sin_sanitarios - min.entrega_sanitarios;
                    reg_mae.chapa := reg_mae.chapa - min.construidas;
                    minimo(det,reg,min);
                end;
                if(reg_mae.chapa=0)then cant_sin_chapa:= cant_sin_chapa + 1; 
                seek(mae,(filepos(mae))-1);
                Write(mae,reg_mae);
            end;
        end;
        writeln('La cantidad de localidades sin viviendas de chapa es ',cant_sin_chapa);
        for i := 1 to dimF do
        begin
            close(det[i]);
        end;
        close(mae);
    end;

var
    maestro:archivoMaestro;
    detalles:vecDetalles;
    registros:vecRegistros;
BEGIN
    crearMaestro(maestro);
    crearDetalles(detalles);
    imprimirMaestro(maestro);
    imprimirDetalles(detalles);
    actualizarMaestro(maestro,detalles,registros);
    imprimirMaestro(maestro);
END.