### <mark>ID:Acceder a catálogo</mark>
### TÍTULO: Como visitante o usuario registrado  quiero acceder al catálogo de libros y navegar en él
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Acceso exitoso**    
**DADO** un catálogo con 16 libros    
**CUANDO** el usuario registrado o el visitante seleccione "ver catálogo"  
**ENTONCES** el sistema se redirige al catálogo y muestra un mensaje de éxito    

**Escenario 2: Acceso fallido por catálogo fallido**  
**DADO** un catálogo sin libros  
**CUANDO** el usuario registrado o el visitante selecciona "ver catálogo"  
**ENTONCES** el sistema informa que no se puede abrir el catálogo ya que el mismo se encuentra vacío   

---

### <mark>ID: Registrarse (alta parcial)</mark>
### TÍTULO: Como visitante quiero darme de alta parcialmente en el sistema para poder comprar libros
### REGLAS DE NEGOCIO:
- la clave debe tener 6 caracteres
- el correo no puede repetirse
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Alta parcial exitoso**    
**DADO** el correo user@gmail.com que no se encuentra registrado en el sistema y la clave 123456
**CUANDO** el visitante ingresa el nombre pepe, apellido gonzales, dni 46789462, el correo user@gmail.com, la clave 123456 y presiona "registrarse"
**ENTONCES** el sistema genera un código de 16 dígitos, lo envía por correo al usuario

**Escenario 2: Alta parcial fallido por clave inválida**  
**DADO** el correo user@gmail.com que no se encuentra registrado en el sistema y la clave 123
**CUANDO** el visitante ingresa el nombre pepe, apellido gonzales, dni 46789462, el correo user@gmail.com, la clave 123 y presiona "registrarse"
**ENTONCES** el sistema informa que la clave no cumple con las restricciones y pide que se vuelva a ingresar la misma   

**Escenario 3: Alta parcial fallido por correo repetido**  
**DADO** el correo user@gmail.com que se encuentra registrado en el sistema y la clave 123456
**CUANDO** el visitante ingresa el nombre pepe, apellido gonzales, dni 46789462, el correo user@gmail.com, la clave 123456 y presiona "registrarse"
**ENTONCES** el sistema informa que el usuario correspondiente al correo ingresado ya se encuentra registrado 

---

### <mark>ID: Registrarse (confirmación)</mark>
### TÍTULO: como visitante quiero confirmar mi registro en el sistema para poder comprar libros
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Confirmación exitosa**    
**DADO** el código 1234567890987654 (16 dígitos) correspondiente a un alta parcial previa
**CUANDO** el visitante ingresa el código 1234567890987654 y presiona "confirmar"  
**ENTONCES** el sistema registra definitivamente al usuario en el sistema

**Escenario 2: Confirmación fallida por código inválido**  
**DADO** el código 1234 que no corresponde a ningún alta parcial previa
**CUANDO** el visitante ingresa el código 1234 y presiona "confirmar"  
**ENTONCES** el sistema informa que el códgio ingresado no corresponde a ninguna alta parcial previa

---

### <mark>ID:Iniciar sesión</mark>
### TÍTULO: Como usuario registrado quiero iniciar sesión para poder entrar en el sistema
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Inicio de sesión exitoso**    
**DADO** el usuario usuario@gmail.com registrado en el sistema y la contraseña 123456 correspondiente al usuario  
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 123456 y presiona "iniciar sesión"  
**ENTONCES** el sistema inicia la sesión de usuario e informa un mensaje de éxito     

**Escenario 2: Inicio de sesión fallido por usuario no registrado**  
**DADO**  el usuario usuario@gmail.com que no se encuentra registrado en el sistema   
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 123456 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que el usuario no se encuenra registrado en el sistema y se redirige a la pestaña de registro     

**Escenario 2: Inicio de sesión fallido por contraseña incorrecta**   
**DADO**  el usuario usuario@gmail.com registrado en el sistema y la contraseña 1234 que no corresponde al usuario  
**CUANDO**  el usuario ingresa el usuario usuario@gmail.com y la contraseña 1234 y presiona "iniciar sesión"    
**ENTONCES** el sistema informa que la contraseña es incorrecta y solicita que se vuelva a ingresar la misma  

---

