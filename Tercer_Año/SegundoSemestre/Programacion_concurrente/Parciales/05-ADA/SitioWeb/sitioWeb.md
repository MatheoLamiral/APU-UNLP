# Repaso ADA - Sitio web

```ada
    Procedure SitioWeb is

    task type PersonaPaciente;
    arrPacientes: array(1..N) of PersonaPaciente;
    task type PersonaImpacienete;
    arrPacientes: array(1..M) of PersonaImpaciente;
    task web is
        entry compra(isVendida: out boolean);
    end Web;

    task body PersonaPaciente is
        atendido: boolean := false;
        isVendida: boolean;
    begin
        while not atendido loop
            select 
                Web.compra(isVendida);
                atendido := true;
            or delay 5 minutos
                null;
        end loop;
    end PersonaPaciente;

    task body PersonaImaciente is
        atendido: boolean := false;
        isVendida: boolean;
    begin
        while not atendido loop
            select 
                Web.compra(isVendida);
                atendido := true;
            else
                delay 10 segundos;
            end select;
        end loop;
    end PersonaImaciente;

    task body Web is
        disponibles: integer := E;
    begin
        loop
            accept compra(isVendida: out boolean)do
                if(disponibles > 0)then
                    isVendida := true;
                    disponibles := disponibles - 1;
                else
                    isVendida := false;
                end if;
            end compra;
        end loop;
    end;

    Begin 
        null;
    End SitioWeb;
```