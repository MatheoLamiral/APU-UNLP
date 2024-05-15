//codigo original
procedure buscar(NRR, clave, NRR_encontrado, pos_encontrada, resultado)
var clave_encontrada: boolean;
begin
if (nodo = null)
resultado := false; {clave no encontrada}
else
posicionarYLeerNodo(A, nodo, NRR);
claveEncontrada(A, nodo, clave, pos, clave_encontrada);
if (clave_encontrada) then begin
NRR_encontrado := NRR; { NRR actual }
pos_encontrada := pos; { posicion dentro del array }
resultado := true;
end
else
buscar(nodo.hijos[pos], clave, NRR_encontrado, pos_encontrada,
resultado)
end;



//inciso A
//recibe el archivo, nodo y NRR, va al archivo, se posiciona en NRR y lee el nodo
Procedure posicionarYLeer(var A: archivo;var nodo:Tnodo;NRR:integer);
begin
    Seek(A,NRR);
    Read(A,nodo);
end;

//inciso B
//devuelve si la calve se encuentra a no en el campo vector de claves del nodo 
Procedure claveEncontrada(nodo:Tnodo, clave:integer, var pos:integer, var clave_encontrada:boolean);
begin
    clave_encontrada:=false;
    while(pos<(M-1))and(not clave_encontrada)do
    begin
        if(nodo.claves[pos]=clave)then
            clave_encontrada:=true
        else
            pos:= pos + 1;
    end;
end;

//inciso C
procedure buscar(var A:archivo;NRR, clave,var NRR_encontrado,var pos_encontrada,var resultado);
var 
    clave_encontrada: boolean;
    pos:integer;
    nodo:Tnodo;
begin
    pos:=1;
    if (NRR = -1)then
        resultado := false; {clave no encontrada}
    else
    begin
        //Recibe Archivo y un NRR y devuelve el nodo en esa dirección
        posicionarYLeerNodo(A, nodo, NRR);
        //Recibe el nodo y verifica si la clave se encuentra en nodo.claves
        claveEncontrada(nodo, clave, pos, clave_encontrada);
        //si encontre la clave
        if (clave_encontrada) then begin
            NRR_encontrado := NRR; { NRR actual }
            pos_encontrada := pos; { posicion dentro del vector }
            resultado := true; 
        end
        else
            buscar(nodo.hijos[pos], clave, NRR_encontrado, pos_encontrada,resultado)
    end;
end;

//inciso D
//clave encontrada deveria devolver true, si la clave se encuentra en el nodo y si el nodo es una hoja, sino sigo buscando
//porque estara en el archvio en el único caso de que este en una hoja 