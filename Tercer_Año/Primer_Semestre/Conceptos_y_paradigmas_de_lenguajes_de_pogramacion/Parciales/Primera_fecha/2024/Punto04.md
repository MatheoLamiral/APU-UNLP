# Punto 4

## Inciso a
- En c las variables estáticas se almacenan en ...?
  - En la sección de datos del segmento de memoria, que es una parte de la memoria donde se almacenan las variables globales y estáticas.
- La política de inicialización de variables en C para las variabeles globales y estáticas es ..?
  - Las variables globales y estáticas se inicializan a cero por defecto si no se les asigna un valor explícito al momento de la declaración.
## Inciso b 
```C
    void ejemplo(){
        static int x = 0;
        x++;
    }

    int main(){
        ejemplo();
        return 0;
    }
```
## Inciso c (verdadero o falso)
- "un lenguaje interpretado sólo detecta errores semántcos, ya que se chequea todo en ejecución".
  - Falso. Que un lenguaje sea interpretado no significa que solo se detecten errores semánticos, sino que se evaluará en tiempo de ejecución línea por línea, y podrán detectarse tanto errores sintácticos como semánticos 

