# Inciso A
¿Qué debo hacer si aparece una nueva fuente de locomoción (por ejemplo, motor con ruedas con tracción 4x4)? ¿Cuántas y cuáles clases debo agregar en caso de querer todas las variantes de robots posibles para este nuevo tipo de sistema de locomoción?

- En total debería ageragar 1 clase nueva
- Debería crear la `FourXFour` que hereda de la clase `LocomotionSystem` 

# Inciso B
¿Puedo cambiarle, a un robot existente, el sistema de armas sin tener que instanciar el robot de nuevo?

-  Si ya que el sistema de armas de un robot corresponde a una variable del mimso, que podría ser seteada
# Inciso C
¿Dónde almacenaría usted el nivel de carga de la batería? ¿Qué implicaría eso sí antes de disparar el láser hay que garantizar que la fuente de energía puede satisfacer el consumo del arma?

- Deberia almacenar el nivel de batería en la clase `Robot` ya que si lo almaceno en la clase `SolarWithBattery` no seria accesible desde la clase LaserSystem. Esto implica que las clases que no requieran de un nivel de batería deberían tener de todas formas este campo 
