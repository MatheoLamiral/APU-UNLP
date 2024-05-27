Program P2p10;
const
    valoralto=-1;
type

    categoria=1..15;
    empleado=record
        depto:integer;
        divi:integer;
        num:integer;
        cat:categoria;
        he:integer;
    end;

    ArchivoMaestro=file of empleado;

    valorCategoria=array [categoria] of real;

    procedure cargarVector(var v:valorCategoria);
    var
        txt:text;
        pos:integer;
        monto:real;
    begin
        Assign(txt,'categorias.txt');
        reset(txt);
        while not eof(txt)do
        begin
            Readln(txt,pos,monto);
            v[pos]:= monto;
        end;
        close(txt);
    end;

    procedure crearMaestro(var mae:ArchivoMaestro);
    var
        txt:text;
        e:empleado;
    begin
        Assign(mae,'ArchivoEmpleados');
        Assign(txt,'empleados.txt');
        rewrite(mae);
        reset(txt);
        while not eof(txt)do
        begin
            with e do
            begin
                Readln(txt,depto,divi,num,cat,he);
                Write(mae,e);
            end;
        end;
        writeln('Archivo "ArchivoEmpleados" creado con exito!');
        close(mae);
        close(txt);
    end;

    procedure leer(var mae: ArchivoMaestro; var reg: empleado);
    begin
        if(not eof(mae)) then
            read(mae, reg)
        else
            reg.depto:= valoralto;
    end;


    procedure informarMaestro(var mae:ArchivoMaestro;vec:valorCategoria);
    var
        horas_div,horas_depto,horas_emple:integer;
        monto_div,monto_depto,motno_emple:real;
        e:empleado;
        depto_act,div_act,num_act,cat_act:integer;
    begin
        reset(mae);
        leer(mae,e);
        while(e.depto<>valoralto)do
        begin
            writeln();
            writeln('---DEPARTAMENTO: ',e.depto,'---');
            depto_act:= e.depto;
            horas_depto:=0;
            monto_depto:=0;
            while(depto_act = e.depto)do
            begin
                writeln();
                writeln('DIVISION: ',e.divi);
                div_act:=e.divi;
                horas_div:=0;
                monto_div:=0;
                while(depto_act = e.depto)and(div_act = e.divi)do
                begin
                    num_act:=e.num;
                    cat_act:=e.cat;
                    horas_emple:=0;
                    motno_emple:=0;
                    while(depto_act = e.depto)and(div_act = e.divi)and(num_act = e.num)do
                    begin
                        horas_emple:= horas_emple + e.he;
                        leer(mae,e);
                    end;
                    motno_emple:= vec[cat_act]*horas_emple;
                    writeln('NRO EMPLEADO:',num_act,' HS:',horas_emple,' COBRO:',motno_emple:0:2);
                    monto_div:= monto_div + motno_emple;
                    horas_div:= horas_div + horas_emple;
                end;
                writeln();
                writeln('Total de horas por division: ',horas_div);
                writeln('Monto total por division: ',monto_div:0:2);
                horas_depto:= horas_depto + horas_div;
                monto_depto:= monto_depto + monto_div;
            end;
             writeln();
             writeln('Total de horas por departamento: ',horas_depto);
             writeln('Monto total por departamento: ',monto_depto:0:2);
        end;
        close(mae);
    end;

var
    maestro:ArchivoMaestro;
    categorias:valorCategoria;
BEGIN
    cargarVector(categorias);
    crearMaestro(maestro);
    informarMaestro(maestro,categorias);
END.