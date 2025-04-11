#!/bin/bash

dir=$(dirname $0)

opciones=(12 13 14 16 17 18 0)

echo "Elija una opcion (0 para salir)"

select op in "${opciones[@]}"
do
	case $op in
		"12")
		;;
		"13")
		;;
		"14")
			echo "ingrese un directorio"
			read par1
			echo "ingrese una cadena"
			read par2
			echo "ingrese una opcion (-a o -b)"
			read par3
			bash $dir/punto14.sh $par1 $par2 $par3
		;;
		"15")
			ech
		;;
		"16")
		;;
		"17")
		;;
	esac
done


