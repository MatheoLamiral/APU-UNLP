### <mark>ID: Registrarse </mark>
### TÍTULO: Como persona quiero registrarme en el sistema para poder publicar o postularme a viajes
### REGLAS DE NEGOCIO:
- el mail debe ser único
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Registro exitoso**    
**DADO** el mail persona@gmail.com que no se encuentra registrado en el sistema  
**CUANDO** la persona ingrese el nombre de usuario pepe05, constraseña 123 y el mail persona@gmail.com y presione "registrarse"   
**ENTONCES** el sistema registra a la persona en el sistema e informa que la operación se ralizó con éxito 

**Escenario 2: Registro fallido por mail repetido**  
**DADO** el mail persona@gmail.com que se encuentra registrado en el sistema  
**CUANDO** la persona ingrese el nombre de usuario pepe05, constraseña 123 y el mail persona@gmail.com y presione "registrarse"    
**ENTONCES** el sistema informa que el mail ingresado corresponde a un usuario ya registrado en el sistema 


---

### <mark>ID:Iniciar sesión</mark>
### TÍTULO: Como usuario quiero iniciar sesión para poder publicar o postularme a un viaje
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Inicio de sesión exitoso**    
**DADO**  el usuario usuario1 registrado en el sistema y la contraseña 1234 correspondiente al usuario  
**CUANDO**  el usuario ingresa el usuario usuario1 y la contraseña 1234 y presiona "iniciar sesión"  
**ENTONCES** el sistema inicia la sesión de usuario e informa un mensaje de éxito     

**Escenario 2: Inicio de sesión fallido por usuario no registrado**  
**DADO**  el usuario usuario1 que no se encuentra registrado en el sistema   
**CUANDO**  el usuario ingresa el usuario usuario1 y la contraseña 1234 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que el usuario no se encuenra registrado en el sistema y se redirige a la pestaña de registro     

**Escenario 2: Inicio de sesión fallido por contraseña incorrecta**   
**DADO**  el usuario usuario1 registrado en el sistema y la contraseña 1234 que no corresponde al usuario  
**CUANDO**  el usuario ingresa el usuario usuario1 y la contraseña 1234 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que la contraseña es incorrecta y solicita que se vuelva a ingresar la misma  

---

### <mark>ID:Cerrar sesión</mark>
### TÍTULO:Como usuario quiero cerrar sesión para salir del sistema 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Cerrado de sesión exitoso**      
**DADO** el usuario usuario1 con una sesión iniciada  
**CUANDO** el usuario usuario1 selecciona "cerrar sesión"  
**ENTONCES**  el sistema cierra la sesión del usuario  

---

### <mark>ID:Publicar viaje</mark>
### TÍTULO: Como usuario quiero publicar un viaje para poder ser chofer del mismo
### REGLAS DE NEGOCIO:
- los diferentes viajes de un usuario no deben superponerse
- un usuario que adeuda calificaciones no puede publicar un viaje 
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Publicación  exitosa**    
**DADO** el usuario usuario1 con una sesión iniciada, que no adeuda calificaciones, y que el viaje se que desea publicar con horario 22:00 hs y fecha 10/10/2024 no se suerpone con nigún otro del usuario  
**CUANDO** el usuario ingrerse hprario 22:00hs, fecha 10/10/2024, automóvil gol power y presione "publicar"  
**ENTONCES** el sistema publicará el viaje del usuario e informará que la operación se realizó con éxito  

**Escenario 2: Publicación fallida por superposición de viajes**  
**DADO** el usuario usuario1 con una sesión iniciada, que no adeuda calificaciones, y que el viaje se que desea publicar con horario 22:00 hs y fecha 10/10/2024 se suerpone con otro vijae del usuario  
**CUANDO** el usuario ingrerse hprario 22:00hs, fecha 10/10/2024, automóvil gol power y presione "publicar"  
**ENTONCES** el sistema infromará que no se pudo publicar el viaje debido a que se superpone con otro viaje ya publicado    

