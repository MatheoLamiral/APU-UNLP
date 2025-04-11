### <mark>ID: Iniciar sesión</mark>
### TÍTULO: Como personal de área comercial o gerente quiero iniciar sesíon en el sistema para realizar mis tareas
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Autenticación exitosa**    
**DADO** el dni 22456789 registrado en el sistema del banco central y la contraseña 123456 correspondiente al usuario y que la conexión con el sistema del banco central es estable
**CUANDO** el empleado del personal de área de comercial o el gerente ingresa el dni 22456789 y la contraseña 123456 y presiona "iniciar sesión"
**ENTONCES** el sistema envía los datos de usuario al sistema del banco central para que este los corrobore, espera respuesta, y recibe el token de autenticación válida del mismo    

**Escenario 2: Autenticación fallida por credenciales inválidas**  
**DADO** el dni 22456789 registrado en el sistema del banco central y la contraseña 123456 qie no corresponde al usuario y que la conexión con el banco central es estable
**CUANDO** el empleado del personal de área de comercial o el gerente ingresa el dni 22456789 y la contraseña 123456 y presiona "iniciar sesión"
**ENTONCES** el sistema envía los datos de usuario al sistema del banco central para que este los corrobore, espera respuesta, y recibe mensaje de credenciales inválidas del mismo, el cual se muestra al usuario    

**Escenario 3: Autenticación fallida por conexión fallida**  
**DADO** que no se puede establecer conexión con el sistema del banco central
**CUANDO** el empleado del personal de área de comercial o el gerente ingresa el dni 22456789 y la contraseña 123456 y presiona "iniciar sesión"
**ENTONCES** el sistema informa que no se puede establecer conexión con el sistema de autenticación 

---

### <mark>ID:Cerrar sesión</mark>
### TÍTULO:Como personal del área comercial o gerente quiero cerrar sesión para salir del sistema 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Cerrado de sesión exitoso**      
**DADO** el usuario 22456789 con una sesión iniciada  
**CUANDO** el usuario 22456789 selecciona "cerrar sesión"  
**ENTONCES**  el sistema cierra la sesión del usuario 

---

### <mark>ID: Alta de tarjeta</mark> ???????????????????
### TÍTULO: Como personal del área comercial quiero dar de alta una tarjeta para un cliente
### REGLAS DE NEGOCIO:
- la persona a la cual se le va a dar de alta la tarjeta debe ser cliente del banco
- no podra darse de alta una tarjeta si la persona es morosa en el sistema SIVA
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Alta exitosa**    
**DADO** el nombre pepe gonzales con dni 23456234 y CUIT 123445, cliente del banco, tipo de tarejeta gold y que se cumplen las condiciones para verificar la morosidad de una persona
**CUANDO** el empleado del personal del área comercial ingresa el nombre pepe gonzales, dni 23456234, cuit 123445, tipo de tarjeta gold y presiona "dar de alta"
**ENTONCES** el sistema se comunica con el SIVA pra verificar la morosidad de la persona, espera respuesta, y muestra en pantalla el número de tarjeta que recibió el SIVA

**Escenario 2: Alta fallida por persona no registrada como cliente**  
**DADO** el nombre pepe gonzales con dni 23456234 y CUIT 123445, no registrado como cliente del banco, tipo de tarejeta gold y que se cumplen las condiciones para verificar la morosidad
**CUANDO** el empleado del personal del área comercial ingresa el nombre pepe gonzales, dni 23456234, cuit 123445, tipo de tarjeta gold y presiona "dar de alta"
**ENTONCES** el sistema informa que el dni y CUIT ingresado no corresponden a un cliente del banco por lo que no se puede realizar la operación  

