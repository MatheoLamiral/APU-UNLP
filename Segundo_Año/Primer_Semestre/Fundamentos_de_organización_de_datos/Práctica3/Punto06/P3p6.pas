Program P3p6;

type

    prenda = record
        cod:integer;
        desc:string;
        colores:string;
        tipo:integer;
        stock:integer;
        precio:real;
    end;

    prenda_obsoleta = record
        cod:integer;
    end;

    archivoPrendas = file of prenda;
    archivoPrendasObsoletas = file of prenda_obsoleta;

    Procedure crearMaestro(var mae:archivoPrendas);
    var
        txt:text;
        p:prenda;
    begin
        Assign(txt,'maestro.txt');
        Assign(mae,'archivoPrendas');
        reset(txt);
        rewrite(mae);
        while(not eof(txt))do
        begin
            with p do
            begin
                Readln(txt,p.cod,p.tipo,p.stock,p.precio,p.desc);
                Readln(txt,p.colores);
                Write(mae,p);
            end;
        end;
        close(txt);
        close(mae);
    end;

    Procedure crearDetalle(var det:archivoPrendasObsoletas);
    var
        txt:text;
        po:prenda_obsoleta;
    begin
        Assign(txt,'det.txt');
        Assign(det,'archivoPrendasObsoletas');
        reset(txt);
        rewrite(det);
        while(not eof(txt))do
        begin
            with po do
            begin
                Readln(txt,po.cod);
                Write(det,po);
            end;
        end;
        close(txt);
        close(det);
    end;

    Procedure imprimirMae(var mae:archivoPrendas);
    var
        p:prenda;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(mae);
        while(not eof(mae))do
        begin
            Read(mae,p);
            writeln('CODIGO:',p.cod,', DESCRIPCION:',p.desc,', COLORES:',p.colores,
                    ', TIPO:',p.tipo,', STOCK:',p.stock,', PRECIO:',p.precio:0:2);
        end;
        close(mae);
    end;

    Procedure imprimirDet(var det:archivoPrendasObsoletas);
    var
        po:prenda_obsoleta;
    begin
        writeln('***ARCHIVO DETALLE***');
        reset(det);
        while(not eof(det))do
        begin
            Read(det,po);
            writeln('CODIGO:',po.cod);
        end;
        close(det);
    end;

    Procedure bajaLogica(var det:archivoPrendasObsoletas;var mae:archivoPrendas);
    var
        reg_mae:prenda;
        reg_det:prenda_obsoleta;
        cod_act:integer;
    begin
        reset(mae);
        reset(det);
        while(not eof(det))do
        begin
            Read(det,reg_det);
            cod_act:= reg_det.cod;
            if(not eof(mae))then
                Read(mae,reg_mae);
            while(not eof(mae))and(reg_mae.cod<>cod_act)do
                Read(mae,reg_mae);
            if(reg_mae.cod=cod_act)then
            begin
                seek(mae,filepos(mae)-1);
                reg_mae.cod:=reg_mae.cod*-1;
                Write(mae,reg_mae);
            end;
        end;
        close(mae);
        close(det);
    end;

    Procedure baja(var mae:archivoPrendas;var nue_mae:archivoPrendas);
    var
        reg_mae:prenda;
    begin
        Assign(nue_mae,'archivoAuxiliar');
        reset(mae);
        rewrite(nue_mae);
        while(not eof(mae))do
        begin
            Read(mae,reg_mae);
            if(reg_mae.cod>=0)then
                Write(nue_mae,reg_mae);
        end;
        close(mae);
        close(nue_mae);
        erase(mae);
        rename(nue_mae,'archivoPrendas');
    end;

var
    maestro,nue_mae:archivoPrendas;
    detalle:archivoPrendasObsoletas;
BEGIN
    crearMaestro(maestro);
    crearDetalle(detalle);
    imprimirMae(maestro);
    imprimirDet(detalle);
    bajaLogica(detalle,maestro);
    imprimirMae(maestro);
    baja(maestro,nue_mae);
    imprimirMae(nue_mae);
END.