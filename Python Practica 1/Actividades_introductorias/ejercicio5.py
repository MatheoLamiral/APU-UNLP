#Implementa un programa que solicite al usuario que ingrese una lista de números.
#Luego, imprime la lista pero detén la impresión si encuentras un número negativo.
#Nota: utilice la sentencia break cuando haga falta.

numeros = input('ingrese la lista de numeros: ').split()

lista = [int(num) for num in numeros]

msj = "["
for num in lista:
   if num >= 0:
        msj += str(num) + ","
   else:
        break
msj += "]"

print(msj)
