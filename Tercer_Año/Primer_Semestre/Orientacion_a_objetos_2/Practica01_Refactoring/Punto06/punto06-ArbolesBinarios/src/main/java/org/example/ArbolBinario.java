package org.example;

public abstract class ArbolBinario {
    private int valor;
    private ArbolBinario hijoIzquierdo;
    private ArbolBinario hijoDerecho;

    public ArbolBinario(){
        this.valor = -1;
    }

    public ArbolBinario(int valor) {
        this.valor = valor;
        this.hijoIzquierdo = new ArbolNulo();
        this.hijoDerecho = new ArbolNulo();
    }

    abstract String recorrido();

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ArbolBinario getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(ArbolBinario hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public ArbolBinario getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(ArbolBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }







}

