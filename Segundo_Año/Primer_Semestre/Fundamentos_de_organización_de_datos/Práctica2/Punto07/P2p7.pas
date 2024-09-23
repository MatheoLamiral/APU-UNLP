Program P2p7;

uses
    SysUtils;
const
    valoralto = 9999;
    dimF = 3;//= 10;
type 

    casos_ministerio = record
        cod_loc:integer;
        nom_loc:string;
        cod_cepa:integer;
        nom_cepa:string;
        activos:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;

    casos_municipio = record
        cod_loc:integer;
        cod_cepa:integer;
        activos:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;
    
    Detalle = file of casos_municipio;
    Maestro = file of casos_ministerio;

    vecDetalles = array [1..dimF] of Detalle;
    vecRegistros = array [1..dimF] of casos_municipio;

    Procedure cargarMaestro(var mae:Maestro);
    var
        txt:text;
        regMae:casos_ministerio;
    begin
        Assign(txt,'maestro.txt');
        Assign(mae,'archivoMinisterio.dat');
        reset(txt);
        rewrite(mae);
        while(not eof(txt))do
        begin
            with regMae do
            begin
                Readln(txt,cod_loc,cod_cepa,activos,nuevos,recuperados,fallecidos,nom_cepa);
                Readln(txt,nom_loc);
            end;
            Write(mae,regMae);
        end;
        writeln('Archivo "archivoMinisterio.dat" creado con exito!');
        close(txt);
        close(mae);
    end;

    Procedure imprimirMaestro(var mae:Maestro);
    var
        regMae:casos_ministerio;
    begin
        reset(mae);
        while(not eof(mae))do
        begin
            Read(mae,regMae);
            with regMae do
            begin
                writeln('CODIGO DE LOCALIDAD: ', cod_loc, ', NOMBRE DE LOCALIDAD: ', nom_loc,
                 ', CODIGO DE CEPA: ', cod_cepa, ', NOMBRE DE CEPA: ', nom_cepa, ', ACTIVOS: ',
                  activos, ', NUEVOS: ', nuevos, ', RECUPERADOS: ', recuperados, ', FALLECIDOS: ',
                   fallecidos);
            end;
        end;
        close(mae);
    end;

    Procedure cargarUnDetalle(var det:Detalle;nombre:string);
    var
        txt:text;
        regDet:casos_municipio;
    begin
        Assign(txt,nombre+'.txt');
        Assign(det,nombre+'.dat');
        reset(txt);
        rewrite(det);
        while(not eof(txt))do
        begin
            with regDet do
            begin
                Readln(txt,cod_loc,cod_cepa,activos,nuevos,recuperados,fallecidos);
            end;
            Write(det,regDet);
        end;
        writeln('Archivo "',nombre,'.dat " creado con exito!');
        close(txt);
        close(det);
    end;

    Procedure cargarDetalles(var vec:vecDetalles);
    var
        i:integer;
    begin
        for i:= 1 to dimF do
        begin
            cargarUnDetalle(vec[i],'det'+IntToStr(i));
        end;
    end;

    Procedure imprimirDetalles(var vec:vecDetalles);
    var
        i:integer;
        regDet:casos_municipio;
    begin
        for i:= 1 to dimF do
        begin
            writeln('***ARCHIVO DETALLE ',i,'***');
            reset(vec[i]);
            while(not eof(vec[i]))do
            begin
                Read(vec[i],regDet);
                with regDet do
                begin
                    writeln('CODIGO DE LOCALIDAD: ', cod_loc, ', CODIGO DE CEPA: ', cod_cepa,
                     ', ACTIVOS: ', activos, ', NUEVOS: ', nuevos, ', RECUPERADOS: ', recuperados,
                      ', FALLECIDOS: ', fallecidos);
                end;
            end;
            close(vec[i]);
            writeln('----------------------------');
        end;
    end;

    Procedure leerDet(var det:Detalle;var regDet:casos_municipio);
    begin
        if(not eof(det))then
            read(det,regDet)
        else
            regDet.cod_loc:=valoralto;
    end;

    Procedure leerMae(var mae:Maestro;var regMae:casos_ministerio);
    begin
        if(not eof(mae))then
            read(mae,regMae)
        else
            regMae.cod_loc:=valoralto;
    end;

    Procedure minimo(var vd:vecDetalles;var vr:vecRegistros;var min:casos_municipio);
    var 
        i,pos:integer;
    begin
        min.cod_loc:=valoralto;
        for i:= 1 to dimF do
        begin
            //si encunetro una localidad con menor codigo o si tienen el mismo codigo pero la cepa es menor
            if(vr[i].cod_loc < min.cod_loc)or((min.cod_loc = min.cod_loc)and(vr[i].cod_cepa < min.cod_cepa))then
            begin
                //actualizo el minimo
                min:=vr[i];
                //me guardo la posicion del minimo
                pos:=i;
            end;
        end;
        //si el minimo no es valoralto
        if(min.cod_loc <> valoralto)then
            //actualizo el vector de registros con el siguiente registro del archivo detalle
            leerDet(vd[pos],vr[pos]);
    end;

    Procedure actualizarMaestro(var mae:Maestro;var vd:vecDetalles);
    var
        regMae:casos_ministerio;
        vr:vecRegistros;
        min:casos_municipio;
        i,cant_localidades,total_casos_localidad:integer;
    begin
        for i:= 1 to dimF do
        begin
            reset(vd[i]);
            leerDet(vd[i],vr[i]);
        end;
        reset(mae);
        minimo(vd,vr,min);
        leerMae(mae,regMae);
        cant_localidades:=0;
        //mientras no se termine el detalle
        while(min.cod_loc <> valoralto)do
        begin
            //mientras no encuentre la misma localidad
            while(regMae.cod_loc <> min.cod_loc)do
                leerMae(mae,regMae);
            //mientras este en la misma localidad
            while (regMae.cod_loc = min.cod_loc)do
            begin
                total_casos_localidad := regMae.activos;
                //mientras no encuentre la misma cepa
                while(regMae.cod_cepa <> min.cod_cepa)do
                    leerMae(mae,regMae);
                //mientras este en la misma localidad y en la misma cepa
                while(regMae.cod_loc = min.cod_loc)and(regMae.cod_cepa = min.cod_cepa)do
                begin
                    regMae.fallecidos:= regMae.fallecidos + min.fallecidos;
                    regMae.recuperados:= regMae.recuperados + min.recuperados;
                    regMae.activos:= min.activos;
                    regMae.nuevos:= min.nuevos;
                    minimo(vd,vr,min);
                    total_casos_localidad:= total_casos_localidad + min.activos - (min.fallecidos + min.recuperados);
                    writeln('es en el tercer while');
                end;
                seek(mae,(filepos(mae))-1);
                Write(mae,regMae);
                writeln('es en el segundo while');
            end;
            writeln('es en el primer while');
            if(total_casos_localidad > 50)then
                cant_localidades:= cant_localidades + 1;
        end;
        writeln('La cantidad de localidades con mas de 50 casos activos es: ',cant_localidades);
    end;

var
    mae:Maestro;
    vd:vecDetalles;
begin
    cargarMaestro(mae);
    //imprimirMaestro(mae);
    cargarDetalles(vd);
    //imprimirDetalles(vd);
    actualizarMaestro(mae,vd);
end.


