# Ejercicio 2

- Modo IN:
  - Valor
    - El valor del parámetro real se usa para inicializar el correspondiente parámetro formal al invocar la unidad. Se transfiere el dato real y se copia en una nueva variable. En este caso el parámetro formal actúa como una variable local de la unidad llamada, y crea otra variable. La conexión es al inicio para pasar el valor y se corta la vinculación.
    ```Pascal
        Program main
        var i:integer;
            Procedure A(a:integer)
            var x,y: integer;
            Begin
                a=a+3;
                x=a+1;
                y=x+1;
            end;
        Begin
            i=3;
            A(i);
            Print(i);
        End.
    ```
    - Imprime 3
  - Valor Constante
    - Se envía un valor, pero la rutina receptora no puede modificarlo, es decir queda con un valor fijo que no se puede cambiar.
    ```C
        void mostrar(const int x){
            	//x = 10 -> daria error ya que x no puede modificarse
                printf("%d", x);
        }

        int main(){
            int a = 5;
            mostrar(a); 
        }
    ```
    - Imprime 5
- Modo OUT:
  - Por resultado
    - El valor del parámetro formal de la rutina se copia al parámetro real al terminar de ejecutarse la unidad que fue llamada. El parámetro formal es una variable local del entorno de la rutina sin valor inicial porque no recibe nada, entonces, se debe inicializar de alguna forma si el lenguaje no lo hace por defecto.
    ```Pascal
        Program main
        var i:integer;
            Procedura A(res a:integer)
            var x,y: integer;
            Begin
                a=3
                x=a+1;
                y=x+1;
                a=y;
            end;
        Begin
            i=3;
            A(i);
            Print(i);
        End.
    ```
    - Imprime 5
  - Por resultado de funciones
    - Es el resultado que me devuelven las funciones. Reemplaza la invocación en la expresión que contiene el llamado.
    ```Pascal
        Program main
        var i:integer;
            Function A():integer
            var x,y: integer;
            Begin
                x=3;
                y=x+1;
                return y;
            end;
        Begin
            i=3;
            i=A();
            Print(i);
        End.
    ```
    - Imprime 4
- Modo In – Out:
  - Valor – resultado
    - Es una combinación de por valor y resultado. El parámetro formal es una variable local que recibe una copia a la entrada del contenido del parámetro real y a la salida el parámetro real recibe una copia de lo que tiene el parámetro formal. Cuando se invoca la rutina, el parámetro real le da valor al parámetro formal (se genera copia) y se desliga en ese momento.
    ```Pascal
        Program main
        var i:integer;
            Procedura A(in-out a:integer)
            var x,y: integer;
            Begin
                a=4
                x=a+1;
                y=x+1;
                a=y;
            end;
        Begin
            i=3;
            A(i);
            Print(i);
        End.
    ```
    - Imprime 6
  - Referencia
    - Comparte la dirección del parámetro real al parámetro formal. Haciendo que el parámetro formal actúe como un puntero al real. Cada referencia al parámetro formal será a un ambiente no local, entonces cualquier cambio que se realice en el parámetro formal dentro del cuerpo del subprograma quedará registrado en el parámetro real. El cambio será automático. 
    ```Pascal
        Program main
        var i:integer;
            Procedura A(var a:integer)
            var x,y: integer;
            Begin
                a=4
                x=a+1;
                y=x+1;
                a=y;
            end;
        Begin
            i=3;
            A(i);
            Print(i);
        End.
    ```
    - Imprime 6
  - Nombre
    - El parámetro formal es sustituido "textualmente" por una expresión del parámetro real, más un puntero al entorno del parámetro real (expresión textual, entorno real). Se establece la ligadura entre parámetro formal y parámetro real en el momento de la invocación, pero la "ligadura de valor" se difiere hasta el momento en que se lo utiliza (la dirección se resuelve en ejecución).
    - Se comporta según el dato que se comparte:
      - Si es un único valor se comporta exactamente igual que el pasaje por referencia.
      - Si es una constante es equivalente a por valor.
      - Si es un elemento de un arreglo puede cambiar el suscripto entre las distintas referencias
      - Si es una expresión se evalúa cada vez
    ```Pascal
        Program main
        var i:integer;
            Procedura A(nombre a:integer)
            var vec[1..3] of integer;
            Begin
                vec[1]=0;
                a=a-1;
                vec[i]=a;
                vec[a+1]=1;
            end;
        Begin
            i=3;
            A(i);
            Print(i);
        End.
    ```
    - Imprime 2