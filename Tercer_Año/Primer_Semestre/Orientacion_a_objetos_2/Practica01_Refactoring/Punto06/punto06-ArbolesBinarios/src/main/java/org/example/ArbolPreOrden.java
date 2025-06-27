package org.example;

public class ArbolPreOrden extends ArbolBinario{
    public ArbolPreOrden(int valor) {
        super(valor);
    }

    public String recorrido() {
        String resultado = this.getValor() + " - ";
        resultado += this.getHijoIzquierdo().recorrido();
        resultado += this.getHijoDerecho().recorrido();
        return resultado;
    }
}
