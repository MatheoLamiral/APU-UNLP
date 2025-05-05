# Ejercicio 5

## Lenguaje - Pascal
```Pascal
    1. Program P
    2. var 5: integer;
    3. var a:char;
    4. Begin
    5.      for i:=5 to 10 do begin
    6.          write(a);
    7.          a=a+1;
    8.      end;
    9. End.
```
### Errores sintácticos
- (1), falta `;`. Se detecta en compilación  
- (2), no se puede comenzar el nombre de una variable con un número. Se detecta en compilación  
- (7), la asignación en Pascal se escribe con `:=`. Se detecta en compilación  
### Errores semánticos   
- (5), la variable `i` no se encuentra declarada. Se detecta en compilación 
- (7), a no se encuentra inicializada. Se detecta en ejecución  
- (8), operación inválida, ya que se le intenta sumar 1 (integer) a la variable `a` (char) y esto no se permite en Pascal. Se detecta en compilación  

## Lenguaje - Java
```Java
    1. public String tabla(int numero, arrayList<Boolean> listado) {
    2.     String result = null;
    3.     for(i = 1; i < 11; i--) {
    4.         result += numero + "x" + i + "=" + (i*numero) + "\n";
    5.         listado.get(listado.size()-1)=(BOOLEAN) numero>i;
    6.    }
    7.    return true;
    8. }
```
### Errores sintácticos
- (1), `arrayList` debería escribirse con A mayúscula (`ArrayList`). Se detecta en compilación  
- (5), `get()` devuelve un valor, no se le puede realizar una asignación
- (5), El tipo de dato `BOOLEAN` no existe (es `boolean`). Se detecta en compilación
### Errores semánticos  
- (3), la variable `i` no se encuentra declarada. Se detecta en compilación
- (7), intenta retornar un `boolean` en un método que debe devolver un `String`. Se detecta en compilación  
- (4), concatenar cadenas con `null` genera una excepción. Se detecta en ejecución  
### Errores lógicos
- (3), ocurre un bucle infinito, `i` nunca va a ser mayor o igual a 11, por ende el bucle no tiene fin. Se detecta en ejecución.  
- (5), si la lista está vacía devuelve un error. Se detecta en ejecución.  

## Lenguaje - C
```C
    1. # include <stdio.h>
    2. int suma; /* Esta es una variable global */
    3. int main()
    4. { 
    5.    int indice;
    6.    encabezado;
    7.    for (indice = 1 ; indice <= 7 ; indice ++)
    8.        cuadrado (indice);
    9.    final(); Llama a la función final */
   10.    return 0;
   11. }
   12. cuadrado (numero)
   13. int numero;
   14. { 
   15.     int numero_cuadrado;
   16.     numero_cuadrado == numero * numero;
   17.     suma += numero_cuadrado;
   18.     printf("El cuadrado de %d es %d\n", numero, numero_cuadrado);
   19. }
```
### Errores sintácticos
- (6), `encabezado` no tiene paréntesis para ser una llamada a una función, ni tampoco se encuentra declarado como variable. Detectado en compilación
- (7), faltan las llaves del for. Detectado en compilación
- (9), comentario mal realizado, se escribe /* comentario */. Detectado en compilación
- (12), falta `;`. Detectado en compilación
- (12), el parámetro `numero` no tiene un tipo definido. Detectado en compilación
- (12), no se abren las llaves de la función. Detectado en compilación
- (16), uso de `==` en lugar de `=` para la asignación. Detectado en compilación
### Errores semánticos  
- (9), `final()` no se encuentra declarado previamente. Detectado en compilación
- (8), se realiza llamada a `cuadrado()` antes de su declaración. Detectado en compilación
- (16), la variable `numero` no se encuentra inicializada. Detectado en compilación
- (17), la variable `suma` no se encuentra inicializada. Detectado en compilación
- (17), la variable `numero_cuadrado` no se encuentra inicializada. Detectado en compilación
## Lenguaje - Python
```Python
    1. #!/usr/bin/python
    2. print "\nDEFINICION DE NUMEROS PRIMOS"
    3. r = 1
    4. while r = True:
    5.     N = input("\nDame el numero a analizar: ")
    6.     i = 3
    7.     fact = 0
    8.     if (N mod 2 == 0) and (N != 2):
    9.         print "\nEl numero %d NO es primo\n" % N
   10.      else:
   11.           while i <= (N^0.5):
   12.               if (N % i) == 0:
   13.                   mensaje="\nEl numero ingresado NO es primo\n" % N
   14.                   msg = mensaje[4:6]
   15.                   print msg
   16.                   fact = 1
   17.               i+=2
   18.           if fact == 0:
   19.              print "\nEl numero %d SI es primo\n" % N
   20.      r = input("Consultar otro número? SI (1) o NO (0)--->> ")
```
### Errores sintácticos
- (2), faltan paréntesis, la sintaxis es `print ("cadena")`. Detectado en compilación
- (4), la igualdad en Python se verifica con `==`. Detectado en compilación
- (8), el operador `mod` no existe, en Python se escribe como `%`. Detectado en compilación
- (9), idem (2)
- (15), idem (2)
- (19), idem (2)
### Errores semánticos  
- (13), se está intentando usar el operador `%` en un string que no tiene marcador de formato (`%d`, `%s`, etc.).
### Errores lógicos
- (11), el operador `^` en Python no es potencia, sino XOR bit a bit.
## Lenguaje - Ruby
```Ruby
    1. def ej1
    2. Puts 'Hola, ¿Cuál es tu nombre?'
    3. nom = gets.chomp
    4. puts 'Mi nombre es ', + nom
    5. puts 'Mi sobrenombre es 'Juan''
    6. puts 'Tengo 10 años'
    7. meses = edad*12
    8. dias = 'meses' *30
    9. hs= 'dias * 24'
   1.  puts 'Eso es: meses + ' meses o ' + dias + ' días o ' + hs + ' horas'
   2.  puts 'vos cuántos años tenés'
   3.  edad2 = gets.chomp
   4.  edad = edad + edad2.to_i
   5.  puts 'entre ambos tenemos ' + edad + ' años'
   6.  puts '¿Sabes que hay ' + name.length.to_s + ' caracteres en tu nombre, ' + name + '?'
    end
```
### Errores sintácticos
- (2), la sintáxis correctas es `puts` (en minúscula). Se detecta en ejecución 
- (4), Error en la concatencación  de `'mi nombre es'` y `nom`
- (5), intenta concatenar una variable en un string sin usar `+` o `,`. Se detecta en ejecución 
- (10), idem. Línea 5. Se detecta en ejecución 

### Errores semánticos  
- (7), `edad` no se encuentra declarada. Se detecta en ejecución 
- (13), idem. Línea 7. Se detecta en ejecución 
- (14), `edad` se encuentra mal declarada debido a línea 13. Se detecta en ejecución 
- (15), `name` no se encuentra declarada. Se detecta en ejecución 