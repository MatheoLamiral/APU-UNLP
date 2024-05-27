program P2p12;
const
    valoralto = -1;
type
    log=record
        user_num:integer;
        user:String;
        nom:String;
        ape:String;
        cant_mails:integer;
    end;

    diario=record
        user_num:integer;
        dest:string;
        cuerpo:string;
    end;

    ArchivoMaestro=file of log;
    ArchivoDetalle=file of diario;

    Procedure cargarMaestro(var maestro:ArchivoMaestro);
    var
        t:text;
        l:log;
    begin
        Assign(t,'logmail.txt');
        Assign(maestro,'logmail.dat');
        reset(t);
        rewrite(maestro);
        while(not eof(t))do
        begin
            with l do
            begin
                Readln(t,user_num,cant_mails,user,nom,ape);
                Write(maestro,l);
            end;
        end;
        writeln('Archivo "logmail.dat" creado con exito!');
        close(t);
        close(maestro);
    end;

    Procedure cargarDetalle(var det:ArchivoDetalle);
    var
        t:text;
        d:diario;
    begin
        Assign(t,'correo_diario.txt');
        Assign(det,'correoDiario');
        reset(t);
        rewrite(det);
        while(not eof(t))do
        begin
            with d do
            begin
                Readln(t,user_num,dest);
                Readln(t,cuerpo);
                Write(det,d);
            end;
        end;
        writeln('Archivo "correoDiario" creado con exito!');
        close(t);
        close(det);
    end;

    Procedure LeerDetalle(var arch:ArchivoDetalle;var reg:diario);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.user_num:=valoralto;
    end;
    Procedure LeerMaestro(var arch:ArchivoMaestro;var reg:log);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.user_num:=valoralto;
    end;

    Procedure actualizarMaestro(var mae:ArchivoMaestro;var det:ArchivoDetalle);
    var
        l:log;
        d:diario;
        t:text;
    begin
        Assign(t,'usuarios2.txt');
        rewrite(t);
        reset(mae);
        reset(det);
        LeerDetalle(det,d);
        LeerMaestro(mae,l);
        while(d.user_num<>valoralto)and(not eof(mae))do
        begin
            while(l.user_num<>d.user_num)do Read(mae,l);
            while(l.user_num = d.user_num)do
            begin
                l.cant_mails := l.cant_mails + 1;
                LeerDetalle(det,d);
            end;
            Writeln(t,'USUARIO: ',l.user_num,', CANTMENSAJES: ',l.cant_mails);
            seek(mae, filepos(mae)-1);
            Write(mae, l);
        end;
        while(not eof(mae))do
        begin
            Read(mae,l);
            Writeln(t,'USUARIO: ',l.user_num,', CANTMENSAJES: ',l.cant_mails);
        end;
        close(det);
        close(mae);
        close(t);
    end;

    Procedure listarEntxt(var mae:ArchivoMaestro);
    var 
        l:log;
        t:text;
    begin
        Assign(t,'usuarios.txt');
        reset(mae);
        rewrite(t);
        while(not eof(mae))do
        begin
            Read(mae,l);
            Writeln(t,'NRO USUARIO: ',l.user_num,', CANTMENSAJES: ',l.cant_mails);
        end;
        close(mae);
        close(t);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:log;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('NUMERO USUARIO:',reg.user_num,', NOMBRE DE USUARIO:',reg.user,', NOMBRE:',reg.nom,', APELLIDO:',reg.ape,'CANTIDAD DE MAILS:', reg.cant_mails);
		end;
		close(arch);
	end;
var
    maestro:ArchivoMaestro;
    detalle:ArchivoDetalle;
BEGIN
    cargarMaestro(maestro);
    imprimirArchivo(maestro);
    cargarDetalle(detalle);
    actualizarMaestro(maestro,detalle);
    imprimirArchivo(maestro);
    listarEntxt(maestro);
END.
