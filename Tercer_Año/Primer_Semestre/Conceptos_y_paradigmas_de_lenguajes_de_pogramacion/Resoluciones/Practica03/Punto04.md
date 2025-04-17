# Ejercicio 4

## Diferencia entre error sintáctico y error semántico
El error **sintáctico** es un error en la **estructura o gramática del código**, mientras que un error **semántico** es un error en el **significado o lógica del código**. Si ocurre un error sintáctico el programa no compila, en cambio, si ocurre un error semántico el programa compila pero no se comporta como se espera

## Ejemplos
### Ejemplo error sintáctico
```Pascal
    Program ejemploSintactico;
    var
        x : integer;
    Begin
        x := 1;
        write(x)
    End.
```
- En este código de pascal ocurre un error sintáctico ya que falta `;` detras de la expresión `write(x)`
- El compilador mostrará un error porque la sintáxis es incorrecta
### Ejemplo error semántico
```Pascal
    Program ejemploSintactico;
    var
        x : integer;
    Begin
        write(x);
    End.
```
- En este códgio hay un error semántico ya que la variable `x` no se encuentra inicializada al momento de realizar `write(x)`
- La sintáxis está bien escrita, pero falla la semántica