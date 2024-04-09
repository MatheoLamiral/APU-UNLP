{Realizar un algoritmo que cree un archivo de números enteros no ordenados y permita
incorporar datos al archivo. Los números son ingresados desde teclado. La carga finaliza
cuando se ingresa el número 30000, que no debe incorporarse al archivo. El nombre del
archivo debe ser proporcionado por el usuario desde teclado.}

program P1p1;

const corte=30000;

type
	archivo= file of integer;

	procedure EnteroPorTeclado(var num:integer);
	begin
		write('ingrese un numero entero:');
		readln(num);
	end;

	procedure CargarArchivoEnteros(var arch:archivo);
	var
		num:integer;
	begin
		EnteroPorTeclado(num);
		rewrite(arch);
	    while(num <> corte)do
	    begin
		  Write(arch,num);
		  EnteroPorTeclado(num);
	    end;
	    close(arch);
	end;

var
    arch:archivo;
    arch_fis: String[20];
BEGIN
    write('ingrese el nombre del archivo:');
    read(arch_fis);
    assign(arch,arch_fis);
    CargarArchivoEnteros(arch);
END.
