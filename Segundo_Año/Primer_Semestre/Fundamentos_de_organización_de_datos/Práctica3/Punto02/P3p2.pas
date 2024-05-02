Program P3p2;

type

    asistente=record
        num:integer;
        ape_nom:String;
        mail:String;
        tel:integer;
        dni:integer;
    end; 

    archivoAsistentes=file of asistente;

    procedure leerAsistente(var a:asistente);
    begin
        writeln('ingrese el numero del empleado:');
        readln(a.num);
        if(a.num<>-1)then
        begin
            writeln('ingrese el apellido y nombre del empleado:');
            readln(a.ape_nom);
            writeln('ingrese el email del empleado:');
            readln(a.mail);
            writeln('ingrese el telefono del empleado');
            readln(a.tel);
            writeln('ingrese el DNI del empleado');
            readln(a.dni);
        end;
    end;

    procedure crearArchivo(var arch:archivoAsistentes);
    var 
        a:asistente;
    begin
        Assign(arch,'archivoAsistentes');
        rewrite(arch);
        leerAsistente(a);
        while(a.num<>-1)do
        begin
            write(arch,a);
            leerAsistente(a);
        end;
        close(arch);
    end;

    procedure bajaLogica(var arch:archivoAsistentes);
    var
        a:asistente;
    begin
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,a);
            if(a.num<1000)then a.ape_nom:='******'+a.ape_nom;
            seek(arch,filepos(arch)-1);
            write(arch,a);
        end;
        close(arch);
    end;

    Procedure ImprimirAsistente(a:asistente);
    begin
        writeln('numero de asistente: ',a.num);
        writeln('apellido y nombre de asistente: ',a.ape_nom);
        writeln('email de asistente: ',a.mail);
        writeln('telefono de asistente: ',a.tel);
        writeln('DNI de asistente: ',a.dni);
    end;

    procedure ImprimirArchivo(var arch:archivoAsistentes);
    var
        a:asistente;
    begin
        reset(arch);
        while(not eof(arch))do
        begin
            Read(arch,a);
            ImprimirAsistente(a);
            writeln('---------------')
        end;
        close(arch);
    end;

var
    arch:archivoAsistentes;
BEGIN
    crearArchivo(arch);
    ImprimirArchivo(arch);
    bajaLogica(arch);
    ImprimirArchivo(arch);
END.