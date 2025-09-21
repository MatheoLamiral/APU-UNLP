# Ejercicio 7

## Implementación en PL/1

```PL/1
    Principal : PROCEDURE OPTIONS(MAIN);
        DECLARE x BIN FIXED; 
        DECLARE b1,b2 BIT(1);

        ON CONDITION Manejador1
        begin
            x = x + 1;
        end;

        ON CONDITION Manejador2
        begin
            x = x * 100;
        end;
        

        P : PROCEDURE (b1:boolean);
            var x:int;
        begin
            x = 1;
            IF (b1 = 0) THEN SIGNAL Manejador1;
        end;
    Begin
        x = 4;
        b2 = 0;
        b1 = 1;
        IF (b1 = 1) THEN SIGNAL Manejador2;
        CALL P(b);
        PUT LIST (x);
    End.
```

## Implementación en Java

```Java
    public class ProgramaConExcepciones {

        static int x;
        static boolean b1;
        static boolean b2;

        // Excepciones personalizadas
        static class Manejador1Exception extends Exception {}
        static class Manejador2Exception extends Exception {}

        static void P(boolean b1) throws Manejador1Exception {
            int x = 1;
            if (b1) {
                throw new Manejador1Exception();
            }
            x = x + 4;
        }

        public static void main(String[] args) {
            x = 4;
            b2 = true;
            b1 = false;

            try {
                if (!b1) {
                    throw new Manejador2Exception();
                }
            } catch (Manejador2Exception e2) {
                x = x * 100; 
            }

            try {
                P(b1); 
            } catch (Manejador1Exception e1) {
                x = x + 1; 
            }

            System.out.println(x);
        }
    }
```