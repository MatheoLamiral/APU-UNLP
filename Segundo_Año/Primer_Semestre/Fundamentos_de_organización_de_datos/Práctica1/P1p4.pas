{Agregar al menú del programa del ejercicio 3, opciones para:
a. Añadir uno o más empleados al final del archivo con sus datos ingresados por
teclado. Tener en cuenta que no se debe agregar al archivo un empleado con
un número de empleado ya registrado (control de unicidad).
b. Modificar la edad de un empleado dado.
c. Exportar el contenido del archivo a un archivo de texto llamado
“todos_empleados.txt”.
d. Exportar a un archivo de texto llamado: “faltaDNIEmpleado.txt”, los empleados
que no tengan cargado el DNI (DNI en 00).
NOTA: Las búsquedas deben realizarse por número de empleado.}

program P1p4;

type
  empleado=record
    num:integer;
    ape:String[15];
    nom:String[15];
    edad:integer;
    dni:integer;
  end;
    
  archivoEmpleados = file of empleado;
  
  Procedure LeerEmpleado(var e:empleado);
  begin
    write('ingrese el apellido del empleado: ');
    readln(e.ape);
    if (e.ape<>'fin')then
    begin
        write('ingrese el nombre del empleado: ');
		readln(e.nom);
		write('ingrese el numero del empleado: ');
		readln(e.num);
		write('ingrese la edad del empleado: ');
		readln(e.edad);
		write('ingrese el DNI del empleado: ');
		readln(e.dni);
	end;
  end;
  
  Procedure CargarArchivo(var arch:archivoEmpleados);
  var
    e:empleado;
  begin
    LeerEmpleado(e);
    while (e.ape<>'fin')do
    begin
      write(arch,e);
      LeerEmpleado(e);
    end;
  end;
  
  Procedure ImprimirEmpleado(e:empleado);
  begin
    writeln('numero de empleado: ',e.num);
    writeln('apellido del empleado: ',e.ape);
    writeln('nombre del empleado: ',e.nom);
    writeln('edad del empleado: ',e.edad);
    writeln('DNI del empledao: ',e.dni);
  end;
  
  Procedure BuscarEmpleado(var arch:archivoEmpleados);
  var
    encontre:boolean;
    e:empleado;
    apeOnom:String;
  begin
    write('ingrese el nombre o el apellido del empleado a buscar: ');
    readln(apeOnom);
    encontre:= false;
    while (not eof(arch))and(not encontre)do
    begin
      read(arch,e);
      if(e.nom=apeOnom)or(e.ape=apeOnom)then
      begin
        ImprimirEmpleado(e);
        encontre:=true;
      end;
    end;
    if (not encontre)then
      write('el empleado ingresado no existe');
  end;
  
  Procedure ListarEmpleados(var arch:archivoEmpleados);
  var
    e:empleado;
  begin
    writeln ('LISTA DE EMPLEADOS:');
    while not eof(arch)do
    begin
      read(arch,e);
      ImprimirEmpleado(e);
      writeln('-----------------------------')
    end;
  end;
  
  Procedure ListarEmpleados70(var arch:archivoEmpleados);
  var
    e:empleado;
  begin
    writeln ('LISTA DE EMPLEADOS PRÓXIMOS A JUVILARSE (MAYORES DE 70):');
    while not eof(arch)do
    begin
      read(arch,e);
      if(e.edad>=70)then
        ImprimirEmpleado(e);
        writeln('-----------------------------')
    end;
  end;
  
  function ExisteEmpleado(var arch:archivoEmpleados;num:integer):boolean;
  var
    e:empleado;
  begin
	if not(eof(arch)) then read(arch,e);
    while (not eof(arch))and(e.num<>num)do
    begin
      read(arch,e);
    end;
    if(e.num<>num)then
      ExisteEmpleado:= false
    else
      ExisteEmpleado:= true;
  end;
  
  Procedure AgregarEmpleados (var arch:archivoEmpleados);
  var
    e:empleado;
  begin
    writeln('ingrese el apellido fin para finalizar la carga de empleados');
    LeerEmpleado(e);
	while(e.ape<> 'fin')do
	begin
		if(ExisteEmpleado(arch,e.num))then
		begin
			write('el empleado que intenta ingresar ya existe');
			seek(arch,0);
		end
		else begin
			seek(arch,filesize(arch));
			Write(arch,e);
		end;
		LeerEmpleado(e);
    end;   
  end;
  
  Procedure ModificarEdad (var arch:archivoEmpleados);
  var
	num:integer;e:empleado;edad:integer;
  begin
	writeln('ingrese el numero de empleado para modificar su edad:');
    read(num);
    Read(arch,e);
    while(not eof(arch))and(e.num<>num)do
    begin 
      Read(arch,e);
    end;
    if(e.num=num)then
    begin
	  writeln('ingrese la edad modificada del empleado: ');
	  readln(edad);
	  seek(arch,filePos(arch)-1);
      e.edad:= edad;
      Write(arch,e);
    end
    else
		write('el empleado ingresado no existe!')
  end;
  
  Procedure ExportarContenido (var arch:archivoEmpleados;var arch_texto:text);
  var e:empleado;
  begin
	assign(arch_texto,'todos_empleados.txt');
	rewrite(arch_texto);
	while(not eof(arch)) do
	begin
		read(arch,e);
		with e do
			writeln(arch_texto,' ',num,' ',ape,' ',nom,' ',edad,' ',dni);
	end;
	writeln('Datos agregados a txt correctamente!');
	close(arch_texto);
  end;
  
  Procedure ExportarAFaltaDni(var arch:archivoEmpleados;var arch_texto_Dni:text);
  var e:empleado;
  begin
	assign(arch_texto_Dni,'faltDNIEmpleado.txt');
	rewrite(arch_texto_Dni);
	while (not eof(arch)) do 
	begin
		read(arch,e);
		if (e.dni=00)then
		begin
			with e do
				writeln(arch_texto_Dni,num,' ',ape,' ',nom,' ',edad,' ',dni);
		end;
	end;
	writeln('datos agregados a txt correctamente!');
	close(arch_texto_Dni);
  end;
  
var
  arch:archivoEmpleados;
  arch_fis:String[20];
  num:integer;
  ok:boolean;
  arch_texto:text;
  arch_texto_Dni:text;
begin
  write('ingrese el nombre del archivo: ');
  readln(arch_fis);
  assign(arch,arch_fis);
  rewrite(arch);// después de hacer rewrite, para cargar el archivo tengo que hacer reset también?
  CargarArchivo(arch);
  close(arch);
  ok:= True;
  while(ok)do
  begin
	writeln('ingrese un número para realizar una accion:');
	writeln('1:listar empleados.');
	writeln('2:Buscar empleado por nombre o apellido.');
	writeln('3:listar esmpleados próximos a jubilarse.');
	writeln('4:Agregar empleados al archivo.');
	writeln('5:Modificar edad de empleado.');
	writeln('6:Exportar contenido binario a archivo Txt.');
	writeln('7:Exportar empleados con dni 00 a archivo Txt.');
	writeln('8:terminar programa.');
	readln(num);
	reset(arch);
	case num of
		1:ListarEmpleados(arch);
		2:BuscarEmpleado(arch);// Por qué se cuelga cuando ingreso un nombre o apellido que no existe en el archivo ?
		3:ListarEmpleados70(arch);
		4:AgregarEmpleados(arch);
		5:ModificarEdad(arch);
		6:ExportarContenido(arch,arch_texto);
		7:ExportarAFaltaDni(arch,arch_texto_Dni);
		8: ok:= false;
	end;
	close(arch);
  end;
end.
