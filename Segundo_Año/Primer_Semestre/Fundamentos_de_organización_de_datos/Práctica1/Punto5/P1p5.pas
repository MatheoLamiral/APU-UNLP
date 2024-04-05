{Realizar un programa para una tienda de celulares, que presente un menú con
opciones para:
a. Crear un archivo de registros no ordenados de celulares y cargarlo con datos
ingresados desde un archivo de texto denominado “celulares.txt”. Los registros
correspondientes a los celulares deben contener: código de celular, nombre,
descripción, marca, precio, stock mínimo y stock disponible.
b. Listar en pantalla los datos de aquellos celulares que tengan un stock menor al
stock mínimo.
c. Listar en pantalla los celulares del archivo cuya descripción contenga una
cadena de caracteres proporcionada por el usuario.
d. Exportar el archivo creado en el inciso a) a un archivo de texto denominado
“celulares.txt” con todos los celulares del mismo. El archivo de texto generado
podría ser utilizado en un futuro como archivo de carga (ver inciso a), por lo que
debería respetar el formato dado para este tipo de archivos en la NOTA 2.
NOTA 1: El nombre del archivo binario de celulares debe ser proporcionado por el usuario.
NOTA 2: El archivo de carga debe editarse de manera que cada celular se especifique en
tres líneas consecutivas. En la primera se especifica: código de celular, el precio y
marca, en la segunda el stock disponible, stock mínimo y la descripción y en la tercera
nombre en ese orden. Cada celular se carga leyendo tres líneas del archivo
“celulares.txt”.}

program P1p5;

type

	celular = record
		cod:integer;
		nom:String;
		des:String;
		marca:String;
		precio:real;
		stockMin:integer;
		stock:integer;
	end;
	
	archivoCelulares = file of celular;
	
	Procedure crearArchivo(var arch:archivoCelulares);
	var nomArch:String;
	begin
	  writeln('ingrese el nombre del archivo a crear: ');
	  readln(nomArch);
	  assign(arch,nomArch);
	end;
	
	Procedure cargarArchivo(var arch:archivoCelulares;var arch_texto:text);
	var c:celular;
	begin
		crearArchivo(arch);
		rewrite(arch);
		reset(arch_texto);
		while (not eof(arch_texto))do 
		begin
			with c do 
			begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockMin,des);
				readln(arch_texto,nom);
			end;
			Write(arch,c);
		end;
		writeln('Archivo binario cargado correctamente!');
		close(arch);
		close(arch_texto);
	end;

	Procedure MostrarRegistro(c:celular);
	begin
		writeln('codigo: ',c.cod);
		writeln('precio: ',c.precio);
		writeln('marca: ',c.marca);
		writeln('stock disponible: ',c.stock);
		writeln('stock minimo: ',c.stockMin);
		writeln('descripcion: ',c.des);
		writeln('nombre: ',c.nom);
	end;
	
	Procedure ListarStockMenorStockMin(var arch_texto:text);
	var c:celular;
	begin
		reset(arch_texto);
		writeln('lista de celulares con stock menor al stock minimo: ');
		while (not eof(arch_texto))do
		begin
			with c do
			begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockMin,des);
				readln(arch_texto,nom);
			end;
			if(c.stock<c.stockMin)then 
			begin
				MostrarRegistro(c);
				writeln('---------------'); 
			end;
		end;
	end;
	
	Procedure ListarPorDesc(var arch_texto:text);
	var desc: String;c:celular;
	begin
		writeln('Ingrese una descripcion para listar celulares: ');
		readln(desc);
		desc := ' '+desc;
		reset(arch_texto);
		writeln('lista de celulares con la descicion ingresada: ');
		while (not eof(arch_texto))do
		begin
			with c do
			begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockMin,des);
				readln(arch_texto,nom);
			end;
			if(c.des=desc)then 
			begin
				MostrarRegistro(c);
				writeln('---------------'); 
			end;
		end;
	end;
	
	Procedure ExportarBinarioATxt(var arch_texto:text;var arch:archivoCelulares);
	var c:celular;
	begin
		reset(arch);
		rewrite(arch_texto);
		while (not eof(arch_texto))do 
		begin
			Read(arch,c);
			with c do 
			begin
				writeln(arch_texto,cod,precio,marca);
				writeln(arch_texto,stock,stockMin,des);
				writeln(arch_texto,nom);
			end;
		end;
		writeln('Archivo txt cargado correctamente!');
		close(arch);
		close(arch_texto);
	end;
var 
	arch:archivoCelulares;arch_texto:text;ok:boolean;opcion:integer;
begin
	assign(arch_texto,'celulares.txt');
	ok:= true;
	while(ok)do
	begin
		writeln('Ingrese un numero para realizar una tarea: ');
		writeln('1.Crear archivo binario a partir de celulares.txt.');
		writeln('2.Listar en pantalla los celulares con stock menor a stock minimo.');
		writeln('3.Listar en pantalla los celulares a partir de una descripcion ingresada.');
		writeln('4.Exportar archivo binario a celulares.txt.');
		writeln('5.Finalizar programa. ');
		readln(opcion);
		case opcion of
			1:cargarArchivo(arch,arch_texto);
			2:ListarStockMenorStockMin(arch_texto);
			3:ListarPorDesc(arch_texto);
			5:ok:=false;
		end;
	end;	
end.
