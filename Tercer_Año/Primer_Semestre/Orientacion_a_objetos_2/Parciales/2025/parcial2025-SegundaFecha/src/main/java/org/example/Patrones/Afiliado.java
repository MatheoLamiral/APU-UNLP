package org.example.Patrones;

import java.time.LocalDate;
import java.util.List;

public class Afiliado {
    private String nombre;
    private int familiaresACargo;
    private double salario;
    private LocalDate fechaNacimiento;
    private List<PlanMedico> planesPrevios;
    private PlanMedico plan;
    private ICoseguro coseguro;

    public Afiliado(String nombre, int familiaresACargo, double salario, LocalDate fechaNacimiento, List<PlanMedico> planesPrevios, PlanMedico plan, ICoseguro coseguro) {
        this.nombre = nombre;
        this.familiaresACargo = familiaresACargo;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.planesPrevios = planesPrevios;
        this.plan = plan;
        this.coseguro = coseguro;
    }

    public Afiliado(String nombre, int familiaresACargo, double salario, LocalDate fechaNacimiento, List<PlanMedico> planesPrevios, PlanMedico plan){
        this.nombre = nombre;
        this.familiaresACargo = familiaresACargo;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.planesPrevios = planesPrevios;
        this.plan = plan;
        this.coseguro = new CoseguroNulo();
    }

    public double calcularMonto(){
        return this.plan.calcularMonto(this, this.coseguro);
    }

    public void setPlanMedico(PlanMedico plan) {
        this.plan = plan;
    }

    public void setCoseguro(ICoseguro coseguro) {
        this.coseguro = coseguro;
    }

    public int getFamiliaresACargo() {
        return this.familiaresACargo;
    }

    public double getSalario() {
        return this.salario;
    }

}
