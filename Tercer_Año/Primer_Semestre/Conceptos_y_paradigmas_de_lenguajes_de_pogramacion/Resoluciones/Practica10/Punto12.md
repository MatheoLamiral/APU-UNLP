# Ejercicio 12

- Un programa escrito en un lenguaje lógico es una secuencia de “cláusulas”.
- Las cláusulas pueden ser: un “**Hecho**” o una “**Regla**”. 
  - **Hecho**:
      - Expresan relaciones entre objetos
      - Expresan verdades
      - Son expresiones del tipo `p(t1,t2,….tn)`
      - Ejemplos:
          - tiene(coche,ruedas) 🡪 representa el hecho que un coche tiene ruedas
          - longuitud([],0) 🡪 representa el hecho que una lista vacía tiene longuitud cero
          - moneda(peso) 🡪 representa el hecho que peso es una moneda.
  - **Regla**:
      - Cláusula de Horn
      - Tiene la forma: `conclusión :- condición`. (‘:-’ sintaxis prolog)
      - Dónde:
          - :- indica “Si”
          - conclusión es un simple predicado y
          - condición es una conjunción de predicados, separados por comas. Representan un AND lógico
      - En un lenguaje procedural una regla la podríamos representar como: `if condición else conclusión`.