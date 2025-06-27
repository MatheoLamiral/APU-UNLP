package org.example;

public class ArbolInOrden extends  ArbolBinario{
    public ArbolInOrden(int valor) {
        super(valor);
    }

    public String recorrido() {
        String resultado = "";
        resultado += this.getHijoIzquierdo().recorrido();
        resultado += this.getValor() + " - ";
        resultado += this.getHijoDerecho().recorrido();
        return resultado;
    }
}
