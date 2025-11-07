# Ejercicio 3

```ada
    Procedure sistema is
        task Central 
            entry señal1();
            entry señal2();
            entry fintimer3();
        end Central;

        task periferico1;
        task periferico2;
        task timer3
            entry iniciartimer3();
        end timer3;

        task body Central is
            ok : boolean := true;
        begin
            accept señal1();
            loop
                select
                    select
                        accept señal1();
                    or delay 120
                        null;
                    end select;
                or
                    select 
                        accept señal2();
                        Timer3.iniciartimer3();
                        while ok loop
                            select
                                accept señal2();
                            or 
                                accept fintimer3();
                                ok := false;
                            end select;
                        end loop;
                    or delay 60
                        accept señal2();
                    end select;
                end select;
            end loop;
        end;     

        task body Timer3 is
        begin
            loop
                accept iniciartimer3();
                delay 180;
                Central.fintimer3();
            end loop;
        end;

        task body periferico1 is
        begin
            loop
                Central.señal1;
            end loop;
        end;

        task body periferico2 is
        begin
            loop
                Central.señal2;
            end loop;
        end;
    begin
        null;
    End sistema;           
```