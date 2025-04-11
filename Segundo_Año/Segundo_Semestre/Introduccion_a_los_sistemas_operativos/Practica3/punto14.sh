#!/bin/bash

#verifico si se ingreso la cantidad correcta de parametros
if [ $# -ne 3 ]
then
        echo "se deben ingresar 2 parametros"
        exit 1
fi

dir=$3

#verifico si el directorio ingresado es valido
if [! -d $dir  ]
then
        echo "el directorio ingresado no es valido"
        exit
fi

op=$2
cad=$3

for arch in $(ls $dir)
do
        #verifico si el archivo es regular
        if [ -f "$dir/$arch" ]
        then
                case op in
                        "-a")
                                mv "$dir/$arch" "$dir/$arch$cad"
                        ;;
                        "-b")
                                mv "$dir/$arch" "$dir/$cad$arch"
                        ;;
                        "*")
                                echo "la opcion ingresada no es valida"
                        ;;
                esac
        fi
done

ls $dir