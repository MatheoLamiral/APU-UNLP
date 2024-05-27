Program P2p4;
const
    valoralto='ZZZZ';
type
    alfab=record
        prov:string;
        cant_a:integer;
        cant_e:integer;
    end;

    agencia=record
        prov:string;
        cod:integer;
        cant_a:integer;
        cant_e:integer;
    end;

    ArchivoMaestro=file of alfab;
    ArchivoDetalle=file of agencia;

    Procedure cargarMaestro(var t:text;var maestro:ArchivoMaestro);
    var
        a:alfab;
    begin
        reset(t);
        rewrite(maestro);
        while(not eof(t))do
        begin
            with a do
            begin
                Readln(t,cant_a,cant_e,prov);
                Write(maestro,a);
            end;
        end;
        writeln('Archivo "archivoAlfabetizacion" creado con exito!');
        close(t);
        close(maestro);
    end;

    Procedure cargarDetalle(var t:text;var detalle:ArchivoDetalle);
    var
        a:agencia;
    begin
        reset(t);
        rewrite(detalle);
        while(not eof(t))do
        begin
            with a do
            begin
                Readln(t,cod,cant_a,cant_e,prov);
                Write(detalle,a);
            end;
        end;
        writeln('Archivo cargado!');
        close(t);
        close(detalle);
    end;

    Procedure Leer(var arch:ArchivoDetalle;var reg:agencia);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.prov:=valoralto;
    end;

    Procedure minimo(var det1,det2:ArchivoDetalle;var a1,a2,min:agencia);
    begin
        if(a1.prov<=a2.prov)then
        begin
            min:=a1;
            leer(det1,a1);
        end
        else 
        begin
            min:=a2;
            leer(det2,a2);
        end;
    end;

    Procedure LeerMaestro(var arch:ArchivoMaestro;var reg:alfab);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.prov:=valoralto;
    end;

    Procedure actualizarMaestro(var det1,det2:ArchivoDetalle;var maestro:ArchivoMaestro);
    var
        a1,a2,min:agencia;
        alf:alfab;
    begin
        reset(maestro);
        reset(det1);
        reset(det2);
        leer(det1,a1); leer(det2,a2);
        minimo(det1,det2,a1,a2,min);
        leerMaestro(maestro,alf);
        while(min.prov<>valoralto)and(not eof(maestro))do
        begin
            while(alf.prov<>min.prov) do Read(maestro,alf);
            while(alf.prov = min.prov)do
            begin
                alf.cant_a:= alf.cant_a + min.cant_a;
                alf.cant_e:= alf.cant_e + min.cant_e;        
                minimo(det1,det2,a1,a2,min);
            end;
            seek(maestro, filepos(maestro)-1);
            Write(maestro, alf);

        end;
        close(maestro);
        close(det1);
        close(det2);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:alfab;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('PROVINCIA:',reg.prov,', ALFABETIZADAS:',reg.cant_a,', ENCUESTADAS:',reg.cant_e);
		end;
		close(arch);
	end;

var
    t,t1,t2:text;
    maestro:ArchivoMaestro;
    detalle1,detalle2:ArchivoDetalle;
BEGIN
    Assign(maestro,'archivoAlfabetizacion');
    Assign(t,'alfabetizacion.txt');
    cargarMaestro(t,maestro);
    imprimirArchivo(maestro);
    Assign(detalle1,'archivoAgencia1');
    Assign(t1,'agencia1.txt');
    Assign(detalle2,'archivoAgencia2');
    Assign(t2,'agencia2.txt');
    cargarDetalle(t1,detalle1);
    cargarDetalle(t2,detalle2);
    writeln('Archivos "archivoAgencia1" y "archivoAgencia2" creados con exito!');
    actualizarMaestro(detalle1,detalle2,maestro);
    imprimirArchivo(maestro);
END.