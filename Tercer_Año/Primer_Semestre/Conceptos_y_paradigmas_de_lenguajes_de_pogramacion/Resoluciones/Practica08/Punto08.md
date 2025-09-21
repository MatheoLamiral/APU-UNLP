# Ejercicio 8

## Pasaje a ADA
- Puede trasladarse a ada de la siguiente manera:
    ```ADA
        procedure Evaluar_Puntos is
            type Rango is range 0 .. 100;  -- Definimos el tipo explícitamente
            puntos : Rango := 10;
        begin
            case puntos is
                when 1 .. 5 =>
                    Put_Line("No puede continuar");
                when 10 =>
                    Put_Line("Trabajo terminado");
                when others =>
                    Put_Line("Otro estado");
            end case;
        end Evaluar_Puntos;
    ```
- ADA requiere que el tipo de la variable usada en el case sea ordinal y conocido.
- El uso de `when others` es obligatorio.
## Pasaje a C
- En C no pueden utilizarse rangos como tal, pero podría simularse de la siguiente forma:
    ```C
        int main() {
            int puntos = 10;

            switch (puntos) {
                case 1:
                    printf("No puede continuar\n");
                    break;
                case 2:
                    printf("No puede continuar\n");
                    break;
                case 3:
                    printf("No puede continuar\n");
                    break;
                case 4:
                    printf("No puede continuar\n");
                    break;
                case 5:
                    printf("No puede continuar\n");
                    break;
                case 10:
                    printf("Trabajo terminado\n");
                    break;
                default:
                    printf("Otro estado\n");
                    break;
            }

            return 0;
        }
    ```
- En C se simula un rango repitiendo los case para cada valor.
- Es necesario incluir break para evitar el fall-through (si no se especifica una sentencia break pordia llegar a entrar en más de un case).