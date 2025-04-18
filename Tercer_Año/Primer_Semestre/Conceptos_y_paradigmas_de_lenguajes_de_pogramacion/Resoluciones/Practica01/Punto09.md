# Ejercicio 9 - C

## Cómo es la estrctura de un programa escrito en C?
Un programa en C está compuesto por distintas secciones que siguen una estructura típica. Las principales partes son:  
1. Directivas del preprocesador  
    - Instrucciones que comienzan con #, como #include, para incluir bibliotecas estándar.  
2. Definiciones globales  
    - Definición de constantes, macros, variables globales y prototipos de funciones.  
3. Función main()  
    - Es el punto de entrada del programa. Desde acá comienza la ejecución.  
4. Funciones auxiliares  
    - Otras funciones definidas por el programador para dividir el código en bloques reutilizables y organizados.  

## Estructura básica
```C
    #include <stdio.h>

    //Prototipo de función 
    void saludar();

    int main() 
    {
        saludar();
        return 0;
    }

    //Definición de funcion
    void saludar()
    {
        printf("Hola mundo");
    }
```

## Existe anidamiento de funciones en C?
No, en C no se permite el anidamiento de funciones. Es decir, no se pueden definir funciones dentro de otras funciones. Todas las funciones deben estar definidas a nivel global, fuera de otras funciones. Sin embargo, una función puede llamar a otras funciones desde su interior, lo cual sí es muy común y totalmente válido.
- Ejemplo válido: la función main() puede llamar a otras funciones.
- Ejemplo no válido: no se puede definir una función nueva dentro del cuerpo de main().
