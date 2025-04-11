### <mark>ID: Ingresar reclamo</mark>
### TÍTULO: Como empleado quiero ingresar un reclamo para poder registrarlo en el sistema
### REGLAS DE NEGOCIO:
- la dirección y el teléfono deben pertenecer a la ciudad
- los reclamos inician con nivel de prioridad 1
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Ingreso exitoso**    
**DADO** la dirección calle1 nro 123 y el teléfono 221-123-456 ambos pertenecientes a la ciudad
**CUANDO** el empleado ingresa nombre pepe, dni 223457643, mail pepe@gmail.com, dirección calle1 nro 123, teléfono 221-123-456 y "ingresar reclamo"
**ENTONCES** el sistema genera un número de reclamo, lo etiqueta como "pendiente" y envía un correo al interesado con los detalles e inicia con nivel de prioridad 1 el reclamo

**Escenario 2: Ingreso fallido por direccion que no corresponde a la ciudad**  
**DADO** la dirección calle1 nro 123 que no corresponde a la ciudad y el teléfono 221-123-456 perteneciente a la ciudad
**CUANDO** el empleado ingresa nombre pepe, dni 223457643, mail pepe@gmail.com, dirección calle1 nro 123, teléfono 221-123-456 y "ingresar reclamo"
**ENTONCES** el sistema informa un error ya que la dirección ingresada no corresponde a la ciudad  

**Escenario 3: Ingreso fallido por teléfono que no corresponde a la ciudad**  
**DADO** la dirección calle1 nro 123 que corresponde a la ciudad y el teléfono 221-123-456 que no pertenece a la ciudad
**CUANDO** el empleado ingresa nombre pepe, dni 223457643, mail pepe@gmail.com, dirección calle1 nro 123, teléfono 221-123-456 y "ingresar reclamo"
**ENTONCES** el sistema informa un error ya que el teléfonos ingresado no corresponde a la ciudad   

---

### <mark>ID: Agregar evento</mark>
### TÍTULO: Como empleado quiero agregar un evento a un reclamo para actualizar el mismo
### REGLAS DE NEGOCIO:
- no pueden agregarse eventos a un reclamo con prioridad 5
- no pueden agregarse eventos sin han pasado 15 días desde el último evento
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Agregación exitosa**    
**DADO** el número de reclamo 123 correspondiente a un reclamo registrado en el sistema, que el reclamo cuenta con prioridad 3 y pasaron 4 días desde el último evento agregado
**CUANDO** el empleado ingresa el número de reclamo 123, la fehca 10/10/2024, descripción desc1 y presiona "agregar evento" 
**ENTONCES** el sistema vincula el evento al reclamo, aumenta su nivel de prioridad y notifica el interesado por mail    

**Escenario 2: Agregación fallida por nivel de prioridad 5**  
**DADO** el número de reclamo 123 correspondiente a un reclamo registrado en el sistema, que el reclamo cuenta con prioridad 5 y pasaron 4 días desde el último evento agregado
**CUANDO** el empleado ingresa el número de reclamo 123, la fehca 10/10/2024, descripción desc1 y presiona "agregar evento" 
**ENTONCES** el sistema infroma error ya que el nivel de prioridad del reclamo es igual a 5

**Escenario 3: Agregación fallida por haber transucrrido 15 o más días despues del último evento**  
**DADO** el número de reclamo 123 correspondiente a un reclamo registrado en el sistema, que el reclamo cuenta con prioridad 3 y pasaron 16 días desde el último evento agregado
**CUANDO** el empleado ingresa el número de reclamo 123, la fehca 10/10/2024, descripción desc1 y presiona "agregar evento" 
**ENTONCES** el sistema informa que error ya que pasaron 15 días o más desde el último evento agregado

**Escenario 3: Agregación fallida por número inválido**  
**DADO** el número de reclamo 123 que no corresponde a un reclamo registrado en el sistema 
**CUANDO** el empleado ingresa el número de reclamo 123, la fehca 10/10/2024, descripción desc1 y presiona "agregar evento" 
**ENTONCES** el sistema informa error ya que el número ingresado no corresponde a un reclamo

---

### <mark>ID: Cerrar reclamo</mark>
### TÍTULO: Como Jefe de área quiero cerrar un reclamo para marcarlo como finalizado
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Cierre exitoso con resolución positiva**    
**DADO** el número de reclamo 123 correspondiente a un reclamo registrado en el sistema y un resultado de resolución positiva 
**CUANDO** el jefe de área ingresa el número 123, fecha 10/10/2024, descripción desc1, resolución positiva y selecciona "cerrar reclamo"
**ENTONCES** el sistema registra el reclamo como finalizado y envía un correo al interesado con la novedad

**Escenario 2: Cierre exitoso con resolución negativa**  
**DADO** el número de reclamo 123 correspondiente a un reclamo registrado en el sistema y un resultado de resolución negativa
**CUANDO** el jefe de área ingresa el número 123, fecha 10/10/2024, descripción desc1, resolución negativa y selecciona "cerrar reclamo"
**ENTONCES** el sistema registra el reclamo como finalizado y envía un correo al interesado con la novedad adjuntando imagen de un acta completada por un inspector   

**Escenario 3: Cierre fallido por número inválido**  
**DADO** el número de reclamo 123 que no corresponde a un reclamo registrado en el sistema 
**CUANDO** el jefe de área ingresa el número 123, la fehca 10/10/2024, descripción desc1, resolución positiva y presiona "cerrar evento" 
**ENTONCES** el sistema informa error ya que el número ingresado no corresponde a un reclamo
