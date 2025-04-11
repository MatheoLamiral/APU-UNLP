#!/bin/bash

for((i=1; i <= 100; i++))
do
        echo "el numero es $i"
        cuadrado=$(expr $i "*" $i)
        echo "su cuadrado es $cuadrado"
done