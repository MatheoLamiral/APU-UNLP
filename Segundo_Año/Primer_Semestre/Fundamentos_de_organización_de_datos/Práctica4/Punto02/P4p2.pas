const 
    M = 8; {orden del árbol}
type
    TDato = record
        nombre: string[50];
        apellido: string[50];
        dni:integer;
        legajo:string[20];
        anio_ingreso:integer;
    end; 
	
    TNodo = record
		cant_claves:integer;
		claves: array[1..M-1] of longint;
		enlaces: array [1..M-1] of integer;
		hijos: array[1..M] of integer;
	end;

	TArchivoDatos  = file of TDato; //archivo de datos
	arbolB = file of TNodo;	//archivo indice
var
	archivoDatos: TArchivoDatos;
	archivoIndice: arbolB;