**Escenario 2: Publicación fallida por adeudar calificaciones**  
**DADO** el usuario usuario1 con una sesión iniciada, que adeuda 3 calificaciones, y que el viaje se que desea publicar con horario 22:00 hs y fecha 10/10/2024 no se suerpone con ningún otro del usuario  
**CUANDO** el usuario ingrerse hprario 22:00hs, fecha 10/10/2024, automóvil gol power y presione "publicar"  
**ENTONCES** el sistema infromará que no se pudo publicar el viaje debido a que se adeudan 3 calificaciones  

---

### <mark>ID:Postularse a viaje</mark>
### TÍTULO: Como usuario quiero postularme a un viaje para poder ser copiloto del mismo
### REGLAS DE NEGOCIO:

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Postulación  exitosa**      
**DADO** el usuario usuario1 con una sesión iniciada y un viaje con cupos disponible para seleccionar  
**CUANDO** el usuario seleccione el viaje y presione "postularse"  
**ENTONCES** el sistema añadirá al usuario en la lista de postulados del viaje seleccionado e informara que se realizó a operación con éxito  

**Escenario 2: Publicación fallida por cupos agotados**  
**DADO** el usuario usuario1 con una sesión iniciada y un viaje sin cupos disponible   
**CUANDO** el usuario seleccione el viaje y presione "postularse"  
**ENTONCES** el sistema informará que no se pudo postular al viaje debido a que no hay cupos disponibles  

---

### <mark>ID: Aceptar copiloto</mark>
### TÍTULO: Cómo chofer quiero aceptar a uno o varios candidatos para copiloto de mi viaje
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Aceptación exitosa**    
**DADO** el chofer con usuario usuario1 con una sesión iniciada y 3 candidatos a copiloto en la lista  
**CUANDO** el chofer con usuario usuario1 ingrese a la lista de candidatos, seleccione al candidato 2 y presione "aceptar"  
**ENTONCES**  el sistema elimina al candidato de la lista de candidatos, lo agrega a la lista de copilotos y muestra un mensaje de exito en la operación  

**Escenario 2:Aceptación fallida por lista de candidatos vacía**  
**DADO** el chofer con usuario usuario1 con una sesión iniciada y 0 candidatos a copiloto en la lista  
**CUANDO** el chofer con usuario usuario1 ingrese a la lista de candidatos   
**ENTONCES**  el sistema informara que no hay candidatos registrados  

---

### <mark>ID: Rechazar copiloto</mark>
### TÍTULO: Cómo chofer quiero rechazar a uno o varios candidatos para copiloto de mi viaje
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Aceptación exitosa**    
**DADO** el chofer con usuario usuario1 con una sesión iniciada y 3 candidatos a copiloto en la lista  
**CUANDO** el chofer con usuario usuario1 ingrese a la lista de candidatos, seleccione al candidato 2 y presione "rechazar"  
**ENTONCES**  el sistema elimina al candidato de la lista de candidatos y muestra un mensaje de exito en la operación  

**Escenario 2:Aceptación fallida por lista de candidatos vacía**  
**DADO** el chofer con usuario usuario1 con una sesión iniciada y 0 candidatos a copiloto en la lista  
**CUANDO** el chofer con usuario usuario1 ingrese a la lista de candidatos   
**ENTONCES**  el sistema informara que no hay candidatos registrados  

---

### <mark>ID: Calificar usuario </mark>
### TÍTULO: como piloto o copiloto quiero calificar al/los copilotos o el chofer de mi viaje
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:

**Escenario 1: Calificacion exitosa**    
**DADO** el usuario usuario1 con una sesión iniciada y 3 calificaciones pendientes  
**CUANDO** Cuando el usuario1 ingresa a la lista de calificaciones pendientes, selecciona el usuario3 para calificar, ingresa su calificacion positiva/negativa y presiona calificar    
**ENTONCES**  el sistema le suma/resta un punto al usuario correspondiente, lo elimina de la lista de calificaciones pendientes e informa que la operación se realizó con éxito    

**Escenario 1: Calificacion fallida por lista vacía**      
**DADO** el usuario usuario1 con una sesión iniciada y 0 calificaciones pendientes    
**CUANDO** Cuando el usuario1 ingresa a la lista de calificaciones pendientes  
**ENTONCES**  el sistema informa que no hay calificaciones pendientes  
 





