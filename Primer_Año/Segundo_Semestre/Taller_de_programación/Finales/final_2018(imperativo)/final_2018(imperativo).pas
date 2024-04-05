Program final_2018;

type

	pedido = record   // registro completo
		dni: integer;
		nom: String;
		fecha: integer;
		monto: real;
	end;
	
	pedidoSinDniYNom = record
		fecha:integer;
		monto: real;
	end;
	
	lista = ^nodoLista;  //lista
	
	nodoLista = record //nodo de la lista
		dato:pedidoSinDniYNom;
		sig:lista;
	end;
	
	
	arbol = ^nodo;   //arbol
	
	nodo = record
		dni: integer;
		nom: String;
		list: lista;
		hi: arbol;
		hd: arbol;
	end;
	
	Procedure leerPedido(var p:pedido);
	begin
		writeln('ingrese el monto:');
		readln(p.monto);
		if(p.monto<>0)then
		begin
			writeln('ingrese la fecha:');
			readln(p.fecha);
			writeln('ingrese el nombre:');
			readln(p.nom);
			writeln('ingrese del DNI:');
			readln(p.dni);
		end;
	end;
	
	Procedure agregarAdelante(var pri:lista;p2:pedidoSinDniYNom);
	var
		nue:lista;
	begin
		new(nue);
		nue^.dato:=p2;
		nue^.sig:=pri;
		pri:=nue;
	end;
	
	Procedure pasajeDeRegistros(var p2:pedidoSinDniYNom;p:pedido);
	begin
		p2.fecha:= p.fecha;
		p2.monto:= p.monto;
	end;
	
	Procedure agregarAlArbol(var a:arbol;p:pedido);
	var p2:pedidoSinDniYNom;
	begin
		pasajeDeRegistros(p2,p);
		if (a=nil)then
		begin
			new(a);
			a^.dni:=p.dni;
			a^.nom:=p.nom;
			a^.list:=nil;
			agregarAdelante(a^.list,p2);
			a^.hi:=nil;
			a^.hd:=nil;
		end
		else
			if(p.dni = a^.dni)then
				agregarAdelante(a^.list,p2)
			else
				if(p.dni<a^.dni)then
					agregarAlArbol(a^.hi,p)
				else
					agregarAlArbol(a^.hd,p);
	end;
	
	Procedure generarArbol(var a:arbol);
	var p:pedido;
	begin
		leerPedido(p);
		while(p.monto<>0)do
		begin
			agregarAlArbol(a,p);
			leerPedido(p);
		end;
	end;
	
	Function huboPedido(pri:lista;fech:integer):integer;
	begin
		while((pri<>nil)and(pri^.dato.fecha<>fech))do
		begin
			pri:=pri^.sig;
		end;
		if(pri<>nil)then
			huboPedido:=1
		else
			huboPedido:=0;
	end;
	
	Function cantidadPedidosFecha(a:arbol;fech:integer):integer;
	begin
		if(a<>nil)then
			cantidadPedidosFecha:= cantidadPedidosFecha(a^.hi,fech)+cantidadPedidosFecha(a^.hd,fech)+huboPedido(a^.list,fech)
		else
			cantidadPedidosFecha:= 0;
	end;
	
	Procedure calcularLista(pri:lista;var totalM:real;var totalP:integer);
	begin
		while(pri<>nil)do
		begin
			totalP:=totalP + 1;
			totalM:=totalM + pri^.dato.monto;
			pri:=pri^.sig;
		end;
	end;
	
	
	Procedure totalMontoYTotalPedidos(a:arbol;var totalM:real;var totalP:integer;dni:integer);
	begin
		if(a=nil)then
			writeln('el DNI ingresado no se encuentra en la estructura!')
		else
		begin
			if(a^.dni=dni)then
				calcularLista(a^.list,totalM,totalP)
				else
					if(a^.dni>dni)then
						totalMontoYTotalPedidos(a^.hi,totalM,totalP,dni)
					else
						totalMontoYTotalPedidos(a^.hd,totalM,totalP,dni);
		end;
	end;
var
	a:arbol;fech:integer;dni:integer;totalM:real;totalP:integer;
BEGIN
	a:=nil;
	generarArbol(a);
	writeln('ingrese una fecha para contar la canitdad de pedidos relaizados: ');
	readln(fech);
	writeln('la cantidad de pedidos realizados en la fecha ingresada es: ', cantidadPedidosFecha(a,fech));
	writeln('ingrese un DNI para calcular el total abonado y el total de pedidos asociados al mismo: ');
	readln(dni);
	totalM:=0;totalP:=0;
	totalMontoYTotalPedidos(a,totalM,totalP,dni);
	if((totalM<>0)and(totalP<>0))then
	  writeln('el total abonado es ',totalM,' y la canitdad de pedidos realizados es ',totalP);
END.
