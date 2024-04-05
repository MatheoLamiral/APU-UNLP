{Realizar un programa que presente un menú con opciones para:
a. Crear un archivo de registros no ordenados de empleados y completarlo con
datos ingresados desde teclado. De cada empleado se registra: número de
empleado, apellido, nombre, edad y DNI. Algunos empleados se ingresan con
DNI 00. La carga finaliza cuando se ingresa el String ‘fin’ como apellido.
b. Abrir el archivo anteriormente generado y
i. Listar en pantalla los datos de empleados que tengan un nombre o apellido
determinado, el cual se proporciona desde el teclado.
ii. Listar en pantalla los empleados de a uno por línea.
iii. Listar en pantalla los empleados mayores de 70 años, próximos a jubilarse.
NOTA: El nombre del archivo a crear o utilizar debe ser proporcionado por el usuario}

program P1p3;

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
  
var
  arch:archivoEmpleados;
  arch_fis:String[20];
  num:integer;
  ok:boolean;
begin
  write('ingrese el nombre del archivo: ');
  readln(arch_fis);
  assign(arch,arch_fis);
  rewrite(arch);
  CargarArchivo(arch);
  close(arch);
  write('ingrese un número para realizar una accion. 1:listar empleados.2:Buscar empleado por nombre o apellido.3:listar esmpleados próximos a jubilarse. 4: terminar programa');
  ok:= True;
  while(ok)do
  begin
	readln(num);
	reset(arch);
	case num of
		1:ListarEmpleados(arch);
		2:BuscarEmpleado(arch);
		3:ListarEmpleados70(arch);
		4: ok:= false;
	end;
	close(arch);
  end;
end.
