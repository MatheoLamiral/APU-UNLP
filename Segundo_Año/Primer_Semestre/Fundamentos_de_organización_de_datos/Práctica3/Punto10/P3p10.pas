Program P3p10;

type

    mesa_electoral=record
        cod_loc:integer;
        num:integer;
        votos:integer;
    end;

    reg_aux=record
        cod_loc:integer;
        votos_totales:integer;
    end;

    archivoMesas = file of mesa_electoral;
    archivoAuxiliar = file of reg_aux;

    Procedure crearArchivo(var arch:archivoMesas);
    var
        txt:text;
        m:mesa_electoral;
    begin
        Assign(txt,'carga.txt');
        Assign(arch,'archivoMesas');
        reset(txt);
        rewrite(arch);
        while(not eof(txt))do
        begin
            with m do
            begin
                Readln(txt,m.cod_loc,m.num,m.votos);
                Write(arch,m);    
            end;
        end;
        close(txt);
        close(arch);
    end;

    Procedure imprimirAuxiliar(var arch:archivoAuxiliar);
    var
        aux:reg_aux;
        votos_generales:integer;
    begin 
        reset(arch);
        votos_generales:=0;
        while(not eof(arch))do
        begin
            Read(arch,aux);
            writeln('CODIGO_LOCALIDAD:',aux.cod_loc,', TOTAL_VOTOS:',aux.votos_totales);
            votos_generales:=votos_generales+aux.votos_totales;
        end;
        writeln('TOTAL GENERAL DE VOTOS:',votos_generales);
        close(arch);
    end;

    Procedure informar(var arch:archivoMesas);
    var 
        auxiliar:archivoAuxiliar;
        aux:reg_aux;
        m:mesa_electoral;
        encontre:boolean;
        votos_generales:integer;
    begin
        Assign(auxiliar,'archivoAuxiliar');
        rewrite(auxiliar);
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,m);
            encontre:=false;
            seek(auxiliar,0);
            while(not eof(auxiliar))and(not encontre)do
            begin
                Read(auxiliar,aux);
                if(aux.cod_loc = m.cod_loc)then encontre:=true;
            end;
            if(encontre)then
            begin
                aux.votos_totales:=aux.votos_totales+m.votos;
                seek(auxiliar,filepos(auxiliar)-1);
            end
            else begin
                aux.cod_loc:=m.cod_loc;
                aux.votos_totales:=m.votos;
            end;
            Write(auxiliar,aux);           
        end;
        close(arch);
        close(auxiliar);
        imprimirAuxiliar(auxiliar);
    end;
var
    archivo:archivoMesas;
BEGIN
    crearArchivo(archivo);
    informar(archivo);
END.