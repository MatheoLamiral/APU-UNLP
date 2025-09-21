# Ejercicio 13

**Problema**: se lee una variable entera por teclado y si es par se imprime “El valor ingresado es PAR” y si es impar imprime “El valor ingresado es IMPAR”
## Paradigma imperativo
```python
    numero = int(input("Ingrese un número entero: "))
    if numero % 2 == 0:
        print("El valor ingresado es PAR")
    else:
        print("El valor ingresado es IMPAR")
```
## Programación lógica
```prolog
    par(X) :- X mod 2 =:= 0.

    ?- read(X), (par(X) -> write('El valor ingresado es PAR') ; write('El valor ingresado es IMPAR')).
```
## Programación orientada a objetos
```Java
    public class Numero {
        private int valor;

        public Numero(int valor) {
            this.valor = valor;
        }

        public void verificarParidad() {
            if (valor % 2 == 0) {
                System.out.println("El valor ingresado es PAR");
            } else {
                System.out.println("El valor ingresado es IMPAR");
            }
        }
    }

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();
            Numero num = new Numero(numero);
            num.verificarParidad();
        }
    }
```
## Programación orientada a eventos
```JavaScript
    document.getElementById("checkButton").addEventListener("click", function() {
        var numero = parseInt(document.getElementById("numeroInput").value);
        if (numero % 2 === 0) {
            alert("El valor ingresado es PAR");
        } else {
            alert("El valor ingresado es IMPAR");
        }
    });
```
## Basados en scripts
```JavaScript
    function verificarParidad() {
        var numero = parseInt(prompt("Ingrese un número entero:"));
        if (numero % 2 === 0) {
            console.log("El valor ingresado es PAR");
        } else {
            console.log("El valor ingresado es IMPAR");
        }
    }

    verificarParidad();
```
## Paradigma aplicativo o funcional
```Haskell
    verificarParidad :: Int -> String
    verificarParidad numero
        | mod numero 2 == 0 = "El valor ingresado es PAR"
        | otherwise = "El valor ingresado es IMPAR"

    main :: IO ()
    main = do
        putStrLn "Ingrese un número entero:"
        input <- getLine
        let numero = read input :: Int
        putStrLn (verificarParidad numero)
```
## Programación orientada a aspectos
```Java
    //AspectJ code
    public aspect ParidadAspect {
        pointcut verificarParidad(int numero): execution(* Numero.verificarParidad(..)) && args(numero);

        before(int numero): verificarParidad(numero) {
            if (numero % 2 == 0) {
                System.out.println("El valor ingresado es PAR");
            } else {
                System.out.println("El valor ingresado es IMPAR");
            }
        }
    }
```
## Programación reactiva 
```JavaScript
    const { fromEvent } = rxjs;
    const { map } = rxjs.operators;

    const input = document.getElementById("numeroInput");
    const button = document.getElementById("checkButton");

    fromEvent(button, 'click')
        .pipe(
            map(() => parseInt(input.value))
        )
        .subscribe(numero => {
            if (numero % 2 === 0) {
                alert("El valor ingresado es PAR");
            } else {
                alert("El valor ingresado es IMPAR");
            }
        });
```