**Escenario 3: Alta fallida por persona morosa**  
**DADO** el nombre pepe gonzales con dni 23456234 y CUIT 123445, registrado como cliente del banco, tipo de tarejeta gold y que se cumplen las condiciones para verificar la morosidad
**CUANDO** el empleado del personal del área comercial ingresa el nombre pepe gonzales, dni 23456234, cuit 123445, tipo de tarjeta gold y presiona "dar de alta"
**ENTONCES** el sistema se comunica con el SIVA pra verificar la morosidad de la persona, espera respuesta, e informa que la persona está registrada como morosa en el sistema SIVA   

**Escenario 4: Alta fallida por conexión fallida con el SIVA**  
**DADO** que no se cumplen las condiciones para verificar la morosidad
**CUANDO** el empleado del personal del área comercial ingresa el nombre pepe gonzales, dni 23456234, cuit 123445, tipo de tarjeta gold y presiona "dar de alta"
**ENTONCES** el sistema informa que no se puede realizar la operación por conexión fallida con el SIVA

---

### <mark>ID: Verificar morosidad</mark> ????????????????????
### TÍTULO: Como sistema quiero verificar la morosidad de una persona a través del sistema SIVA para poder determinar si puede darse de alta a una tarjeta para el mismo
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:**    
**DADO**  
**CUANDO**  
**ENTONCES**    

**Escenario 2:**  
**DADO**  
**CUANDO**  
**ENTONCES**    

**Escenario N:**  
**DADO**  
**CUANDO**  
**ENTONCES**  

---

### <mark>ID: Baja tarjeta</mark>
### TÍTULO: Como personal del área comercial quiero dar de baja la tarjeta de un cliente para inhabilitarla
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Baja exitosa**    
**DADO** el número de tarjeta 1234556 registrado en el sistema
**CUANDO** el empleado del personal del área comercial ingresa le número de tarjeta 1234556 y presiona "dar de baja"
**ENTONCES** el sistema inhabilita la tarjeta y la elimina del sistema

**Escenario 2: Baja fallida por tarjeta inexistente**  
**DADO** el número de tarjeta 1234556 no registrado en el sistema
**CUANDO** el empleado del personal del área comercial ingresa le número de tarjeta 1234556 y presiona "dar de baja"
**ENTONCES** el sistema informa que la tarjeta ingresada no se encuentra registrada   

---

### <mark>ID: Pedir listado</mark>
### TÍTULO:Como gerente quiero ver el listado de las operaciones realizadas entre dos fechas para poder trabajar con ellas 
### REGLAS DE NEGOCIO:
- no se pueden ingresar fechas futuras al presente
- fecha de inicio no puede ser mayor a fecha de fin
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Visualización exitosa**    
**DADO** que las fehcas desde 10/10/2024 hasta 12/10/2024 son válidas   
**CUANDO** el gerente ingrese las fechas desde 10/10/2024 hasta 12/10/2024  
**ENTONCES** el sistema los montos y la cantidad de cobros realizados agrupados por empresa 

**Escenario 2:Visualización fallida por fechas futuras**  
**DADO** que las fehcas desde 10/12/2024 hasta 12/12/2024 son futuras  
**CUANDO** el gerente ingrese las fechas desde 10/10/2024 hasta 12/10/2024  
**ENTONCES** el sistema informa que las fehcas son inválidas ya que son fechas futuras 

**Escenario 3:Visualización fallida por fecha de inicio mayor a fecha de fin**  
**DADO** que la clave maestra "clave" es correcta y que las fehcas desde 10/10/2024 hasta 09/09/2024 son inválidas   
**CUANDO** el gerente ingreselas fechas desde 10/10/2024 hasta 09/09/2024  
**ENTONCES** el sistema informa que el rango de fechas ingresado es inválido ya que la fecha de inicio es mayor a la de fin

**Escenario 4:Visualización fallida por falta de datos**  
**DADO** que las fehcas desde 10/10/2024 hasta --- son inválidas   
**CUANDO** el gerente ingrese las fechas desde 10/10/2024 hasta ---  
**ENTONCES** el sistema informa que el rango de fechas ingresado es inválido ya que faltan ingresar datos

