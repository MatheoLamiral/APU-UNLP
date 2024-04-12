{Agregar al menú del programa del ejercicio 5, opciones para:

a. Añadir uno o más celulares al final del archivo con sus datos ingresados por teclado.
b. Modificar el stock de un celular dado.
c. Exportar el contenido del archivo binario a un archivo de texto denominado:
”SinStock.txt”, con aquellos celulares que tengan stock 0.

NOTA: Las búsquedas deben realizarse por nombre de celular.}
program Ejercicio6;
type
	celular = record
		cod:integer;
		nom:string;
		des:string;
		marca:string;	
		precio:integer;
		stockMin:integer;
		stock:integer;
	end;
	
	archivo = file of celular;

	procedure crearArchivo(var arch:archivo);
	var nom:string;
	begin
		writeln('Ingrese el nombre del archivo binario: ');
		readln(nom);
		assign(arch,nom);
	end;
		
	procedure cargarArchivo(var arch:archivo; var arch_texto:Text);
	var c:celular;	
	begin
		crearArchivo(arch);
		rewrite(arch);
		reset(arch_texto);
		while (not eof (arch_texto))do
		begin
			with c do begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockMin,des);
				readln(arch_texto,nom);
			end;
			write(arch,c);
		end;
		writeln('Archivo cargado con exito!');
		close(arch); close(arch_texto);
	end;

	procedure listar(c:celular);
	begin
		writeln('- nombre: ',c.nom);
		writeln('- marca:',c.marca);
		writeln('- codigo:',c.cod);
		writeln('- precio:',c.precio);
		writeln('- stock:',c.stock);
		writeln('- stockMin:',c.stockMin);
		writeln('- descripcion:',c.des);
		writeln('---------------------------')
	end;

	procedure listarMenor(var arch_texto:text);
	var c:celular;
	begin
		reset(arch_texto);
		writeln('Lista de celulares con stock por debajo del minimo:');
		while(not eof(arch_texto))do
		begin
			with c do begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockmin,des);
				readln(arch_texto,nom);
			end;
			if (c.stock < c.stockmin) then listar(c);
		end;
		close(arch_texto);
	end;

	procedure listarCadena(var arch_texto:text);
	var cadena,cadena2:string; c:celular;
	begin
		writeln('Ingrese la descripcion del celular a buscar');
		cadena := ' ';
		readln(cadena2);
		cadena += cadena2;
		reset(arch_texto);
		writeln('Lista de celulares con dicha descripcion:');
		while(not eof(arch_texto))do
		begin
			with c do begin
				readln(arch_texto,cod,precio,marca);
				readln(arch_texto,stock,stockmin,des);
				readln(arch_texto,nom);
			end;
			writeln(c.des);
			writeln(cadena);
			writeln(c.des = cadena);
			if (c.des = cadena) then 
			begin
				listar(c);
			end;
		end;
		close(arch_texto);
	end;

	procedure exportarBinario(var arch:archivo;var arch_texto_2:text);
	var c:celular;
	begin
		assign(arch_texto_2,'celulares.txt');
		rewrite(arch_texto_2);
		reset(arch);
		while(not eof(arch))do
		begin
			read(arch,c);
			with c do begin
				writeln(arch_texto_2,cod,' ',precio,marca);
				writeln(arch_texto_2,stock,' ',stockmin,des);
				writeln(arch_texto_2,nom);
			end;
		end;
		writeln('Archivo exportado con éxito!');
		close(arch_texto_2);close(arch);
	end;

	procedure leerTeclado(var c:celular);
	begin
		writeln('Ingrese el codigo del celular');
		readln(c.cod);
		writeln('Ingrese el precio');
		readln(c.precio);
		writeln('Ingrese la marca');
		readln(c.marca);
		writeln('Ingrese el stock disponible');
		readln(c.stock);
		writeln('Ingrese el stock minimo');
		readln(c.stockMin);
		writeln('Ingrese la descripcion');
		readln(c.des);
		writeln('Ingrese el nombre');
		readln(c.nom);
	end;

	procedure agregarAlFinal(var arch:archivo);
	var ok:integer;c:celular;
	begin
		ok:= 1;
		reset(arch);
		seek(arch,filesize(arch));
		while(ok = 1) do
		begin
			leerTeclado(c);
			write(arch,c);
			writeln('Ingrese 1 si quiere ingresar otro celular, sino ingrese 0');
			readln(ok);
		end;
		close(arch);
	end;

	procedure modificarStock(var arch:archivo);
	var c:celular; nombre:string; stock:integer;
	begin
		reset(arch);
		writeln('Ingrese el nombre del celular a modificar');
		readln(nombre);
		read(arch,c);
		while(not eof(arch)) and (c.nom <> nombre) do
			read(arch,c);
		if(c.nom = nombre)then
		begin
			writeln('Ingrese el nuevo stock');
			readln(stock);
			c.stock:= stock;
			seek(arch,filepos(arch)-1);
			write(arch,c);
			writeln('Stock actualizado correctamente!');
		end
		else
			writeln('Celular no encontrado!');
		close(arch);
	end;

	procedure ExportarSinStock(var arch:archivo;var arch_texto_3:text);
	var c:celular;
	begin
		reset(arch);
		Assign(arch_texto_3,'celularesSinStock.txt');
		rewrite(arch_texto_3);
		while(not eof(arch))do 
		begin
			read(arch,c);
			if (c.stock = 0)then
			begin
				with c do begin
					writeln(arch_texto_3,cod,' ',precio,marca);
					writeln(arch_texto_3,stock,' ',stockmin,des);
					writeln(arch_texto_3,nom);
					writeln('celular '+nom+' exportado con exito!')
				end;
			end;
		end;
		close(arch);close(arch_texto_3);
	end;

var 
	arch_texto:text;
	arch_texto_2:text;
	arch_texto_3:text;
	arch:archivo;
	ok:boolean;
	opcion:integer;
BEGIN
	ok:= true;
	assign(arch_texto,'celulares.txt');
	while(ok) do
	begin
		writeln('Ingrese un numero:');
		writeln('1 = Crear un archivo binario con archivo txt');
		writeln('2 = Listar datos de celulares con stock menor al minimo');
		writeln('3 = Listar datos de celulares cuya descripcion contenga caracteres enviados por mi');
		writeln('4 = Exportar archivo binario a archivo txt');
		writeln('5 = Agregar mas celulares al final del archivo');
		writeln('6 = Modificar stock de un celular dado');
		writeln('7 = Exportar a txt celulares sin stock');
		writeln('8 = Finalizar programa');
		readln(opcion);
		case opcion of
			1: cargarArchivo(arch,arch_texto);	
			2: listarMenor(arch_texto);
			3: listarCadena(arch_texto);
			4: exportarBinario(arch,arch_texto_2);
			5: agregarAlFinal(arch);
			6: modificarStock(arch);
			7: ExportarSinStock(arch,arch_texto_3);
			8: ok:= false;
		end;
	end;
END.
