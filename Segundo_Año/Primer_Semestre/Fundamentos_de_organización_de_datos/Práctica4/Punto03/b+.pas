const 
    M = 8; {orden del árbol}
type
    TipoNodo=(TNodoInterno,TNodoHoja)

    TDato = record
        nombre: string[50];
        apellido: string[50];
        dni:integer;
        legajo:string[20];
        anio_ingreso:integer;
    end; 

    TNodo=record
        cant_claves:integer;
        claves: array[1..M-1] of longint;
        case tipo: TipoNodo of
            TNodoInterno:
                hijos: array[1..M] of integer;
            TNodoHoja:
                enlaces: array[1..M-1] of TDato;
                sig:integer;
    end;

	TArchivoDatos  = file of TNodo;
var
	archivoDatos: TArchivoDatos;
	archivoIndice: arbolB;

