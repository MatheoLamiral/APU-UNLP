{La baja lógica coniste en marcar un registro como borrado, pero no se elimina fisicamente del archivo.
la baja física consiste en eliminar fisicamente el registro del archivo.}

Program segunda2017;

type

    sistema = record
        nom:string;
        instalaciones:integer;
        cod_abierto:boolean;
        licencia:string;
    end;

    ArchivoSistemas = file of sistema;

    Procedure leerSistema(var reg:sistema);
    var
        op:string;
    begin
        writeln('Ingrese el nombre del sistema');
        readln(reg.nom);
        if(reg.nom <> 'ZZZ')then
        begin
            writeln('Ingrese la cantidad de instalaciones');
            readln(reg.instalaciones);
            writeln('Ingrese si el codigo es abierto (si/no)');
            readln(op);
            if(op='si')then
                reg.cod_abierto:=true
            else
                reg.cod_abierto:=false;
            writeln('Ingrese la licencia');
            readln(reg.licencia);
        end;
    end;

    Procedure crearArchivo(var arch:ArchivoSistemas);
    var
        reg:sistema;
    begin
        Assign(arch,'ArchivoSistemas');
        //inicializo los valores de la cabecera
        reg.instalaciones:=0;
        reg.nom:='ZZZ';
        reg.licencia:='ZZZ';
        rewrite(arch);
        //escribo la cabecera
        Write(arch,reg);
        close(arch);
    end;

    Procedure darDeAlta(var arch:ArchivoSistemas;reg:sistema);
    var
        cab,aux:sistema;
    begin
        reset(arch);
        //leo la cabecera
        Read(arch,cab);
        //si es 0 agrego al final 
        if(cab.instalaciones=0)then
        begin
            seek(arch,filesize(arch));
            Write(arch,reg);
        end
        else begin
            //me posiciono en la dirección efectiva de la cabecera
            seek(arch,cab.instalaciones*-1);
            //leo el registro (cod=0)
            Read(arch,aux);
            //vuelvo una posición para atras
            seek(arch,filepos(arch)-1);
            //agrego el registro
            Write(arch,reg);
            //vuelvo a la cabecera
            seek(arch,0);
            //copio el registro con cod=0
            Write(arch,aux);
        end;
        close(arch);
    end;

    Procedure darDeBaja(var arch:ArchivoSistemas;reg:sistema);
    var
        cab:sistema;
        encontre:boolean;
        pos:integer;
    begin
        encontre:=false;
        reset(arch);
        //leo la cabecera
        if(not eof(arch))then Read(arch,cab);
        //mientras no encuentre el registro y no sea el final del archivo
        while((not encontre) and (not eof(arch)))do
        begin
            Read(arch,reg);
            if(reg.nom=reg.nom)then
                encontre:=true;
        end;
        //si lo encontre
        if(encontre)then
        begin
            //me guardo la posición
            pos:= filepos(arch)-1;
            //vuelvo uno para atras
            seek(arch,pos);
            //escribo la cabecera en la posición del registro
            Write(arch,cab);
            //vuelvo a la cabecera
            seek(arch,0);
            //multiplico el campo instalaciones por -1
            reg.instalaciones:=pos*-1;
            //escribo la cabecera
            write(arch,reg);
        end
        else
            writeln('No se encontro el registro!');
        close(arch);
    end;

    Procedure procesos(var arch:ArchivoSistemas);
    var
        op:integer;
        reg:sistema;
    begin
       while true do
       begin
            writeln('Ingrese un numero para realizar una operacion:');
            writeln('1:dar de alta. 2:dar de baja. 3:finalizar el programa.');
            readln(op);
            case op of
                1:begin
                    leerSistema(reg);
                    darDeAlta(arch,reg);
                end;
                2:begin
                    leerSistema(reg);
                    darDeBaja(arch,reg);
                end;
                3:break;
                else
                    writeln('No se ingerso una opcion valida!');
            end;
       end;
    end;

    Procedure imprimir(var arch:ArchivoSistemas);
    var 
        reg:sistema;
    begin
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,reg);
            with reg do
                writeln('Nombre: ',nom,' Instalaciones: ',instalaciones,' Codigo abierto: ',cod_abierto,' Licencia: ',licencia);
        end;
        close(arch);
    
    end;

var 
    arch:ArchivoSistemas;
BEGIN
    crearArchivo(arch);
    procesos(arch);
    imprimir(arch);
END.