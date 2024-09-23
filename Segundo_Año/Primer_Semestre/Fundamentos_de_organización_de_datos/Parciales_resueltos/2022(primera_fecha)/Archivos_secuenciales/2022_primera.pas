program primera2022;

const
    valoralto = 'zzzz';
type

    libreria = record
        razon_libreria: string;
        genero: string;
        nombre_libro: string;
        precio: real;
        cant_vendida: integer;
    end;

    Archivo = file of libreria;

    Procedure cargarLibrerias (var arch: Archivo);
    var
        l: libreria;
    begin
        assign(arch, 'librerias.dat');
        rewrite(arch);
        
        l.razon_libreria := 'Libreria A';
        l.genero := 'Ficcion';
        l.nombre_libro := 'Libro F';
        l.precio := 10.5;
        l.cant_vendida := 100;
        write(arch, l);
        l.razon_libreria := 'Libreria A';
        l.genero := 'Romance';
        l.nombre_libro := 'Libro R';
        l.precio := 15.5;
        l.cant_vendida := 50;
        write(arch, l);
        l.razon_libreria := 'Libreria A';
        l.genero := 'Romance';
        l.nombre_libro := 'Libro RO';
        l.precio := 15.5;
        l.cant_vendida := 50;
        write(arch, l);
        
        l.razon_libreria := 'Libreria B';
        l.genero := 'Aventura';
        l.nombre_libro := 'Libro A';
        l.precio := 15.0;
        l.cant_vendida := 200;
        write(arch, l);
        
        l.razon_libreria := 'Libreria B';
        l.genero := 'Aventura';
        l.nombre_libro := 'Libro A';
        l.precio := 15.0;
        l.cant_vendida := 200;
        write(arch, l);

        l.razon_libreria := 'Libreria B';
        l.genero := 'Aventura';
        l.nombre_libro := 'Libro AB';
        l.precio := 15.0;
        l.cant_vendida := 200;
        write(arch, l);

        l.razon_libreria := 'Libreria B';
        l.genero := 'Historia';
        l.nombre_libro := 'Libro H';
        l.precio := 15.0;
        l.cant_vendida := 200;
        write(arch, l);

        l.razon_libreria := 'Libreria C';
        l.genero := 'Ciencia';
        l.nombre_libro := 'Libro C';
        l.precio := 20.0;
        l.cant_vendida := 300;
        write(arch, l);

        l.razon_libreria := 'Libreria C';
        l.genero := 'Ciencia';
        l.nombre_libro := 'Libro CI';
        l.precio := 20.0;
        l.cant_vendida := 300;
        write(arch, l);
        
        close(arch);
    end;

    Procedure leer(var arch: Archivo; var reg: libreria);
    begin
        if not eof(arch) then
            read(arch, reg)
        else
            reg.razon_libreria := valoralto;
    end;

    Procedure corteDeControl (var arch: Archivo);
    var
        tot_libs,tot_lib,tot_gen,tot_libro:real;
        reg:libreria;
        lib_act,gen_act,libro_act:string;
    begin
        tot_libs:=0;
        reset(arch);
        leer(arch,reg);
        while(reg.razon_libreria<>valoralto)do
        begin
            tot_lib:=0;
            lib_act:=reg.razon_libreria;
            writeln('Libreria: ',lib_act);
            while(reg.razon_libreria=lib_act)do
            begin
                tot_gen:=0;
                gen_act:=reg.genero;
                writeln('       Genero: ',gen_act);
                while(reg.razon_libreria=lib_act)and(reg.genero=gen_act)do
                begin
                    tot_libro:=0;
                    libro_act:=reg.nombre_libro;
                    while(reg.razon_libreria=lib_act)and(reg.genero=gen_act)and(reg.nombre_libro=libro_act)do
                    begin
                        tot_libro:=tot_libro+reg.precio*reg.cant_vendida;
                        leer(arch,reg);
                    end;
                    writeln('               Nombre del libro: ',libro_act);
                    writeln('               Total vendido del libro ',libro_act,' es: ',tot_libro:2:2);
                    tot_gen:=tot_gen+tot_libro;
                end;
                Writeln('       Monto vendido  del genero ',gen_act,' es: ',tot_gen:2:2);
                tot_lib:=tot_lib+tot_gen;
            end;
            writeln('Monto vendido de la libreria ',lib_act,' es: ',tot_lib:2:2);
            tot_libs:=tot_libs+tot_lib;
        end;
        writeln('MONTO VENDIDO DE TODAS LAS LIBRERIAS ES: ',tot_libs:2:2);
        close(arch);
    end;

var
    arch: Archivo;
begin
    cargarLibrerias(arch);
    corteDeControl(arch);
end.
