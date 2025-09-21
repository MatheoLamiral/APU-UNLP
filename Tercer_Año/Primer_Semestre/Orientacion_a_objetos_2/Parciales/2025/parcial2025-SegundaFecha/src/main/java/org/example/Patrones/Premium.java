package org.example.Patrones;

import java.time.LocalDate;

public class Premium extends PlanMedico {

    public Premium(LocalDate fechaContratacion, int limitePrestaciones) {
        super(fechaContratacion, limitePrestaciones);
    }

    @Override
    public double cargoGrupoFamiliar(Afiliado context, ICoseguro coseguro) {
        if(context.getFamiliaresACargo() <= 4){
            return 0;
        }
        else{
            return (context.getFamiliaresACargo() - 4) * 2800;
        }
    }

    @Override
    public double getMontoFijo(ICoseguro coseguro) {
        return coseguro.aplicarDescuento(33000);
    }
}
