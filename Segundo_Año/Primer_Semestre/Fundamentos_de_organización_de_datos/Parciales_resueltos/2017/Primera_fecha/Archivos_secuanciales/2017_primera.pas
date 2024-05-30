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
                Readln(t,user_num,cant_mails,user);
                Readln(t,nom);
                Readln(t,ape);
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
        Assign(t,'6junio2017.txt');
        Assign(det,'6junio2017.dat');
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

    Procedure actualizarMaestro(var mae:ArchivoMaestro;var det:ArchivoDetalle);
    var
        reg_mae:log;
        reg_det:diario;
        cant_mensajes:integer;
    begin
        reset(mae);
        reset(det);
        if(not eof(mae))then
            Read(mae,reg_mae);
        LeerDetalle(det,reg_det);
        while(reg_det.user_num <> valoralto)do
        begin
            while(not eof(mae))and(reg_mae.user_num <> reg_det.user_num)do
                Read(mae,reg_mae);
            cant_mensajes := 0;
            while(reg_mae.user_num=reg_det.user_num)do
            begin
                cant_mensajes := cant_mensajes + 1;
                LeerDetalle(det,reg_det);
            end;
            reg_mae.cant_mails := reg_mae.cant_mails + cant_mensajes;
            seek(mae,filepos(mae)-1);
            Write(mae,reg_mae);
        end;
        close(mae);
        close(det);
        writeln('archivo maestro actualizado con exito!');
    end;

    Procedure listarTxt(var mae: ArchivoMaestro);
    var
        txt:text;
        l:log;
    begin
        Assign(txt,'listado.txt');
        rewrite(txt);
        reset(mae);
        while(not eof(mae))do
        begin
            with l do
            begin
                Read(mae,l);
                Writeln(txt,l.user_num,l.user,l.cant_mails);
            end;
        end;
        close(txt);
        close(mae);
        writeln('listado realizado con exito!');
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
    op:integer;
BEGIN
    cargarMaestro(maestro);
    //imprimirArchivo(maestro);
    cargarDetalle(detalle);
    while true do
    begin
		Writeln('Ingrese un numero para realizar una operecion:1- actualizar registro maestro. 2- listar maestro en txt.3- finalizar el programa.');
        readln(op);
        case op of
            1:actualizarMaestro(maestro,detalle);
            2:listarTxt(maestro);
            3:break;
            else
                writeln('no se ingreso un valor correcto!');
		end;
	end;
END.
