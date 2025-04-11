#!/bin/bash

echo "elegir una opcion: 1(listar), 2(dondeEstoy), 3(quienEsta)"

read num

case $num in
        "1")
        ls
        ;;
        "2")
        pwd
        ;;
        "3")
        who
        ;;
esac