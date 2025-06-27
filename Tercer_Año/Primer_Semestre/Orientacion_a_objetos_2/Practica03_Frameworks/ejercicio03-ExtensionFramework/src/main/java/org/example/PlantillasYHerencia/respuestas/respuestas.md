# Inciso A
¿Qué debo hacer si aparece una nueva fuente de energía (por ejemplo, paneles solares con baterías)? ¿Cuántas y cuáles clases debo agregar en caso de querer todas las variantes de robots posibles para este nuevo tipo de fuente de energía?

- En total debería agregar 7 clases nuevas
- Debería crear una clase abstracta que hereda de la clase `Robot` llamada `SolarEnergyBateryRobot`
- De ella heredarían las clases `SolarEnergyBateryOvercraftRobot` y `SolarEnergyBateryCaterpillarRobot`
  - De `SolarEnergyBateryOvercraftRobot` heredían las clases `SolarEnergyBateryOvercraftRobotWithLasers` y `SolarEnergyBateryOvercraftRobotWithBombs`
  - De `SolarEnergyBateryCaterpillarRobot` heredían las clases `SolarEnergyBateryCaterpillarRobotWithLasers` y `SolarEnergyBateryCaterpillarRobotWithBombs`

# Inciso B
¿Puedo cambiarle, a un robot existente, el sistema de armas sin tener que instanciar el robot de nuevo?

- No puedo cambiarle el sistema de armas a un robot existente sin instanciarlo de nuevo, ya que el sistema de armas es parte del constructor del robot y no se puede modificar una vez creado el objeto.

# Inciso C
¿Dónde almacenaría usted el nivel de carga de la batería? ¿Qué implicaría eso sí antes de disparar el láser hay que garantizar que la fuente de energía puede satisfacer el consumo del arma?

 - Almacenaría el nivel de carga de la batería en la clase `SolarEnergyBateryRobot`, no la almacenaría en la clase `Robot` porque no corresponderia que una instancia de la clase `NuclearRobot` tenga acceso a un nivel de carga de batería. Almacenar en la clase `SolarEnergyBateryRobot` el nivel de carga de la batería implicaría que los tipos de robots que no requieran de un nivel de batería no tengan acceso al nivel de batería, evitamos que el resto de clases tengan atriutos innecesaria.