{Realizar un algoritmo, que utilizando el archivo de números enteros no ordenados
creado en el ejercicio 1, informe por pantalla cantidad de números menores a 1500 y el
promedio de los números ingresados. El nombre del archivo a procesar debe ser
proporcionado por el usuario una única vez. Además, el algoritmo deberá listar el
contenido del archivo en pantalla.}

Program P1p2;

type 

  archivo=file of integer;

	procedure menores1500yPromedio(var arch:archivo;var menores:integer;var promedio:real);
	var
	    cantNum:integer;
	    num:integer;
	begin
	    cantNum := 0;
	    reset(arch);
	    while (not eof(arch))do
	    begin
		  read(arch,num);
		  if(num < 1500)then
		    menores:=menores + 1;
		  cantNum:=cantNum + num;
	    end;
	    promedio := cantNum/  fileSize(arch);
	end; 

var
    arch:archivo;
    menores:integer;
    promedio:real;
    nomArch:String;
BEGIN
    write('ingrese el nombre del archivo:');
    readln(nomArch);
    assign(arch,nomArch);
    menores1500yPromedio(arch,menores,promedio);
    write('la cantidad de numeros menores a 1500 es ',menores,'y el promedio es: ',promedio:0:2 );
    close(arch);
END.
