Program P3p3;
type
    novela=record
        cod:integer;
        gen:string;
        nom:string;
        dur:integer;
        dir:string;
        precio:real;
    end;

    archivoNovelas=file of novela;
    
    procedure leerNovela(var n:novela);
    begin
        writeln('ingrese el codigo de la novela:');
        readln(n.cod);
        if(n.cod<>-1)then
        begin
            writeln('ingrese el genero de la novela:');
            readln(n.gen);
            writeln('ingrese el nombre de la novela:');
            readln(n.nom);
            writeln('ingrese la duracion de la novela:');
            readln(n.dur);
            writeln('ingrese el director de la novela:');
            readln(n.dir);
            writeln('ingrese el precio de la novela:');
            readln(n.precio);
        end;
    end;

    procedure crearArchivo(var arch:archivoNovelas);
    var
        n:novela;
        nom:string;
    begin
        Writeln('ingrese el nombre del archivo:');
        readln(nom);
        Assign(arch,nom);
        rewrite(arch);
        n.cod:= 0;
        Write(arch,n);
        leerNovela(n);
        while(n.cod<>-1)do
        begin
            write(arch,n);
            leerNovela(n);
        end;
        close(arch);
    end;

    procedure darDeAlta(var arch:archivoNovelas);
    var
        n,aux,cabecera:novela;
    begin
        leerNovela(n);
        reset(arch);
        Read(arch,cabecera);
        if(cabecera.cod=0)then
        begin
            seek(arch,filesize(arch));
            write(arch,n);
        end
        else begin
            seek(arch,(n.cod*-1));
            Read(arch,aux);
            seek(arch,filepos(arch)-1);
            write(arch,n);
            seek(arch,0);
            write(arch,aux);
        end;
        close(arch);
    end;

    procedure modificarDato(var arch:archivoNovelas);
    var
        n:novela;
        cod_busc:integer;
    begin
        writeln('ingrese el codigo de novela a modificar:');
        read(cod_busc);
        reset(arch);
        if(not eof(arch))then Read(arch,n);
        while(not eof(arch))and(n.cod<>cod_busc)do
        begin
            Read(arch,n);
        end;
        if(n.cod=cod_busc)then 
        begin
            n.cod:=cod_busc;
            writeln('ingrese el genero de la novela:');
            readln(n.gen);
            writeln('ingrese el nombre de la novela:');
            readln(n.nom);
            writeln('ingrese la duracion de la novela:');
            readln(n.dur);
            writeln('ingrese el director de la novela:');
            readln(n.dir);
            writeln('ingrese el precio de la novela:');
            readln(n.precio);
            seek(arch,filepos(arch)-1);
            Write(arch,n);
            writeln('novela modificada con exito!')
        end
        else writeln('no existe la novela con el codigo ingresado!');
        close(arch);
    end;

    procedure eliminar(var arch:archivoNovelas);
    var 
        cod,pos:integer;
        encontre:boolean;
        n:novela;
        cabecera:novela;
    begin
        reset(arch);
        writeln('ingrese un codigo de novela, para eliminarla del archivo:');
        readln(cod);
        encontre:=false;
        Read(arch,cabecera);
        while(not eof(arch))and(not encontre)do
        begin
            Read(arch,n);
            if(n.cod=cod)then encontre := true;
        end;
        if(encontre)then
        begin
            pos:= (filepos(arch))-1;
            seek(arch,pos);
            Write(arch,cabecera);
            seek(arch,0);
            n.cod:=pos*-1;
            Write(arch,n);
        end
        else writeln('No existe una novela con el código ingresado!');
        close(arch);
    end;

    Procedure imprimirArchivo(var arch:archivoNovelas);
    var
        n:novela;
    begin
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,n);
            Writeln('CODIGO:',n.cod,', GENERO:',n.gen,', NOMBRE:',n.nom,', DURACION:',n.dur,', DIRECTOR:',n.dir,', PRECIO:',n.precio:0:2);
        end;
        close(arch);
    end;

    procedure mantenimiento(var arch:archivoNovelas);
    var
        op:integer;
    begin
        while true do
            begin
            writeln('ingrese un numero para realizar una operacion de mantenimiento');
            writeln('1.dar de alta, 2.modificar novela, 3.eliminar novela, 4. imprimir archivo, 5. volver al menu prinipal');
            readln(op);
            case op of
                1:darDeAlta(arch);
                2:modificarDato(arch);
                3:eliminar(arch);
                4:imprimirArchivo(arch);
                5:break;
            end;
        end;
    end;

    procedure listarEspacioLibre(var arch:archivoNovelas);
    var
        txt:text;
    begin

    end;

var
    arch:archivoNovelas;
BEGIN
    crearArchivo(arch);
    mantenimiento(arch);
END.