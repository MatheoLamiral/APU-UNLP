# Ejercicio 1

- Inciso a
```ada
    Procedure Puente1 is
        task puente is
            entry cruzar_auto();
            entry salir_auto();
            entry cruzar_camioneta();
            entry salir_camioneta();
            entry cruzar_camion();
            entry salir_camion();
        end puente;

        task type auto;
        arrAuto: array (1..M) of auto;
        task type camioneta;
        arrCamioneta: array (1..K) of camioneta;
        task type camion;
        arrCamion: array (1..L) of camion;

        task body puente is
            peso_actual: integer := 0;
            capacidad_maxima: constant integer := 5;
            peso_auto: constant integer := 1;
            peso_camioneta: constant integer := 2;
            peso_camion: constant integer := 3;
        begin
            loop
                select
                    when (peso_actual + peso_auto) <= capacidad_maxima =>
                        accept cruzar_auto() do
                            peso_actual := peso_actual + peso;
                or
                    when (peso_actual + peso_camioneta) <= capacidad_maxima =>
                        accept cruzar_camioneta() do
                            peso_actual := peso_actual + peso;
                or
                    when (peso_actual + peso_camion) <= capacidad_maxima =>
                        accept cruzar_camion() do
                            peso_actual := peso_actual + peso;
                or
                    accept salir_auto() do
                        peso_actual := peso_actual - peso_auto;
                or
                    accept salir_camioneta() do
                        peso_actual := peso_actual - peso_camioneta;    
                or
                    accept salir_camion() do
                        peso_actual := peso_actual - peso_camion;
                end cruzar;
            end loop;
        end puente;

        task body auto is
        begin
            puente.cruzar_auto;
            -- cruce del puente
            puente.salir_auto;
        end auto;

        task body camioneta is
        begin
            puente.cruzar_camioneta;
            -- cruce del puente
            puente.salir_camioneta;
        end camioneta;

        task body camion is
        begin
            puente.cruzar_camion;
            -- cruce del puente
            puente.salir_camion;
        end camion;
    Begin
        null;
    End Puente1;
```
- Inciso b
```ada
    Procedure Puente2 is
        task puente is
            entry cruzar_auto();
            entry salir_auto();
            entry cruzar_camioneta();
            entry salir_camioneta();
            entry cruzar_camion();
            entry salir_camion();
        end puente;

        task type auto;
        arrAuto: array (1..M) of auto;
        task type camioneta;
        arrCamioneta: array (1..K) of camioneta;
        task type camion;
        arrCamion: array (1..L) of camion;

        task body puente is
            peso_actual: integer := 0;
            capacidad_maxima: constant integer := 5;
            peso_auto: constant integer := 1;
            peso_camioneta: constant integer := 2;
            peso_camion: constant integer := 3;
        begin
            loop
                select
                    when (cruzar_camion'count=0)and(peso_actual + peso_auto) <= capacidad_maxima =>
                        accept cruzar_auto() do
                            peso_actual := peso_actual + peso;
                or
                    when (cruzar_camion'count=0)and(peso_actual + peso_camioneta) <= capacidad_maxima =>
                        accept cruzar_camioneta() do
                            peso_actual := peso_actual + peso;
                or
                    when (peso_actual + peso_camion) <= capacidad_maxima =>
                        accept cruzar_camion() do
                            peso_actual := peso_actual + peso;
                or
                    accept salir_auto() do
                        peso_actual := peso_actual - peso_auto;
                or
                    accept salir_camioneta() do
                        peso_actual := peso_actual - peso_camioneta;    
                or
                    accept salir_camion() do
                        peso_actual := peso_actual - peso_camion;
                end cruzar;
            end loop;
        end puente;

        task body auto is
        begin
            puente.cruzar_auto;
            -- cruce del puente
            puente.salir_auto;
        end auto;

        task body camioneta is
        begin
            puente.cruzar_camioneta;
            -- cruce del puente
            puente.salir_camioneta;
        end camioneta;

        task body camion is
        begin
            puente.cruzar_camion;
            -- cruce del puente
            puente.salir_camion;
        end camion;
    Begin
        null;
    End Puente2;
```