### <mark>ID:Cerrar sesión</mark>
### TÍTULO:Como usuario registrado quiero cerrar sesión para salir del sistema 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Cerrado de sesión exitoso**      
**DADO** el usuario usuario@gmail.com con una sesión iniciada  
**CUANDO** el usuario usuario@gmail.com selecciona "cerrar sesión"  
**ENTONCES**  el sistema cierra la sesión del usuario

---

### <mark>ID: Realizar compra</mark>
### TÍTULO: Como usuario registrado
### REGLAS DE NEGOCIO:
- solo los usuarios registrados pueden realizar compras
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Compra exitosa**    
**DADO** el usuario user@gmail.com registrado en el sistema, el libro de ISBN 1234 y que se cumplen las condiciones para realizar un pago
**CUANDO** el usuario ingresa el ISBN 1234 y selecciona "comprar"
**ENTONCES** el sistema muestra la portada y la descripción del libro, se redirige a la seccion de pago con tarjeta, espera respuesta, y genera un enlace de descarga y se lo envía por correo al usuario

**Escenario 2: Compra fallida por pago fallido**  
**DADO** el usuario user@gmail.com registrado en el sistema, el libro de ISBN 1234 y que no se cumplen las condiciones para realizar un pago
**CUANDO** el usuario ingresa el ISBN 1234 y selecciona "comprar"
**ENTONCES** el sistema muestra la portada y la descripción del libro, se redirige a la seccion de pago con tarjeta, espera respuesta, e informa que no se pudo realizar la compra por un fallo en el pago

**Escenario 3: Compra fallida por ISBN inexistente**  
**DADO** el usuario user@gmail.com registrado en el sistema, el ISBN 1234 que no corresponde a ningún libro y que se cumplen las condiciones para realizar un pago
**CUANDO** el usuario ingresa el ISBN 1234 y selecciona "comprar"
**ENTONCES** el sistema informa que el ISBN ingresado no corresponde a ningún libro 

---

### <mark>ID: Pagar con tarjeta </mark>
### TÍTULO: Como usuario registrado quiero pagar con tarjeta para comprar un libro
### REGLAS DE NEGOCIO:
- solo el titular de la tarjeta puede realizar la compra
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Pago exitoso**    
**DADO** el usuario pepe@gmail.com resgistrado con nombre pepe y apellido gonzales, el número de tarjeta 123456789 válido y el titular de la tarjeta pepe gonzales, que la conexión con el banco es exitosa y la tarjeta tiene fondos suficientes  
**CUANDO** el usuario pepe@gmail.com ingrese el numero 123456789 y el titular pepe gonzales y seleccione "pagar"  
**ENTONCES** el sistema envía los datos al servidor para realizar el cobro y registra la compra  

**Escenario 2: Pago fallido por titular incorrecto**  
**DADO** el usuario gonzalo@gmail.com resgistrado con nombre gonzalo y apellido ramires, el número de tarjeta 123456789 válido y el titular de la tarjeta pepe gonzales, que la conexión con el banco es exitosa y la tarjeta tiene fondos suficientes  
**CUANDO** el usuario pepe@gmail.com ingrese el numero 123456789 y el titular pepe gonzales y seleccione "pagar"  
**ENTONCES** el sistema informa que el nombre y apellido del usuario no coinciden con los del titular de la tarjeta por lo que no se puede realizar la compra  

**Escenario 3: Pago fallido por fondos insuficientes**  
**DADO** el usuario pepe@gmail.com resgistrado con nombre pepe y apellido gonzales, el número de tarjeta 123456789 válido y el titular de la tarjeta pepe gonzales, que la conexión con el banco es exitosa y la tarjeta no tiene fondos suficientes  
**CUANDO** el usuario pepe@gmail.com ingrese el numero 123456789 y el titular pepe gonzales y seleccione "pagar"  
**ENTONCES** el sistema informa que la tarjeta no posee fondos suficientes para realizar la compra  

**Escenario 1: Pago fallido por error en la conexión**    
**DADO** que no se puede establecer conexión con el banco   
**CUANDO** el usuario pepe@gmail.com ingrese el numero 123456789 y el titular pepe gonzales y seleccione "pagar"  
**ENTONCES** el sistema informa que no se puede realizar la compra debido a un error en la concexión 





