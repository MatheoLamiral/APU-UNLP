program primera2023Tema2;

type
  
    empleado = record
        dni:integer;
        nombre:string;
        apellido:string;
        edad:integer;
        domicilio:string;
        fecha_nacimiento:string;
    end;

    ArchivoEmpleados = file of empleado;

    Procedure cargarArchivo(var archivo:ArchivoEmpleados);
    var
        emp:empleado;
        txt:text;
    begin
        assign(txt,'carga.txt');
        assign(archivo,'empleados.dat');
        //inicializo la cabecera
        emp.dni:=0;
        //inicializo en zzzz para que sea mas facil verificar el output del programa
        emp.nombre:='ZZZZZ';
        emp.apellido:='ZZZZZ';
        emp.fecha_nacimiento:='ZZZZZ';
        emp.domicilio:='ZZZZZ';
        reset(txt);
        rewrite(archivo);
        //escribo la cabecera
        write(archivo,emp);
        while not eof(txt) do begin
            with emp do
            begin
                Readln(txt,dni,edad,nombre);
                Readln(txt,apellido);
                Readln(txt,domicilio);
                Readln(txt,fecha_nacimiento);
            end;
            Write(archivo,emp);
        end;
        close(txt);
        close(archivo);
    end;

    Procedure imprimirArchivo(var archivo:ArchivoEmpleados);	
    var
        emp:empleado;
    begin
        reset(archivo);
        while not eof(archivo) do begin
            Read(archivo,emp);
            writeln('DNI: ', emp.dni, ' NOMBRE: ', emp.nombre, ' APELLIDO: ', emp.apellido,
             ' EDAD: ', emp.edad, ' DOMICILIO: ', emp.domicilio, ' FECHA DE NACIMIENTO: ', 
             emp.fecha_nacimiento);
        end;
        close(archivo);
    end;

    Procedure leerRestoEmpleado(var emp:empleado);
    begin
        writeln('Ingrese el nombre del empleado');
        readln(emp.nombre);
        writeln('Ingrese el apellido del empleado');
        readln(emp.apellido);
        writeln('Ingrese la edad del empleado');
        readln(emp.edad);
        writeln('Ingrese el domicilio del empleado');
        readln(emp.domicilio);
        writeln('Ingrese la fecha de nacimiento del empleado');
        readln(emp.fecha_nacimiento);
    end;

    Function existeEmpleado(var archivo:ArchivoEmpleados; dni:integer):boolean;
    var
        emp:empleado;
        existe:boolean;
    begin
        existe:=false;
        while(not eof(archivo))and(not existe)do begin
            Read(archivo,emp);
            if (emp.dni = dni) then
                existe:=true;
        end;
        existeEmpleado:=existe;
    end;

    Procedure agregarEmpleado(var archivo:ArchivoEmpleados);
    var
        cab,aux,emp:empleado;
    begin
        Write('Ingrese el DNI del empleado: ');
        Readln(emp.dni);
        reset(archivo);
        //Leo la cabecera
        Read(archivo,cab);
        if (not(existeEmpleado(archivo,emp.dni))) then 
        begin
            leerRestoEmpleado(emp);
            if(cab.dni = 0) then
            begin
                seek(archivo,filesize(archivo));
                write(archivo,emp);
            end
            else begin
                //voy a la dirección efectiva
                seek(archivo,cab.dni*-1);
                //leo lo que hay y lo guardo en aux
                read(archivo,aux);
                //vuelvo uno para atrás
                seek(archivo,filepos(archivo)-1);
                //escribo el nuevo registro
                Write(archivo,emp);
                //vuelvo a la cabecera
                Seek(archivo,0);
                //escribo la nueva cabecera
                write(archivo,aux);
            end;
        end
        else
            writeln('El empleado ya existe');
        close(archivo);
    end;

    Procedure quitarEmpleado(var archivo:ArchivoEmpleados);
    var
        cab:empleado;
        dni,pos:integer;
    begin
        writeln('ingrese el dni del empleado a eliminar');
        readln(dni);
        reset(archivo);
        //leo la cabecera
        Read(archivo,cab);
        if(existeEmpleado(archivo,dni)) then
        begin
            //me guardo la posicion del elemento a eliminar
            pos:=filepos(archivo)-1;
            //vuelvo para atrás
            seek(archivo,pos);
            //escribo lo que tenia la cabecera en la posición del elemento a eliminar
            write(archivo,cab);
            //vuelvo a la cabecera
            seek(archivo,0);
            //escribo la nueva cabecera
            cab.dni:= pos * -1;
            write(archivo,cab);	
        end
        else
            writeln('El empleado no existe');
        close(archivo);
    end;
var
    archivo:ArchivoEmpleados;
begin
    cargarArchivo(archivo);
    //imprimirArchivo(archivo);
    agregarEmpleado(archivo);
    //imprimirArchivo(archivo);
    quitarEmpleado(archivo);
    //imprimirArchivo(archivo);
end.
