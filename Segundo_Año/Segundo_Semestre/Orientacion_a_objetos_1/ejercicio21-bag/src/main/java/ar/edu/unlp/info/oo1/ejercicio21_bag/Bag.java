package ar.edu.unlp.info.oo1.ejercicio21_bag;


import java.util.Collection;

public interface Bag<T> extends Collection<T> {

    /**
     * Agrega un elemento al Bag, incrementando en 1 su cardinalidad.
     */
    @Override
    boolean add(T element);

    /**
     * Devuelve la cardinalidad del elemento. SÃ­ el elemento no estÃ¡ en el Bag,            
     * devuelve 0.
     */
    int occurrencesOf(T element);

    /**
     * Elimina una referencia del elemento del Bag. SÃ­ el elemento no estÃ¡ en 
     * el Bag, no hace nada.
     */
    void removeOccurrence(T element);

    /**
     * Elimina el elemento del Bag. SÃ­ el elemento no estÃ¡ en el Bag, no hace
     * nada
     */
    void removeAll(T element);

    /**
     * Devuelve el nÃºmero total de elementos en el Bag, es decir, la suma de
     * todas las cardinalidades de todos sus elementos.
     */
    @Override
    int size();
}


