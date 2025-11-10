```
    Process Enfermera
    {
        for i: 1..30{
            SalaEspera.siguiente(i);
            Vacunatorio.atender(i)
        }
    }

    Process Paciente [id: 1..30]
    {
        turno = //sacarTurno();
        SalaEspera.llegar(turno);
        Vacunatorio.llegar();

    }

    Monitor SalaEspera
    {
        cond esperaVacuna[30];
        cond enfermera;
        boolean vec llegadas([30] false);

        Procedure llegar(turno: in int){
            llegadas[turno] = true;
            signal(enfermera);
            wait(esperaVacuna[turno]);
        } 

        Procedure siguiente(turno: in int){
            while(not esperandoVacuna[turno]){
                wait(enfermera);
            }
            signal(esperaVacuna[turno]);
        }
    }

    Monitor Vacunatorio
    {
        cond enfermera;
        cond paciente;
        boolean llego = false;

        procedure llegar(){
            llego = true;
            signal(enfermera);
            wait(paciente);
        }

        Procedure Atender(){
            if(not llego){
                wait(enfermera);
            }
            //Vacunar();
            signal(paciente);
        }
    }
```