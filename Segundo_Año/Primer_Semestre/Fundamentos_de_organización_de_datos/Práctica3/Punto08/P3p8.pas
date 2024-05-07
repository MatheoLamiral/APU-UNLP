Program P3p8;

type 

    distribucion = record
            nom:string;//no puede repetirse 
            anio:integer;
            version:real;
            cant_des:integer;
            desc:string;
        end;

    archivoDistribuciones = file of distribucion;

    procedure leerDistribucion(var d: distribucion);
    begin
        writeln('Ingrese el nombre de la distribucion');
        readln(d.nom);
        if(d.nom <> 'fin') then
            begin
                writeln('Ingrese el anio de lanzamiento');
                readln(d.anio);
                writeln('Ingrese el numero de version de kernel');
                readln(d.version);
                writeln('Ingrese la cantidad de desarrolladores');
                readln(d.cant_des);
                writeln('Ingrese la descripcion');
                readln(d.desc);
            end;
    end;

    procedure crearArchivo(var arch: archivoDistribuciones);
    var
        d: distribucion;
    begin
        Assign(arch, 'ArchivoMaestro');
        rewrite(arch);
        d.nom:= '';
        d.cant_des:= 0;
        d.anio:= 0;
        d.version:= 0;
        d.desc:= '';
        write(arch, d);
        leerDistribucion(d);
        while(d.nom <> 'fin') do
            begin
                write(arch, d);
                leerDistribucion(d);
            end;
        close(arch);
    end;

    Procedure imprimirArchivo(var arch:archivoDistribuciones);
    var
        d:distribucion;
    begin
        writeln('***ARCHIVO***');
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,d);
            writeln('NOM:',d.nom,', ANIO:',d.anio,
                ', VERSION:',d.version:0:2,', DESARROLLADORES:',d.cant_des,', DESC:',d.desc);
        end;
        close(arch);
    end;

    Procedure existeDistribucion(var arch:archivoDistribuciones;nom:string;var existe:boolean);
    var
        d:distribucion;
    begin
        existe:=false;
        reset(arch);
        while(not eof(arch))and(not existe)do
        begin
            Read(arch,d);
            if(d.nom=nom)then
                existe:=true;
        end;
        close(arch);
    end;

    Procedure altaDistribucion(var arch:archivoDistribuciones);
    var 
        existe:boolean;
        d,cab,aux:distribucion;
    begin
        leerDistribucion(d);
        existeDistribucion(arch,d.nom,existe);
        if(not existe)then
        begin
            reset(arch);
            //leo la cabecera
            Read(arch,cab);
            //si es cero agrego al final 
            if(cab.cant_des=0)then
            begin
                seek(arch,filesize(arch));
                Write(arch,d);
            end
            else begin
                //me posiciono en la direccion efectiva de la cabecera
                seek(arch,cab.cant_des*-1);
                //leo el registro(cant_des=0)
                Read(arch,aux);
                //vuelvo una posicion para atras
                seek(arch,filepos(arch)-1);
                //Agrego el registro 
                Write(arch,d);
                //vuelvo a la cabecera 
                seek(arch,0);
                //copio el registro con cod=0
                Write(arch,aux);
            end;
            close(arch);
        end
        else writeln('Ya existe la distribucion');
    end;

    Procedure bajaDistribucion(var arch:archivoDistribuciones);
    var
        nom:string;
        existe:boolean;
        pos:integer;
        d,cab:distribucion;
    begin
        writeln('ingrese el nombre de la distribucion a borrar:');
        readln(nom);
        existeDistribucion(arch,nom,existe);
        if(existe)then
        begin
            reset(arch);
            //leo la cabecera
            Read(arch,cab);
            //busco el registro 
            while(d.nom<>nom)do Read(arch,d);
            //guardo la posicion del registro
            pos:=filepos(arch)-1;
            //me posiciono en el registro
            seek(arch,pos);
            //sobreescribo el registro con lo que tenia la cabecera
            Write(arch,cab);
            //voy a la cabecerea
            seek(arch,0);
            //escribo en el campo correspondiente la direccion del registro por -1
            d.cant_des:=pos*-1;
            //escribo en la cabecera
            write(arch,d);
            close(arch);
        end
        else writeln('Distribucion no existente');
    end;

var 
    archivo:archivoDistribuciones;
BEGIN
    crearArchivo(archivo);
    imprimirArchivo(archivo);
    altaDistribucion(archivo);
    imprimirArchivo(archivo);
    bajaDistribucion(archivo);
    imprimirArchivo(archivo);
END.