program P2p9;
const
    valoralto = -1;
type
    mesa_electoral = record
      cod_prov:integer;
      cod_loc:integer;
      num_mesa:integer;
      cant_votos:integer;
    end;

    ArchivoMaestro=file of mesa_electoral;

    Procedure cargarMaestro(var maestro:ArchivoMaestro);
    var
        t:text;
        m:mesa_electoral;
    begin
        Assign(t,'mesas_electorales.txt');
        Assign(maestro,'archivoMesas');
        reset(t);
        rewrite(maestro);
        while(not eof(t))do
        begin
            with m do
            begin
                Readln(t,cod_prov,cod_loc,num_mesa,cant_votos);
                Write(maestro,m);
            end;
        end;
        writeln('Archivo "archivoMesas" creado con exito!');
        close(t);
        close(maestro);
    end;

    Procedure LeerMaestro(var arch:ArchivoMaestro;var reg:mesa_electoral);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else 
        begin
            reg.cod_prov:=valoralto;
            reg.cod_loc:=valoralto;
        end;
    end;

    Procedure imprimirMaestro(var maestro:ArchivoMaestro);
    var
        m:mesa_electoral;
        total_votos_loc,total_votos_prov,prov_act,loc_act:integer;
    begin
        reset(maestro);
        leerMaestro(maestro,m);
        while(m.cod_prov<>valoralto)do
        begin
            prov_act := m.cod_prov;
            writeln('CODIGO DE PROVINCIA: ',m.cod_prov);
            total_votos_prov := 0;
            while(prov_act = m.cod_prov)do
            begin
                loc_act := m.cod_loc;
                write('CODIGO DE LOCALIDAD: ',m.cod_loc);
                total_votos_loc := 0;
                while(loc_act = m.cod_loc)do
                begin
                    total_votos_loc := total_votos_loc + m.cant_votos;
                    leerMaestro(maestro,m);
                end;
                writeln(' | TOTAL VOTOS: ',total_votos_loc);
                total_votos_prov := total_votos_prov + total_votos_loc;
            end;
            writeln('TOTAL DE VOTOS PROVINCIA: ',total_votos_prov);
        end;
        close(maestro);
    end;

var
  maestro:ArchivoMaestro;
BEGIN
  cargarMaestro(maestro);
  imprimirMaestro(maestro);
END.
