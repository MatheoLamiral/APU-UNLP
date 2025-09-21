package org.example.patrones;

public abstract class Decorator implements InterfacePaquete {
    private InterfacePaquete paquete;

    public Decorator(InterfacePaquete paquete) {
        this.paquete = paquete;
    }

    public String getDescripcion() {
        return this.paquete.getDescripcion() + this.textoAdicional();
    }

    public String getDestinatario() {
        return this.paquete.getDestinatario();
    }

    public String getDireccionDestino() {
        return this.paquete.getDireccionDestino();
    }

    public double getValorDeclarado() {
        return this.paquete.getValorDeclarado();
    }

    public double getCostoEnvio() {
        return this.paquete.getCostoEnvio() + this.costoAdicional();
    }

    public abstract double costoAdicional();
    public abstract String textoAdicional();

}
