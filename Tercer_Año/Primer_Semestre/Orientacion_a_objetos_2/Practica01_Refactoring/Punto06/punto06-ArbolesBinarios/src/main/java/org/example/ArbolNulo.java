package org.example;

public class ArbolNulo extends ArbolBinario {

    public ArbolNulo() {

    }

    @Override
    String recorrido() {
        return "";
    }

    public void setValor(int valor) {

    }

    public ArbolBinario getHijoIzquierdo() {
        return this;
    }

    public void setHijoIzquierdo(ArbolBinario hijoIzquierdo) {

    }

    public ArbolBinario getHijoDerecho() {
        return this;
    }

    public void setHijoDerecho(ArbolBinario hijoDerecho) {

    }
}
