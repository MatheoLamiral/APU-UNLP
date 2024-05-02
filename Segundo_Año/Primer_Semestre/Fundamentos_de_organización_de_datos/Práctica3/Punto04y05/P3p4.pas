Program P3p4;

type

    flor = record
        nom:string [45];
        cod:integer;
    end;

    archivoFlores = file of flor;

    Procedure leerFlor(var f:flor);
    begin
        writeln('ingrese un nombre:');
        readln(f.nom);
        if(f.nom<>'xxx')then
        begin
            writeln('ingrese un codigo:');
            readln(f.cod);
        end;
    end;

    Procedure crearArchivo(var arch:archivoFlores);
    var
        f:flor;
    begin
        Assign(arch,'archivoFlores');
        f.cod:=0;
        f.nom:='zzz';
        rewrite(arch);
        //escribo flor con codigo 0 en la cabecera
        Write(arch,f);
        close(arch);
    end;

    Procedure agregarFlor(var arch:archivoFlores;flr:flor);
    var
        cab,f,aux:flor;
    begin
        f:=flr;
        reset(arch);
        //leo la cabecera
        Read(arch,cab);
        //si es cero agrego al final 
        if(cab.cod=0)then
        begin
            seek(arch,filesize(arch));
            Write(arch,f);
        end
        else begin
            //me posiciono en la direccion efectiva de la cabecera
            seek(arch,cab.cod*-1);
            //leo el registro(cod=0)
            Read(arch,aux);
            //vuelvo una posicion para atras
            seek(arch,filepos(arch)-1);
            //Agrego el registro 
            Write(arch,f);
            //vuelvo a la cabecera 
            seek(arch,0);
            //copio el registro con cod=0
            Write(arch,aux);
        end;
        close(arch);
    end;

    Procedure eliminarFlor (var arch: archivoFlores; flr:flor);
    var
        cab,f:flor;
        encontre:boolean;
        pos:integer;
    begin
        encontre:=false;
        reset(arch);
        //leo la cabecera
        Read(arch,cab);
        while(not eof(arch))and(not encontre)do
        begin
            Read(arch,f);
            if(f.cod=flr.cod)then encontre:=true;
        end;
        if(encontre)then
        begin
            pos:=filepos(arch)-1;
            seek(arch,pos);
            Write(arch,cab);
            seek(arch,0);
            f.cod:=pos*-1;
            write(arch,f);
        end;
        close(arch);
    end;

    Procedure listarFlores(var arch:archivoFlores);
    var
        f:flor;
    begin
        reset(arch);
        //salteo la cabecera
        Read(arch,f);
        while(not eof(arch))do
        begin
            Read(arch,f);
            if(f.cod>0)then 
            begin
                Writeln('NOMBRE:',f.nom,', CODIGO:',f.cod);
            end;
        end;
        close(arch);
    end;

    Procedure procesos(var arch:archivoFlores);
    var
        op:integer;
        f:flor;
    begin
        while true do
        begin
            writeln('ingrese un numero para realizar una operacion.');
            writeln('1.agregar flor, 2.eliminar flor, 3.volver al menu principal');
            readln(op);
            leerFlor(f);
            case op of
                1:agregarFlor(arch,f);
                2:eliminarFlor(arch,f);
                3:break;
            end;
        end;
    end;



var
    flores:archivoFlores;
    f:flor;
BEGIN
    crearArchivo(flores);
    procesos(flores);
    listarFlores(flores);
END.