Program P2p1;
const
	valoralto = -1;
type
	
	ingresosEmpleado=record
		cod:integer;
		nom:String;
		monto:real;
	end;

	Archivo = file of ingresosEmpleado;
		
	//creo el archivo binario que se "dispone" a partir de archivo.txt
	Procedure crearArchivo(var t:text;var arch:Archivo);
	var
		ing:ingresosEmpleado;
	begin
		reset(t);
		rewrite(arch);
    	while (not eof(t)) do
		begin
			with ing do
			begin
				readln(t,cod,monto,nom);
				Write(arch,ing);
			end;
        end;
		writeln('Archivo binario creado');
		close(arch);
		close(t);
	end;

	procedure leer(var arch: archivo; var dato: ingresosEmpleado);
	begin
		if(not(eof(arch))) then
			read(arch, dato)
    	else
			dato.cod := valoralto;
	end;
     
	Procedure compactar(var arch:Archivo;var maestro:Archivo);
	var 
		total_m:real;
		ing,reg_act,ing_actualizado:ingresosEmpleado;
	begin
		reset(arch);
		rewrite(maestro);
		leer(arch, ing);
		while(ing.cod <> valoralto) do
		begin
			reg_act:= ing;
			total_m:=0;
			while(reg_act.cod = ing.cod)do
			begin
				total_m := total_m + ing.monto;
				leer(arch,ing);
			end;
			ing_actualizado:=reg_act;
			ing_actualizado.monto:=total_m;
			Write(maestro, ing_actualizado);
		end;
		close(arch);
		close(maestro);
    end;
	
	Procedure imprimirArchivo(var arch:Archivo);
	var
		reg:ingresosEmpleado;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			Writeln('CODIGO:',reg.cod,', NOMBRE:',reg.nom,', MONTO:',reg.monto:0:2);
		end;
		close(arch);
	end;
	
var
	t:text; arch:Archivo; maestro:Archivo;i:integer;
BEGIN
	Assign(t,'archivo.txt');
	Assign(arch,'archivoEmpleados');
	crearArchivo(t,arch);
	Assign(maestro,'archivoCompactado');
	compactar(arch,maestro);
	imprimirArchivo(maestro);
	readln(i);
	writeln(i);
END.
