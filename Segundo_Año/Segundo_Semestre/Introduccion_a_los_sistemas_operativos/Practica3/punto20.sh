#!/bin/bash

#vector del select
opt=("push" "pop" "length" "print" "salir")

#pila vacia
pila=()

push(){
        pila+=("$1")
}
pop(){
        if [ ${#pila[@]} -eq 0 ]
        then
                echo "la pila esta vacia"
                return 1
        else
                #calculo la ultima posicion
                ult=$(expr ${#pila[@]} - 1)
                #guardo la ultima posicion
                val=${pila[$ult]}
                #elimino la ultima posicion
                 unset pila[$ult]
                #hago corrimiento para eliminar el espacio libre
                pila=(${pila[@]})
                echo "$val se saco de la pila"
        fi
}

length(){
        echo ${#pila[@]}
}

print(){
        for elem in ${pila[@]}
        do
                echo "$elem | "
        done
}

select op in "${opt[@]}"
do
        case $op in
                "push")
                        echo "ingrese un valor para realizar el push"
                        read val
                        push $val
                ;;
                "pop")
                        pop
                ;;
                "length")
                        echo "el tamaño de la pila es $(length)"
                ;;
                "print")
                        print
                ;;
                "salir")
                        exit 0
                ;;
        esac
done