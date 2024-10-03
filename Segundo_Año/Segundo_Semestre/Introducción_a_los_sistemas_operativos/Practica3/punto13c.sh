#!/bin/bash

if [ $# -ne 1 ]
then
        echo "no se ingresaron parametros"
        exit 1
fi

#verifico si existe
if [ -e $1 ]
then
        #verifivo si es un directorio
        if [ -d $1 ]
        then
                echo "es un directorio"
        else
                echo "es un archivo"
        fi
else
        mkdir $1
fi