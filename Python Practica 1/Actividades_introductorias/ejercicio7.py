#Escribe un programa que tome una lista de números enteros como entrada del usuario.
#Luego, convierte cada número en la lista a string y únelos en una sola cadena,
#separados por guiones ('-'). Sin embargo, excluye cualquier número que sea múltiplo
#de 3 de la cadena final.

lista = []

cant = int(input('ingrese la cantidad de elementos de la lista: '))

for i in range(cant):
    lista.append(int(input('ingrese un número entero: ')))
 
msj = ''   
for elem in lista:
    if elem % 3 != 0:
        msj += str(elem) + '-'

print(msj)    