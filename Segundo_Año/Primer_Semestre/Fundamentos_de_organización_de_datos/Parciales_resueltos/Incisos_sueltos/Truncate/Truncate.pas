Program  IncisoTruncate;

Uses 
    sysutils;
const
    valoralto = 9999;
type 

    prenda = record
        cod: integer;
        desc: string;
        colores: string;
        tipo: string;   
        stock: integer;
        precio: real;
    end;

    ArchivoPrendas = file of prenda;
    ArchivoPrendasObsoletas = file of integer;

    procedure cargarPrendas(var arch: ArchivoPrendas);
    var
        p: prenda;
        i: integer;
    begin
        Assign(arch, 'prendas.dat');
        rewrite(arch);
        for i := 1 to 10 do
        begin
            with p do
            begin
                cod := i;
                desc := 'Prenda ' + IntToStr(i);
                colores := 'Color ' + IntToStr(i);
                tipo := 'Tipo ' + IntToStr(i);
                stock := i * 10;
                precio := i * 1.5;
            end;
            write(arch, p);
        end;
        close(arch);
    end;

    procedure cargarPrendasObsoletas(var arch: ArchivoPrendasObsoletas);
    var
        i: integer;
    begin
        Assign(arch, 'prendasObsoletas.dat');
        rewrite(arch);
        for i := 1 to 4 do
        begin
            write(arch, i*2);
        end;
        close(arch);
    end;

    Procedure leerMae(var arch: ArchivoPrendas; var dato: prenda);
    begin
        if (not eof(arch)) then
            read(arch, dato)
        else
            dato.cod := valoralto;
    end;

    Procedure leerDet(var arch: ArchivoPrendasObsoletas; var dato: integer);
    begin
        if (not eof(arch)) then
            read(arch, dato)
        else
            dato := valoralto;
    end;

    Procedure bajaLogica(var maestro: ArchivoPrendas; var detalle: ArchivoPrendasObsoletas);
    var
        reg_mae: prenda;
        cod_obs: integer;
    begin
        reset(maestro);
        reset(detalle);
        leerDet(detalle, cod_obs);
        leerMae(maestro, reg_mae);
        //mientras no se termine el archivo de detalle
        while(cod_obs <> valoralto)do 
        begin
            //mientras no encuentre el codigo de detalle en el maestro
            while(reg_mae.cod <> cod_obs)do
                leerMae(maestro, reg_mae);
            //si lo encontre
            if(reg_mae.cod = cod_obs)then
            begin
                //pongo el stock en negativo
                reg_mae.stock := reg_mae.stock * -1;
                //escribo en el maestro
                seek(maestro, filepos(maestro) - 1);
                write(maestro, reg_mae);
            end;
            //leo una nueva posicion de detalle
            leerDet(detalle, cod_obs);
            //vuelvo al inicio del maestro
            seek(maestro, 0);
        end;  
        close(maestro);
    end;

    Procedure imprimirMaestro(var arch: ArchivoPrendas);
    var
        reg: prenda;
    begin
        reset(arch);
        writeln('***ARCHIVO MAESTRO***');
        while(not eof(arch))do
        begin
            read(arch, reg);
            with reg do
            begin
                writeln('CODIGO: ', cod, ' DESCRIPCION: ', desc, ' COLORES: ', colores,
                 ' TIPO: ', tipo, ' STOCK: ', stock, ' PRECIO: ', precio:0:2);
            end;
        end;
        writeln('------------------------------------------------------');
        close(arch);
    end;    

    Procedure imprimirDetalle(var arch: ArchivoPrendasObsoletas);   
    var
        cod: integer;
    begin
        reset(arch);
        writeln('***ARCHIVO DETALLE***');
        while(not eof(arch))do
        begin
            read(arch, cod);
            writeln('CODIGO: ', cod);
        end;
        writeln('-----------------------');
        close(arch);
    end;

    Procedure compactar(var mae: ArchivoPrendas);
    var
        reg_mae, aux: prenda;
        pos: integer;
    begin
        reset(mae);
        leerMae(mae, reg_mae);
        while(reg_mae.cod <> valoralto)do
        begin
            writeln('estoy en loope');
            if(reg_mae.stock < 0)then
            begin
                // me guerdo la posicion
                pos:= filepos(mae)-1;
                //voy al ultimo elemento del archivo
                seek(mae, filesize(mae)-1);
                //leo el ultimo elemento
                Read(mae, aux);
                //vuelvo hacia atras
                seek(mae,filepos(mae)-1);
                //escribo el registro a eliminar en la ultima posicion
                while(reg_mae.cod < 0)do
                begin
                    seek(mae, filepos(mae)-2);
                    leerMae(mae, reg_mae);
                end;
                truncate(mae);
                seek(mae, pos);
                write(mae, aux);
                leerMae(mae, reg_mae);
            end;
        end;
        close(mae);
    end;

var 
    maestro: ArchivoPrendas;
    detalle: ArchivoPrendasObsoletas;
begin
    cargarPrendas(maestro);
    imprimirMaestro(maestro);
    cargarPrendasObsoletas(detalle);
    imprimirDetalle(detalle);
    bajaLogica(maestro, detalle);
    imprimirMaestro(maestro);
    compactar(maestro);
    imprimirMaestro(maestro);
end.