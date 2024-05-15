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
		enlaces: array[1..M] of integer;
        //M dimension del vector, para poder asociar cada clave de claves
        //posicionalmente en enlaces y que el utlimo elemento de enlaces, que sobra con respecto 
        //a la cantidad de claves que tengo, es para apuntar a la siguiente hoja de la lista 
	end;

	TArchivoDatos  = file of TDato; //archivo de datos
	arbolB = file of TNodo;	//archivo indice
var
	archivoDatos: TArchivoDatos;
	archivoIndice: arbolB;

