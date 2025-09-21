# Ejercicio 8

## Inciso a 
se va a ejecutar el manejador `catch (Exception a)`, ya que la excepción que se levanta en `acceso_por_indice` es la del segundo else, que es de tipo `Exception`. La variable `i` queda con valor 525.

## Inciso b
La excepcion se propaga de `acceso_por_indice` a `main` gracias a la instrucción `throws Exception` en la firma del método el cual indica que el método `acceso_por_indice` puede levantar excepciones de ese estilo pero no las maneja localmente, las cuales son manejadas por el bloque `try` y `catch` de main.

## Inciso c

```Java 
Public static double acceso_por_indice (double [] v, int indice){
    try {
        return v[indice];
    }
    catch(ArrayIndexOutOfBoundsException a){
        system.out.println(“el indice ” + indice + “ se encuentra fuera de rango”);
        system.out.println(a.tostring());
    }
    catch(Exception e){
        system.out.println(a.tostring());
    }
}
```