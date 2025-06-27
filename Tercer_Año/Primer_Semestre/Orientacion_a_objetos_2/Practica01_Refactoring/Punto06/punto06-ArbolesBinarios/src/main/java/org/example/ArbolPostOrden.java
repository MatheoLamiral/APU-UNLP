package org.example;

public class ArbolPostOrden extends ArbolBinario{
    public ArbolPostOrden(int valor) {
        super(valor);
    }

    public String recorrido() {
        String resultado = "";
        resultado += this.getHijoIzquierdo().recorrido();
        resultado += this.getHijoDerecho().recorrido();
        resultado += this.getValor() + " - ";
        return resultado;
    }
}
