package org.example.Patrones;

import java.time.LocalDate;

public class Obligatorio extends PlanMedico {

    public Obligatorio(LocalDate fechaContratacion, int limitePrestaciones) {
        super(fechaContratacion, limitePrestaciones);
    }

    @Override
    public double cargoGrupoFamiliar(Afiliado context, ICoseguro coseguro) {
        return coseguro.aplicarDescuento(3500 * context.getFamiliaresACargo());
    }

    @Override
    public double getMontoFijo(ICoseguro coseguro) {
        return 15000;
    }

    @Override
    public double cargoCoberturaPorInternacion(ICoseguro coseguro) {
        return 0;
    }
}
