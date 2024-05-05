Program P3p7;
const
    FIN=999;
type

    ave = record
        cod:integer;
        especie:string;
        familia:string;
        desc:string;
        zona:string;
    end;

    avesViaExtinsion = file of ave;

    procedure leerCodigo(var a:ave);
    begin
        writeln('ingrese un codigo de ave:');
        Readln(a.cod);
    end;

    Procedure crearMaestro(var mae:avesViaExtinsion);
    var
        txt:text;
        a:ave;
    begin
        Assign(txt,'maestro.txt');
        Assign(mae,'archivoAvesViaExtinsion');
        reset(txt);
        rewrite(mae);
        while(not eof(txt))do
        begin
            with a do
            begin
                Readln(txt,a.cod,a.especie);
                Readln(txt,a.familia);
                Readln(txt,a.desc);
                Readln(txt,a.zona);
                Write(mae,a);
            end;
        end;
        close(txt);
        close(mae);
    end;

    Procedure imprimirMaestro(var mae:avesViaExtinsion);
    var
        a:ave;
    begin
        writeln('***ARCHIVO MAESTRO***');
        reset(mae);
        while(not eof(mae))do
        begin
            Read(mae,a);
            writeln('COD:',a.cod,', ESPECIE:',a.especie,
                ', FAMILIA:',a.familia,', DESC:',a.desc,', ZONA:',a.zona);
        end;
        close(mae);
    end;

    Procedure bajaLogica(var mae:avesViaExtinsion);
    var
        a:ave;
        cod:integer;
        encontre:boolean;
    begin
        reset(mae);
        writeln('ingrese el codigo de ave a borrar:');
        readln(cod);
        while(cod <> FIN)do
        begin
            encontre:=false;
            while(not eof(mae))and(not encontre)do
            begin
                Read(mae,a);
                if(a.cod=cod)then
                    encontre:=true;
            end;
            if(encontre)then
            begin
                seek(mae,filepos(mae)-1);
                a.cod:=a.cod*-1;
                Write(mae,a);
            end
            else writeln('el ave con el codigo ',cod,', no corresponde a un ave del archivo!');
            writeln('ingrese el codigo de ave a borrar:');
            readln(cod);
        end;
        close(mae);
    end;

    procedure leer(var mae: avesViaExtinsion; var a: ave);
    begin
        if(not eof(mae)) then
            read(mae, a)
        else
            a.cod:= FIN;
    end;

    Procedure compactar(var mae:avesViaExtinsion);
    var
        a:ave;
        pos:integer;
    begin
        reset(mae);
        leer(mae,a);
        while(a.cod<>FIN)do
        begin
            writeln('aaa');
            //si el codigo es negativo 
            if(a.cod<0)then
            begin
                //guardo la posicion actual 
                pos:= filepos(mae)-1;
                //voy al fnal del archivo 
                seek(mae,filesize(mae)-1);
                //leo el registro al final 
                Read(mae,a);
                //mientras el codigo del final tenga codigo negativo 
                while(a.cod<0)do
                begin
                    writeln('bbbb');
                    //vuelvo una posicion hacia atras
                    seek(mae,filesize(mae)-1);
                    //trunco el archivo
                    truncate(mae);
                    //vuelvo a retroceder
                    seek(mae,filesize(mae)-1);
                    //leo el registro 
                    Read(mae,a);
                end;
                //vuelvo a la posicion del registro a borrar
                seek(mae,pos);
                //sobreescribo el registro
                Write(mae,a);
                //vuelvo al final
                seek(mae,filesize(mae)-1);
                //trunco el archivo
                truncate(mae);
                //vuelvo al registro remplazado para continuar desde ahi
                seek(mae,pos);
            end;
            leer(mae,a);
        end;
        close(mae);
    end;

var
    maestro:avesViaExtinsion;
BEGIN
    crearMaestro(maestro);
    imprimirMaestro(maestro);
    bajaLogica(maestro);
    imprimirMaestro(maestro);
    compactar(maestro);
    imprimirMaestro(maestro);
END.