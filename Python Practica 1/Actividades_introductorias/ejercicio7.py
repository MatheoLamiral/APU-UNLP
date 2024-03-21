#Escribe un programa que tome una lista de números enteros como entrada del usuario.
#Luego, convierte cada número en la lista a string y únelos en una sola cadena,
#separados por guiones ('-'). Sin embargo, excluye cualquier número que sea múltiplo
#de 3 de la cadena final.

numeros = input('ingrese una lista de numeros separada por espacios: ').split()

lista = [int(num) for num in numeros]

msj = ''   
for num in lista:
    if num % 3 != 0:
        msj += str(num) + '-'

print(msj)    