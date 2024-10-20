### <mark>ID: Registrarse </mark>
### TÍTULO: Como docente quiero registrarme en el sistema para poder inscribirme a concursos
### REGLAS DE NEGOCIO:
- el mail debe ser único
- pueden concursar docentes cullo dni se encuentre entre 12 millones y 55 millones
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Registro exitoso**    
**DADO** el mail docente@gmail.com que no se encuentra registrado en el sistema y el dni 23.000.000  
**CUANDO** docente ingrese el dni 23.000.000, el nombre pepe, el apellido gonzales y el mail docente@gmail.com y presione "registrarse"   
**ENTONCES** el sistema registra al docente en el sistema y genera una contraseña correspondiente al mismo, la cual manda al mail ingresado

**Escenario 2: Registro fallido por mail repetido**  
**DADO** el mail docente@gmail.com que se encuentra registrado en el sistema y el dni 23.000.000  
**CUANDO** docente ingrese el dni 23.000.000, el nombre pepe, el apellido gonzales y el mail docente@gmail.com y presione "registrarse"   
**ENTONCES** el sistema informa que el mail ingresado corresponde a un docente ya registrado en el sistema 

**Escenario 3: Registro fallido por dni fuera de rango**  
**DADO** el mail docente@gmail.com que no se encuentra registrado en el sistema y el dni 10.000.000  
**CUANDO** docente ingrese el dni 23.000.000, el nombre pepe, el apellido gonzales y el mail docente@gmail.com y presione "registrarse"   
**ENTONCES** el sistema informa que el dni ingresado se encuentra fuera del rango permitido

---

### <mark>ID:Iniciar sesión</mark>
### TÍTULO: Como docente o jefe del áre de concursos quiero iniciar sesión para realizar mis acciones permitidas dentro del sistema
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Inicio de sesión exitoso**    
**DADO**  el usuario usuario@gmail.com registrado en el sistema y la contraseña 1234 correspondiente al usuario  
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 1234 y presiona "iniciar sesión"  
**ENTONCES** el sistema inicia la sesión de usuario e informa un mensaje de éxito     

**Escenario 2: Inicio de sesión fallido por usuario no registrado**  
**DADO**  el usuario usuario@gmail.com que no se encuentra registrado en el sistema   
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 1234 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que el usuario no se encuenra registrado en el sistema y se redirige a la pestaña de registro     

**Escenario 2: Inicio de sesión fallido por contraseña incorrecta**   
**DADO**  el usuario usuario@gmail.com registrado en el sistema y la contraseña 1234 que no corresponde al usuario  
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 1234 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que la contraseña es incorrecta y solicita que se vuelva a ingresar la misma  

---

### <mark>ID:Cerrar sesión</mark>
### TÍTULO:Como docente o jefe del área de concursos quiero cerrar sesión para salir del sistema 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Cerrado de sesión exitoso**      
**DADO** el usuario usuario@gmail.com con una sesión iniciada  
**CUANDO** el usuario usuario@gmail.com selecciona "cerrar sesión"  
**ENTONCES**  el sistema cierra la sesión del usuario  

---

### <mark>ID: Inscribirse a concurso</mark>
### TÍTULO: Como docente quiero inscribirme a un curso para poder participar
### REGLAS DE NEGOCIO:
- el docente no podrá inscribirse a más de 3 concursos  
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Inscripción exitosa**    
**DADO** el docente con usario docente@gmail.com con una sesión iniciada y 2 materias seleccionadas    
**CUANDO** el docente ingresa las dos materias seleccionadas    
**ENTONCES** el sistema imprime el comprobante y muestra un mensaje de éxito  

**Escenario 2: Inscripción fallida por exceder el limite de concursos seleccionados**  
**DADO** el docente con usario docente@gmail.com con una sesión iniciada y 4 materias seleccionadas    
**CUANDO** el docente ingresa las dos materias seleccionadas    
**ENTONCES** el sistema infroma que no se pudo realizar la inscripción ya que se excedió el límite de concursos    

---

### <mark>ID:Imprimir listado</mark>
### TÍTULO: Como jefe del área de concursos quiero imprimir un listado con los inscriptos a una materia determinada para enviar dicho listado al secretario administrativo
### REGLAS DE NEGOCIO:  
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Impresión exitosa**    
**DADO** el jefe del área de concursos con una sesión iniciada con el usuario usuario@gmail.com y un listado con información determinado  
**CUANDO** el jefe del área de concursos selecciona el listado y presiona "imprimir"  
**ENTONCES** el sistema muestra un mensaje de éxito e imprime el listado  

**Escenario 2:Impresión fallida por lista vacía**  
**DADO** el jefe del área de concursos con una sesión iniciada con el usuario usuario@gmail.com y un listado vacío  
**CUANDO** el jefe del área de concursos selecciona el listado y presiona "imprimir"  
**ENTONCES** el sistema informa que el listado está vacio y no se puede imprimir      







