Program P2p5;
uses 
    SysUtils;
const
    valoralto=9999;
    dimF=3;//30 para el enunciado, 3 para simplificación de pruebas
type
    producto=record
        cod:integer;
        nom:string;
        desc:string;
        stock:integer;
        stock_min:integer;
        precio:real;
    end;

    venta=record
        cod_p:integer;
        cant_vendida:integer;
    end;
    
    ArchivoMaestro=file of producto;
    ArchivoDetalle=file of venta;

    vectorDetalles = Array [1..dimF] of ArchivoDetalle;
    VectorRegistros= Array [1..dimF] of venta;

    Procedure cargarMaestro(var maestro:ArchivoMaestro);
    var
        p:producto;
        t:text;
    begin
        Assign(t,'maestro.txt');
        Assign(maestro,'archivoProductos');
        reset(t);
        rewrite(maestro);
        while(not eof(t))do
        begin
            with p do
            begin
                Readln(t,cod,nom);
                Readln(t,stock,stock_min,precio,desc);
                Write(maestro,p);
            end;
        end;
        writeln('Archivo "archivoProductos" creado con exito!');
        close(t);
        close(maestro);
    end;

    procedure crearUnDetalle(var det:ArchivoDetalle;nombre_txt:string;nombre_binario:string);
    var
        t: text;
        v: venta;
    begin
        assign(t, nombre_txt);
        reset(t);
        assign(det, nombre_binario);
        rewrite(det);
        while(not eof(t)) do
            begin
                with v do
                    begin
                        readln(t, cod_p, cant_vendida);
                        write(det, v);
                    end;
            end;
        writeln('Archivo binario detalle creado con exito!');
        close(det);
        close(t);
    end;

    procedure crearDetalles(var vec: vectorDetalles);
    var
        i: integer;
    begin
        for i:= 1 to dimF do 
            crearUnDetalle(vec[i],'detalle'+IntToStr(i)+'.txt','detalle'+IntToStr(i));
    end;

    Procedure LeerDetalle(var arch:ArchivoDetalle;var reg:venta);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.cod_p:=valoralto;
    end;

    Procedure LeerMaestro(var arch:ArchivoMaestro;var reg:producto);
    begin
        if(not eof(arch))then
            Read(arch,reg)
        else
            reg.cod:=valoralto;
    end;

    Procedure minimo(var detalles:vectorDetalles;var registros:VectorRegistros;var min:venta);
    var
        i, pos:integer;
    begin
        min.cod_p:=valoralto;
        for i:= 1 to dimF do
        begin
            if(registros[i].cod_p < min.cod_p)then
            begin
                min:=registros[i];
                pos:=i;
            end
        end;
        if(min.cod_p<>valoralto)then
            LeerDetalle(detalles[pos],registros[pos]);
    end;

    Procedure actualizarMaestro(var detalles:vectorDetalles;var mae:ArchivoMaestro);
    var
        p:producto;
        registros:VectorRegistros;
        min:venta;
        i:integer;
    begin
        reset(mae);
        for i:=1 to dimF do //abro y leo los detalles
        begin
            reset(detalles[i]);
            LeerDetalle(detalles[i],registros[i]);
        end;
        minimo(detalles,registros,min);
        LeerMaestro(mae,p);
        while(min.cod_p<>valoralto)do
        begin
            while(min.cod_p <> p.cod)and(not eof(mae))do Read(mae,p);
            while(min.cod_p = p.cod)and(min.cod_p<>valoralto)do
            begin
                if(p.stock>0)then p.stock:= p.stock - min.cant_vendida;       
                minimo(detalles,registros,min);
            end;
            seek(mae, filepos(mae)-1);
            Write(mae, p);
        end;
        close(mae);
        for i:=1 to dimF do close(detalles[i]);
    end;

    procedure reporte(var mae: ArchivoMaestro);
    var
        p: producto;
        txt: text;
    begin
        assign(txt, 'reporte.txt');
        reset(mae);
        rewrite(txt);
        while(not eof(mae)) do
            begin
                read(mae, p);
                if(p.stock < p.stock_min) then
                    writeln(txt, p.nom,' ', p.desc,' ', p.stock, ' ', p.precio:0:2);
            end;
        close(txt);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:producto;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('COD:',reg.cod,', NOMBRE:',reg.nom,', DESC:',reg.desc,', STOCK: ',reg.stock,', STOCKMIN: ',reg.stock_min,', PRECIO: ',reg.precio:0:2);
		end;
		close(arch);
	end;

var
    maestro:ArchivoMaestro;
    detalles:vectorDetalles;
BEGIN
    cargarMaestro(maestro);
    imprimirArchivo(maestro);
    crearDetalles(detalles);
    actualizarMaestro(detalles,maestro);
    reporte(maestro);
    imprimirArchivo(maestro);
END.