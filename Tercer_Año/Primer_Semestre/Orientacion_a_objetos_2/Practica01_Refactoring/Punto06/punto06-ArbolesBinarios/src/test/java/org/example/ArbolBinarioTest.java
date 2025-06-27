package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.ArbolBinario;

class ArbolBinarioTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	  @Test
	    public void testUnSoloNodo() {
		  	ArbolBinario arbolPreOrd = new ArbolPreOrden(10);
		  	ArbolBinario arbolInOrd = new ArbolInOrden(10);
		  	ArbolBinario arbolPosOrd = new ArbolPostOrden(10);
	        assertEquals("10 - ", arbolPreOrd.recorrido());
	        assertEquals("10 - ", arbolInOrd.recorrido());
	        assertEquals("10 - ", arbolPosOrd.recorrido());
	    }

	    @Test
	    public void testSoloHijoIzquierdo() {
			ArbolBinario arbolPreOrd = new ArbolPreOrden(10);
			ArbolBinario arbolInOrd = new ArbolInOrden(10);
			ArbolBinario arbolPosOrd = new ArbolPostOrden(10);
	        arbolPreOrd.setHijoIzquierdo(new ArbolPreOrden(5));
			arbolInOrd.setHijoIzquierdo(new ArbolInOrden(5));
			arbolPosOrd.setHijoIzquierdo(new ArbolPostOrden(5));
	        assertEquals("10 - 5 - ", arbolPreOrd.recorrido());
	        assertEquals("5 - 10 - ", arbolInOrd.recorrido());
	        assertEquals("5 - 10 - ", arbolPosOrd.recorrido());
	    }

	    @Test
	    public void testSoloHijoDerecho() {
			ArbolBinario arbolPreOrd = new ArbolPreOrden(10);
			ArbolBinario arbolInOrd = new ArbolInOrden(10);
			ArbolBinario arbolPosOrd = new ArbolPostOrden(10);
			arbolPreOrd.setHijoDerecho(new ArbolPreOrden(15));
			arbolInOrd.setHijoDerecho(new ArbolInOrden(15));
			arbolPosOrd.setHijoDerecho(new ArbolPostOrden(15));
	        assertEquals("10 - 15 - ", arbolPreOrd.recorrido());
	        assertEquals("10 - 15 - ", arbolInOrd.recorrido());
	        assertEquals("15 - 10 - ", arbolPosOrd.recorrido());
	    }

	    @Test
	    public void testArbolCompletoTresNodos() {
			ArbolBinario arbolPreOrd = new ArbolPreOrden(10);
			ArbolBinario arbolInOrd = new ArbolInOrden(10);
			ArbolBinario arbolPosOrd = new ArbolPostOrden(10);
			arbolPreOrd.setHijoIzquierdo(new ArbolPreOrden(5));
			arbolInOrd.setHijoIzquierdo(new ArbolInOrden(5));
			arbolPosOrd.setHijoIzquierdo(new ArbolPostOrden(5));
			arbolPreOrd.setHijoDerecho(new ArbolPreOrden(15));
			arbolInOrd.setHijoDerecho(new ArbolInOrden(15));
			arbolPosOrd.setHijoDerecho(new ArbolPostOrden(15));
	        assertEquals("10 - 5 - 15 - ", arbolPreOrd.recorrido());
	        assertEquals("5 - 10 - 15 - ", arbolInOrd.recorrido());
	        assertEquals("5 - 15 - 10 - ", arbolPosOrd.recorrido());
	    }

	    @Test
	    public void testArbolConVariosNiveles() {
			ArbolBinario arbolPreOrd = new ArbolPreOrden(10);
			ArbolBinario arbolInOrd = new ArbolInOrden(10);
			ArbolBinario arbolPosOrd = new ArbolPostOrden(10);

			ArbolBinario n5PreOrd = new ArbolPreOrden(5);
			ArbolBinario n5InOrd = new ArbolInOrden(5);
			ArbolBinario n5PosOrd = new ArbolPostOrden(5);

			ArbolBinario n15PreOrd = new ArbolPreOrden(15);
			ArbolBinario n15InOrd = new ArbolInOrden(15);
			ArbolBinario n15PosOrd = new ArbolPostOrden(15);

			ArbolBinario n3PreOrd = new ArbolPreOrden(3);
			ArbolBinario n3InOrd = new ArbolInOrden(3);
			ArbolBinario n3PosOrd = new ArbolPostOrden(3);

			ArbolBinario n7PreOrd = new ArbolPreOrden(7);
			ArbolBinario n7InOrd = new ArbolInOrden(7);
			ArbolBinario n7PosOrd = new ArbolPostOrden(7);

			ArbolBinario n12PreOrd = new ArbolPreOrden(12);
			ArbolBinario n12InOrd = new ArbolInOrden(12);
			ArbolBinario n12PosOrd = new ArbolPostOrden(12);

			ArbolBinario n18PreOrd = new ArbolPreOrden(18);
			ArbolBinario n18InOrd = new ArbolInOrden(18);
			ArbolBinario n18PosOrd = new ArbolPostOrden(18);

			arbolPreOrd.setHijoIzquierdo(n5PreOrd);
			arbolPreOrd.setHijoDerecho(n15PreOrd);
			n5PreOrd.setHijoIzquierdo(n3PreOrd);
			n5PreOrd.setHijoDerecho(n7PreOrd);
			n15PreOrd.setHijoIzquierdo(n12PreOrd);
			n15PreOrd.setHijoDerecho(n18PreOrd);

			arbolInOrd.setHijoIzquierdo(n5InOrd);
			arbolInOrd.setHijoDerecho(n15InOrd);
			n5InOrd.setHijoIzquierdo(n3InOrd);
			n5InOrd.setHijoDerecho(n7InOrd);
			n15InOrd.setHijoIzquierdo(n12InOrd);
			n15InOrd.setHijoDerecho(n18InOrd);

			arbolPosOrd.setHijoIzquierdo(n5PosOrd);
			arbolPosOrd.setHijoDerecho(n15PosOrd);
			n5PosOrd.setHijoIzquierdo(n3PosOrd);
			n5PosOrd.setHijoDerecho(n7PosOrd);
			n15PosOrd.setHijoIzquierdo(n12PosOrd);
			n15PosOrd.setHijoDerecho(n18PosOrd);

	        assertEquals("10 - 5 - 3 - 7 - 15 - 12 - 18 - ", arbolPreOrd.recorrido());
	        assertEquals("3 - 5 - 7 - 10 - 12 - 15 - 18 - ", arbolInOrd.recorrido());
	        assertEquals("3 - 7 - 5 - 12 - 18 - 15 - 10 - ", arbolPosOrd.recorrido());
	    }
	}
