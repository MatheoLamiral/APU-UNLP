Program P2p3;
const
    valoralto = -1;
type
    producto=record
        cod:integer;
        nom:string;
        precio:real;
        stock_act:integer;
        stock_min:integer;
    end;

    venta=record
        codp:integer;
        unidades:integer;
    end;

    ArchivoMaestro = file of producto;
    ArchivoDetalle = file of venta;

    Procedure cargarMaestro(var t:text;var maestro:ArchivoMaestro);
    var
        prod:producto;
    begin
        reset(t);
        rewrite(maestro);
        while(not eof(t))do
        begin
            with prod do
            begin
                Readln(t,cod,precio,stock_act,stock_min,nom);
                Write(maestro,prod);
            end;
        end;
        writeln('Archivo "archivoProductos" creado con exito!');
        close(t);
        close(maestro);
    end;

    Procedure cargarDetalle(var t:text;var detalle:ArchivoDetalle);
    var
        vent:venta;
    begin
        reset(t);
        rewrite(detalle);
        while(not eof(t))do
        begin
            with vent do
            begin
                Readln(t,codp,unidades);
                Write(detalle,vent);
            end;
        end;
        writeln('Archivo "archivoVentas" creado con exito!');
        close(t);
        close(detalle);
    end;

    Procedure Leer(var arch:ArchivoDetalle;var reg:venta);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.codp:=valoralto;
    end;

    Procedure actualizarMaestro(var maestro:ArchivoMaestro;var detalle:ArchivoDetalle);
    var
        vent:venta;
        prod:producto;
    begin
        reset(maestro);
        reset(detalle);
        leer(detalle,vent);
        while(vent.codp<>valoralto)do
        begin
            Read(maestro,prod);
            while(prod.cod<>vent.codp) do Read(maestro,prod);
            while(prod.cod = vent.codp)do
            begin
                prod.stock_act:= prod.stock_act - vent.unidades;        
                leer(detalle,vent);
            end;
            seek(maestro, filepos(maestro)-1);
            Write(maestro, prod);
        end;
        close(maestro);
        close(detalle);
    end;

    Procedure ListarEnTxt(var arch:ArchivoMaestro);
    var
        txt:text;
        prod:producto;
    begin
        Assign(txt,'stock_minimo.txt');
        reset(arch);
        rewrite(txt);
        while(not eof(arch))do
        begin
            Read(arch,prod);
            with prod do
            begin
                if(prod.stock_act<prod.stock_min)then 
                    Writeln(txt,prod.cod,' ',prod.nom,' ',prod.precio:0:2,' ',prod.stock_act,' ',prod.stock_min);
            end;
        end;
        close(txt);
        close(arch);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:producto;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('CODIGO:',reg.cod,', NOMBRE:',reg.nom,', PRECIO:',reg.precio:0:2,', STOCK:',reg.stock_act,', STOCK MINIMO:',reg.stock_min);
		end;
		close(arch);
	end;
var
    txt,txt2:text;
    maestro:ArchivoMaestro;
    detalle:ArchivoDetalle;
BEGIN
    Assign(maestro,'archivoProductos');
    Assign(txt,'productos.txt');
    cargarMaestro(txt,maestro);//creo archivoProductos
    imprimirArchivo(maestro);//por que toma para stock_act y stock_min el mismo valor?
    Assign(detalle,'archivoVentas');
    Assign(txt2,'ventas.txt');
    cargarDetalle(txt2,detalle);//creo archivoVnetas
    actualizarMaestro(maestro,detalle);//actualizo maestro con detalle
    imprimirArchivo(maestro);
    ListarEnTxt(maestro);
END.