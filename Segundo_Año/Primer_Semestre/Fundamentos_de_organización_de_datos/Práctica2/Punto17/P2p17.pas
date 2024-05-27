program P2p17;
const
    valoralto = -1;
type
    casos_diarios=record
        cod_loc:integer;
        nom_loc:string;
        cod_mun:integer;
        nom_mun:string;
        cod_hp:integer;
        nom_hp:string;
        fecha:string;
        positivos:integer;
    end;

    ArchivoDetalle=file of casos_diarios;

    Procedure cargarDetalle(var det:ArchivoDetalle);
    var
        t:text;
        c:casos_diarios;
    begin
        Assign(t,'detalle.txt');
        Assign(det,'casos_diarios.dat');
        reset(t);
        rewrite(det);
        while(not eof(t))do
        begin
            with c do
            begin
                Readln(t,cod_loc,cod_mun,cod_hp,nom_loc);
                Readln(t,nom_mun);
                Readln(t,fecha);
                Readln(t,positivos,nom_hp);
                Write(det,c);
            end;
        end;
        writeln('Archivo "casos_diarios.dat" creado con exito!');
        close(t);
        close(det);
    end;

    Procedure Leer(var arch:ArchivoDetalle;var reg:casos_diarios);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else 
        begin
            reg.cod_loc:=valoralto;
            reg.cod_mun:=valoralto;
            reg.cod_hp:=valoralto;
        end;
    end;

    procedure agregarATxt(var t:text;c:casos_diarios);
    begin
       Writeln(t,c.nom_loc);
       Writeln(t,c.positivos,' ',c.nom_mun);  
    end;

    Procedure imprimir(var det:ArchivoDetalle);
    var
        c:casos_diarios;
        loc_act,mun_act,total_casos_loc,total_casos_mun,total_casos_prov:integer;
        t:text;
    begin
        Assign(t,'casos_diarios.txt');
        rewrite(t);
        reset(det);
        leer(det,c);
        total_casos_prov:=0;
        while(c.cod_loc<>valoralto)do
        begin
            loc_act := c.cod_loc;//localidad actual
            writeln('LOCALIDAD: ',c.nom_loc);
            total_casos_loc := 0;
            while( c.cod_loc = loc_act)do
            begin
                mun_act := c.cod_mun;//municipio actual
                writeln('    MUNICIPIO: ',c.nom_mun);
                total_casos_mun := 0;
                while(mun_act = c.cod_mun)do
                begin
                    writeln('         HOSPITAL: ',c.nom_hp,' = ',c.positivos);
                    total_casos_mun := total_casos_mun+ c.positivos;
                    leer(det,c);
                end;
                writeln('    TOTAL CASOS MUNICIPIO: ',total_casos_mun);
                total_casos_loc := total_casos_loc + total_casos_mun;
                if (total_casos_mun>1500)then agregarATxt(t,c);
            end;
            writeln('TOTAL CASOS LOCALIDAD: ',total_casos_loc);
            total_casos_prov := total_casos_prov + total_casos_loc;
        end;
        Writeln('TOTAL CASOS PROVINCIA: ',total_casos_prov);
        close(det);
        close(t);
    end;

var
    detalle:ArchivoDetalle;
BEGIN
    cargarDetalle(detalle);
    imprimir(detalle);
END.
