# Repaso PMS - Corredores y Chalecos

```kotlin
    Process Corredor[id: 1..C]{
        int nro;
        Administrador!pedirNumero(id);
        Empleado[*]?asignar(nro);
    }

    Process Administrador{
        cola corredores;
        int idCorredor;
        int idCoordinador;
        do Corredor[*]?pedirNumero(idCorredor) =>
            corredores.push(idCorredor);
        [] (corredores.isNotEmpty); Coordinador[*]?libre(idCoordinador) =>
            Coordinador[idCoordinador]!asignado(corredores.pop());
    }

    Process Coordinador[id: 1..3]{
        int idCorredor;
        int nro;
        while true {
            Administrador!libre(id);
            Administrado?asignado(idCorredor);
            nro = //generarNumeroCorredor();
            Corredor[idCorredor]!asignar(nro);
        }
    }
```