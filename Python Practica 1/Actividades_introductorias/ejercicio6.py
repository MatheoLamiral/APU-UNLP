#Modifique el ejercicio 4 para que dada la lista de número genere dos nuevas listas, una
#con los número pares y otras con los que son impares. Imprima las listas al terminar de
#procesarlas.

lista = [2,3,4,5,6,7,8]
lista_Pares = []
lista_impares = []

for elem in lista:
    if elem % 2 == 0:
        lista_Pares.append(elem)
    else:
        lista_impares.append(elem)
   
msj =''

print ('lista Pares:') 
msj += '['    
for elem in lista_Pares:
    msj += str(elem) + ','
msj += ']'

print(msj)
print ('lista Impares:')
msj = '['
for elem in lista_impares:
       msj += str(elem) + ','
msj += ']'
print(msj)