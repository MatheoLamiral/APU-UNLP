### If
```bash
    if [ condition ]
    then
        block
    fi
```
### Case
```bash
    case $variable in
        "valor 1")  
            block 
        ;; 
        "valor 2") 
            block 
        ;;
        *) 
            block
        ;;
    esac
```
### While
```bash
    while [ condition ] #Mientras se cumpla la condición
    do 
        block 
    done
```
### Until
```bash
    until [ condition ] #Mientras NO se cumpla la condición
    do 
        block 
    done
```
### For
```bash
    for ((i=0; i < 10; i++))
    do
        block
    done
```
### For each
```bash
    for i in value1 value2 value3 valueN;
    do
        block
    done
```

### Select
```bash
    elect variable in opcion1 opcion2 opcion3
    do 
        # en $variable está el valor elegido
        block 
    done
```