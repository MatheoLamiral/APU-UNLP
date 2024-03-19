#Implementa un programa que solicite al usuario que ingrese una lista de números.
#Luego, imprime la lista pero detén la impresión si encuentras un número negativo.
#Nota: utilice la sentencia break cuando haga falta.

lista = []

cant = int(input('ingrese la cantidad de elementos de la lista: '))

for i in range(cant):
    lista.append(int(input ('ingrese un numero: ')))

for i in lista:
    if i >= 0:
        print(i)
    else:
        break
