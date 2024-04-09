{Realizar un programa que permita:
a) Crear un archivo binario a partir de la información almacenada en un archivo de
texto. El nombre del archivo de texto es: “novelas.txt”. La información en el
archivo de texto consiste en: código de novela, nombre, género y precio de
diferentes novelas argentinas. Los datos de cada novela se almacenan en dos
líneas en el archivo de texto. La primera línea contendrá la siguiente información:
código novela, precio y género, y la segunda línea almacenará el nombre de la
novela.
b) Abrir el archivo binario y permitir la actualización del mismo. Se debe poder
agregar una novela y modificar una existente. Las búsquedas se realizan por
código de novela.
NOTA: El nombre del archivo binario es proporcionado por el usuario desde el teclado.}

Program P1p7;
type
	novela = record
		cod:integer;
		nom:String;
		gen:String;
		precio:real;
	end;
	
	archivoNovelas = file of novela;
	
	Procedure crearArchivo(var arch:archivoNovelas;var arch_texto:text);
	var nomArch:String;n:novela;
	begin
		writeln('Ingrese el nombre del archivo binario: ');
		readln(nomArch);
		assign(arch,nomArch);
		rewrite(arch);
		reset(arch_texto);
		while(not eof(arch_texto))do
		begin
			with n do
			begin
				readln(arch_texto,cod,precio,gen);
				readln(arch_texto,nom);
			end;
			write(arch,n);
		end;
		close(arch);close(arch_texto);
		writeln('ARCHIVO BINARIO CREADO CON EXITO!')
	end;
	
	Procedure LeerNovela(var n:novela);
	begin
		writeln('Ingrese el codigo de la novela:');
		readln(n.cod);
		writeln('Ingrese el nombre de la novela: ');
		readln(n.nom);
		writeln('Ingrese el genero de la novela: ');
		readln(n.gen);
		writeln('Ingrese el precio de la novela: ');
		readln(n.precio);
	end;
	
	Procedure AgregarNovela(var arch:archivoNovelas);
	var n:novela;
	begin
		reset(arch);
		seek(arch,filesize(arch));
		LeerNovela(n);
		write(arch,n);
		close(arch);
		writeln('NOVELA AGREGADA CON EXITO!')
	end;

	Procedure ModificarNovela(var arch:archivoNovelas);
	var nom:string;n:novela;opcion:String;
	begin
		writeln('Ingrese el nombre de la novela a modificar: ');
		readln(nom);
		reset(arch);
		Read(arch,n);
		while((not eof(arch))and(n.nom<>nom))do
		begin
			Read(arch,n);
		end;
		if(n.nom=nom)then
		begin
			writeln('Ingrese el campo que desea modificar de la novela.');
			writeln('nombre,codigo,precio y género. En caso de querer terminar ingrese fin');
			readln(opcion);
			while(opcion<>'fin')do
			begin
				case opcion of
					'nombre':readln(n.nom);
					'codigo':readln(n.cod);
					'precio':readln(n.precio);
					'genero':readln(n.gen);
					else begin writeln('NO INGRESASTE UN COMANDO VALIDO!');
					break; end;
				end;
				writeln('nor rompio');
				seek(arch,filesize(arch)-1);
				Write(arch,n);
				writeln('Ingrese el campo que desea modificar de la novela.');
				writeln('nombre,codigo,precio y género. En caso de querer terminar ingrese fin');
				readln(opcion);
			end;
			writeln('rompio');
		end
		else
			writeln('LA NOVELA INGRESADA NO SE ENCUENTRA DENTRO DEL ARCHIVO!');
		close(arch);
	end;
	
var
	arch:archivoNovelas;
	arch_texto:text;
	opcion:integer;
	ok:boolean;
BEGIN
	assign(arch_texto,'novelas.txt');
	crearArchivo(arch,arch_texto);
	ok := true;
	while(ok)do
	begin
		writeln('Ingrese un numero para realizar una accion: ');
		writeln('1.Agregar una novela al archivo.');
		writeln('2.Modificar una novela del archivo.');
		writeln('3.Finalizar programa.');
		readln(opcion);
		case opcion of
				1:AgregarNovela(arch);
				2:ModificarNovela(arch);
				3:ok := false;
		end;
	end;
END.
