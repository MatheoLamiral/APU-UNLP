#Haz un programa que pida al usuario que ingrese una temperatura en grados Celsius y
#luego convierta esa temperatura a grados Fahrenheit, mostrando el resultado.

temp_en_c = input ('ingrese la temperatura en grados Celsius: ')

temp_en_f = (int(temp_en_c) * 9/5 ) + 32

print (f'la temperatura que ingreso convertida a Fahrebheit es {temp_en_f}')