# Ejercicio 12

```Python
    #!/usr/bin/env python
    #calc.py
    def dividir():
        x = a / b
        print (("Resultado"), (x))
    while True:
        try:
            a = int(input("Ingresa el primer numero: \n"))
            b = int(input("Ingresa el segundo numero: \n"))
            dividir()
            break
        except ZeroDivisionError:
            print ("No se permite dividir por cero")
        finally:
            print ("Vuelve a probar")
```

## Diferentes caminos que ejecuta para diferentes valores de ingreso
- Si el usuario ingresa dos números válidos (por ejemplo, 10 y 2), el programa calculará la división y mostrará el resultado (5.0) y luego imprimirá "Vuelve a probar" al ejecutar la cláusula `finally`.
- Si el usuario ingresa 0 como segundo número (por ejemplo, 10 y 0), se lanzará una excepción `ZeroDivisionError`, se imprimirá "No se permite dividir por cero" al entrar a la cláusula `except` y luego se ejecutará "Vuelve a probar" al ejecutar la cláusula `finally`.
- Si el usuario ingresa un valor no numérico (por ejemplo, "abc"), se producirá una excepción `ValueError` al intentar convertir el valor a entero. En este caso, el programa no manejará esta excepción específicamente, por lo que se detendrá y mostrará un mensaje de error. Sin embargo, si se quisiera manejar este caso, se podría agregar un bloque `except ValueError` para capturar la excepción y permitir que el usuario vuelva a intentarlo.

## Agración del uso de una excepción anónima

```Python
    #!/usr/bin/env python
    #calc.py
    def dividir():
        x = a / b
        print (("Resultado"), (x))
    while True:
        try:
            a = int(input("Ingresa el primer numero: \n"))
            b = int(input("Ingresa el segundo numero: \n"))
            dividir()
            break
        except ZeroDivisionError:
            print ("No se permite dividir por cero")
        except:
            print ("Ocurrió un error")
        finally:
            print ("Vuelve a probar")
```