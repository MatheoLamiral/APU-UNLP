# Ejercicio 5

 ```ada
    Procedure CarrerasUniversitarias is

        task servidor is
            entry presentacionDocumento(doc: in String; res: out Boolean);
        end servidor;

        task type usuario;
        arrUsuario: array (1..U) of usuario;

        task body servidor is
        begin
            loop
                accept presentacionDocumento(doc: in String; res: out Boolean) do
                    res := --procesarDocumento(doc);
                end presentacionDocumento;
            end loop;
        end;

        task body usuario is
            doc: String;
            res: Boolean := false;
        begin
            doc := --generarDocumento();
            while not res loop
                select
                    servidor.presentacionDocumento(doc, res);
                    if not res then
                        doc := --modificarDocumento(doc);
                or delay 120
                    delay 60;
                end select;
            end loop;
        end;
    Begin
        null;
    End CarrerasUniversitarias;
```