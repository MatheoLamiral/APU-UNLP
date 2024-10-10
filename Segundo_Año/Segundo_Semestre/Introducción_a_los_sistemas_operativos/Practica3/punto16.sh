#!/bin/bash

#verifico que se pasen los parametros correctos
if [ $# -ne 1 ]
then
        echo "se requiere 1 parametro"
        exit 1
fi

#escribo en reporte.txt sobreescribiento lo que tuviese anteriormente
echo "user      |       files" > reporte.txt
#escribo en reporte.txt agregando al final sin sobreescribir
echo "-----------------------" >> reporte.txt

for line in $(cat /etc/passwd | cut -d : -f 1,6)
do
        user=$(echo $line | cut -d : -f 1)
        home=$(echo $line | cut -d : -f 2)
        #si el directorio home del usuario es valido/existe
        if [ -d $home ]
        then
                #cuento los archivos que con extension $1
                echo "$user     |        $(find $home -name "*.$1" | wc -l)" >> reporte.txt
        fi
done