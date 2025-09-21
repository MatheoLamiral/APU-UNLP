package ar.info.unlp.arboles;

public class ArbolBinario {
    private int valor;
    private ArbolBinario hijoIzquierdo;
    private ArbolBinario hijoDerecho;

    public ArbolBinario(int valor) {
        this.valor = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

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

    public void setDerecha(ArbolBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

   public String recorrerPreorden() {
        String resultado = valor + " - ";
        if (this.getHijoIzquierdo() != null) {
        	resultado += this.getHijoIzquierdo().recorrerPreorden();
	}
        if (this.getHijoDerecho() != null) {
        	resultado += this.getHijoDerecho().recorrerPreorden();
        }
        return resultado;
    }

   public String recorrerInorden() {
        String resultado = "";
        if (this.getHijoIzquierdo() != null) {
        	resultado += this.getHijoIzquierdo().recorrerInorden();
	}
        resultado += valor + " - ";
        if (this.getHijoDerecho() != null) {
        	resultado += this.getHijoDerecho().recorrerInorden();
        }
        return resultado;
    }

   public String recorrerPostorden() {
        String resultado = "";
        if (this.getHijoIzquierdo() != null) {
        	resultado += this.getHijoIzquierdo().recorrerPostorden();
	}
        if (this.getHijoDerecho() != null) {
        	resultado += this.getHijoDerecho().recorrerPostorden();
        }
        resultado += valor + " - ";
        return resultado;
    }

}

