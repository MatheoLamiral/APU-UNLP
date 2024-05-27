Program P2p2;
const
    valoralto = -1;
type
    alumno  = record
        cod:integer;
        nom:string;
        ape:string;
        mat_c:integer;//materias con cursada aprobada
        mat_f:integer;//materias con final aprobado
    end;
    
    opciones=0..1;
    aprobadas = record
        cod:integer;
        final_o_cursada:opciones;//1 final, 0 cursada
    end;

    ArchivoMaestro = file of alumno;
    ArchivoDetalle = file of aprobadas;

    Procedure cargarMaestro(var t:text;var maestro:ArchivoMaestro);
    var
        reg:alumno;
    begin
        reset(t);rewrite(maestro);
        while (not eof(t))do
        begin
            with reg do
            begin
                readln(t,cod,mat_c,mat_f,ape,nom);
                write(maestro,reg);
            end;
        end;
        writeln('"archivoAlumnos" creado con extito!');
        close(t);
        close(maestro);
    end;

    Procedure cargarDetalle(var t:text;var detalle:ArchivoDetalle);
    var
        reg:aprobadas;
    begin
        reset(t);rewrite(detalle);
        while (not eof(t))do
        begin
            with reg do
            begin
                readln(t,cod,final_o_cursada);
                write(detalle,reg);
            end;
        end;
        writeln('"archivoMaterias" creado con extito!');
        close(t);
        close(detalle);
    end;

    Procedure leer(var arch:ArchivoDetalle;var reg:aprobadas);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.cod:=valoralto;
    end;

    Procedure actualizarMaestro(var maestro:ArchivoMaestro;var detalle:ArchivoDetalle);
    var
        reg_det:aprobadas;
        reg_mas:alumno;
    begin
        reset(maestro);
        reset(detalle);
        leer(detalle,reg_det);
        while(reg_det.cod<>valoralto)do
        begin
            Read(maestro,reg_mas);
            while(reg_mas.cod<>reg_det.cod) do Read(maestro,reg_mas);
            while(reg_mas.cod = reg_det.cod)do
            begin
                if(reg_det.final_o_cursada = 1)then 
                begin
                    reg_mas.mat_f := reg_mas.mat_f + 1;
                    reg_mas.mat_c := reg_mas.mat_c - 1;
                end
                else reg_mas.mat_c := reg_mas.mat_c + 1;
                leer(detalle,reg_det);
            end;
            seek(maestro, filepos(maestro)-1);
            Write(maestro, reg_mas);
        end;
        close(maestro);
        close(detalle);
    end;

    Procedure listarEnTxt(var maestro:ArchivoMaestro);
    var 
        txt: text;
        reg:alumno;
    begin
        reset(maestro);
        Assign(txt,'masFinalesQueCursadas.txt');
        rewrite(txt);
        while(not eof(maestro))do
        begin
            Read(maestro,reg);
            if(reg.mat_f>reg.mat_c)then
            begin
                with reg do writeln(txt,'CODIGO:',reg.cod,', NOMBRE Y APELLIDO:',reg.nom,reg.ape,', MATERIAS CON FINAL:',reg.mat_f,', MATERIAS SIN FINAL:',reg.mat_c);
            end;
        end;
        writeln('Archivo "masFinalesQueCursadas.txt" creado con exito!');
        close(maestro);
        close(txt);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:alumno;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('CODIGO:',reg.cod,', NOMBRE Y APELLIDO:',reg.nom,reg.ape,', MATERIAS CON FINAL:',reg.mat_f,', MATERIAS SIN FINAL:',reg.mat_c);
		end;
		close(arch);
	end;
var
    t,t2:text;
    maestro:ArchivoMaestro;
    detalle:ArchivoDetalle;
BEGIN
    Assign(t,'alumnos.txt');
    Assign(maestro,'archivoAlumnos');
    cargarMaestro(t,maestro);
    imprimirArchivo(maestro);
    Assign(t2,'materias.txt');
    Assign(detalle,'archivoMateria');
    cargarDetalle(t2,detalle);
    actualizarMaestro(maestro,detalle);
    imprimirArchivo(maestro);
    listarEnTxt(maestro);
END.