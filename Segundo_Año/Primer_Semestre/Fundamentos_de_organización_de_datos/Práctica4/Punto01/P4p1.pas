const 
    M = 8; {orden del árbol}
type
    //datos 
    TDato = record
        nombre: string[50];
        apellido: string[50];
        dni:integer;
        legajo:string[20];
        anio_ingreso:integer;
    end; 

    //nodo del arbol 
    TNodo = record
        cant_datos:	integer;
        datos: array[1..M-1] of TDato;
        hijos: array[1..M] of integer;
    end;

    arbolB = file of TNodo;	

    var	
        rchivoDatos: arbolB;
