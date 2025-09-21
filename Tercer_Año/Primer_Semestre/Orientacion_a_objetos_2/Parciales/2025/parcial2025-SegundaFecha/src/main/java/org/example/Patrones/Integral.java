package org.example.Patrones;

import java.time.LocalDate;

public class Integral extends PlanMedico {

    public Integral(LocalDate fechaContratacion, int limitePrestaciones) {
        super(fechaContratacion, limitePrestaciones);
    }

    @Override
    public double cargoGrupoFamiliar(Afiliado context, ICoseguro coseguro) {
        return 3000 * context.getFamiliaresACargo() + context.getSalario() * 0.01;
    }

    @Override
    public double getMontoFijo(ICoseguro coseguro) {
        return 22000;
    }

    @Override
    public double cargoCoberturaViajes(Afiliado context, ICoseguro coseguro) {
        return 0.03 * context.getSalario() - coseguro.getAntiguedad() * 10000;
    }
}
