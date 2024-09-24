### <mark>ID:Compra web</mark>
### TÍTULO: como cliente quiero comprar una entrada por la web para retirarla en el teatro
### REGLAS DE NEGOCIO:
- se deben cumplir las condiciones para realizar un pago
- tiene que haber lugares disponibles para realizar una compra
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: compra exitosa**    
**DADO** 2 entradas seleccionadas, 5 disponibles y que se cumplen las condiciones para realizar un pago  
**CUANDO** el cliente selecciona una de las funciones disponibles "funcion 1", ingresa el DNI 11122233, solicita 2 lugares y presiona el botón "pagar"  
**ENTONCES**el sistema se redirige a la sección de pago, espera respuesta, informa que el pago se realizó con éxito y le asigna al cliente un código para poder retirar las entradas en el teatro    

**Escenario 2: compra fallida por incumplir las condiciones para realizar un pago**  
**DADO** 2 entradas seleccionadas, 5 disponibles y que no se cumplen las condiciones para realizar un pago  
**CUANDO** el cliente selecciona una de las funciones disponibles "funcion 2", ingresa el DNI 11122233, solicita 2 lugares y presiona el botón "pagar"  
**ENTONCES**el sistema se redirige a la sección de pago, espera respuesta, informa que no se pudo finalizar la compra por un error en el pago  

**Escenario 3: compra fallida por pasar las entradas disponibles**  
**DADO** 2 entradas seleccionadas y 1 disponible y que se cumplen las condiciones para realizar un pago  
**CUANDO** el cliente selecciona una de las funciones disponibles "funcion3", ingresa el DNI 11122233, solicita 2 lugares y presiona el botón "pagar"  
**ENTONCES**el sistema informa que hubo un error, ya que no hay entradas disponibles suficientes para cumplir con la solicitud 

---

### <mark>ID Reserva personal:</mark>
### TÍTULO: como empleado quiero reservar una entrada para un cliente
### REGLAS DE NEGOCIO:
- no se pueden reservar más de 2 entradas 
- caducan 3 horas antes de la funcion 
- tiene que haber lugares disponibles para realizar una reserva  

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:reserva exitosa**    
**DADO** 2 entradas seleccionadas de una función disponible  
**CUANDO**  cuando el empleado ingresa el dni 345678 y nombre, juan, del cliente. Los datos de la obra, fecha 19/09/2023, 23:00hs, obra1 y presiona el botón "reservar"  
**ENTONCES** el sistema le registra que hay dos entradas asignadas al cliente con los datos ingresados e informa que la reserva se realizó con éxito  

**Escenario 2:reserva fallida por pasar el límite de entradas**  
**DADO** 3 entradas seleccionadas y que hay 10 entradas disponibles  
**CUANDO** el epmleado ingresa el dni 345678 y nombre, juan, del cliente. Los datos de la obra, fecha 19/09/2023, 23:00hs, obra1 y presiona el botón "reservar"  
**ENTONCES** el sistema informa que hubo un error ya que se pueden seleccionar como máximo 2 entradas  

**Escenario 3: compra fallida por pasar las entradas disponibles**  
**DADO** 2 entradas seleccionadas y 1 disponible 
**CUANDO** el epmleado ingresa el dni 345678 y nombre, juan, del cliente. Los datos de la obra, fecha 19/09/2023, 23:00hs, obra1 y presiona el botón "reservar"  
**ENTONCES**el sistema informa que hubo un error, ya que no hay entradas disponibles suficientes para cumplir con la solicitud  

---

### <mark>ID: compra personal</mark>
### TÍTULO: como vendedor quiero llevar a cabo la compra del cliente para venderle la/s entrada/s
### REGLAS DE NEGOCIO:
- se deben cumplir las condiciones para realizar un pago
- tiene que haber lugares disponiles para realizar una reserva  

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:compra exitosa**    
**DADO** que se cumplen las condiciones para realizar un pago, 2 entradas seleccionadas y 10 disponibles  
**CUANDO** el vendedor selecciona la funcion "funcion", ingresa el dni 234567 y el nombre diego, del cliente y selecciona el botón "pagar"  
**ENTONCES** el sistema se redirige a la sección de pago, espera respuesta, informa que la compra se realizó con éxito e imprime las entradas correspondientes   

**Escenario 2: compra fallida por incumplir las condiciones para realizar un pago**  
**DADO** 2 entradas seleccionadas, 3 disponibles y que no se cumplen las condiciones para realizar un pago  
**CUANDO** el cliente selecciona una de las funciones disponibles "funcion 2", ingresa el DNI 11122233, solicita 2 lugares y presiona el botón "pagar"  
**ENTONCES**el sistema se redirige a la sección de pago, espera respuesta, informa que no se pudo finalizar la compra por un error en el pago  

**Escenario 3: compra fallida por pasar las entradas disponibles**  
**DADO** 2 entradas seleccionadas y 1 disponible y que se cumplen las condiciones para realizar un pago  
**CUANDO** el cliente selecciona una de las funciones disponibles "funcion3", ingresa el DNI 11122233, solicita 2 lugares y presiona el botón "pagar"  
**ENTONCES**el sistema informa que hubo un error, ya que no hay entradas disponibles suficientes para cumplir con la solicitud  

---

### <mark>ID: Retirar entradas reservadas</mark>
### TÍTULO: como empleado quiero verificar si el cliente tiene reservas para darle o no la/s entrada/s
### REGLAS DE NEGOCIO:
- el cliente tiene que tener entradas reservadas
- las reservas que no se retiraron hasta 3 horas antes de la funcion caducan 

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:retiro exitoso**    
**DADO** que faltan 6 horas para la funcion y 2 entradas asignadas al cliente de dni 4662345 y nombre pepe  
**CUANDO** el empleado ingresa dni 4662345 y nombre pepe y presiona "pagar"  
**ENTONCES** el sistema se redirige a la seccion de pago, espera respuesta, informa que le pago se realizó con exito e imprime las entradas  

**Escenario 2:retiro fallido por entrada/s caducada/s**  
**DADO** que faltan 2 horas para la funcion y 2 entradas asignadas al cliente de dni 4662345 y nombre pepe  
**CUANDO** el empleado ingresa dni 4662345 y nombre pepe y presiona "pagar"  
**ENTONCES** el sistema informa que las entradas caducaron  

**Escenario N:retiro fallido por entradas inexistentes**  
**DADO** que faltan 4 horas para la funcion y ninguna entrada asignada al cliente   
**CUANDO** el empleado ingresa dni 4662345 y nombre pepe y presiona "pagar"  
**ENTONCES** el sistema informa que no hay entradas correspondientes al cliente con los datos ingresados  

---

### <mark>ID: Retirar entradas con código</mark>
### TÍTULO: como empleado quiero verificar si el código del cliente corresponde a entradas para darle o no la/s entrada/s
### REGLAS DE NEGOCIO:
- el código tiene que estar asociado a una compra  

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:retiro exitoso**    
**DADO** que el código 333 corresponde a 2 entradas  
**CUANDO** el empleado ingresa el código 333 y presiona el botón "imprimir"  
**ENTONCES** el sistema imprime las entradas  

**Escenario N:retiro fallido por entradas inexistentes**  
**DADO** que el código 444 no corresponde a ninguna entrada  
**CUANDO** el empleado ingresa el código 444 y presiona el botón "imprimir"  
**ENTONCES** el sistema informa que no hay entradas asignadas al código ingresado  

---

### <mark>ID:Programar salas</mark>
### TÍTULO: como empleado quiero ingresar una función para poder vender entradas
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
- tiene que haber horarios disponibles en la sala seleccionada  

**Escenario 1:ingreso exitoso**    
**DADO** los datos de una obra y que hay horario disponible de las 22:00hs en adelante en la sala 2  
**CUANDO** el elmpleado ingresa los datos de la obra selecciona la sala 2 y las 22:00hs como horario para la función y presiona el botón "ingresar"  
**ENTONCES** el sistema informa que se ingresó la función correctamente y la carga en el sistema  

**Escenario 2:ingreso fallido por sala ocupada**  
**DADO** los datos de una obra y que no hay horario disponible de las 22:00hs en adelante en la sala 2  
**CUANDO** el elmpleado ingresa los datos de la obra selecciona la sala 2 y las 22:00hs como horario para la función y presiona el botón "ingresar"  
**ENTONCES** el sistema informa que la sala se encuentra ocupada en el horario seleccionado  

---

### <mark>ID:Pago con tarjeta</mark>
### TÍTULO: como cliente quiero pagar con tarjeta para comprar entradas
### REGLAS DE NEGOCIO:
- la tarjeta debe estar autorizada por el banco
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Pago exitoso**    
**DADO** que la conexión con el servidor del banco es exitosa, el número de tarjeta 222555566, el código 654, y el nombre jose perez son válidos y tiene fondos  
**CUANDO** el cliente ingrese el número de tarjeta 222555566, ell código 654, y el vencimiento 06/2030 y presione “pagar”  
**ENTONCES** el sistema registrará el pago e indicará que el pago se realizó con éxito  

**Escenario 2:Pago fallido por tarjeta inexistente**  
**DADO** que la conexión con el servidor del banco es exitosa, el número 1234 no corresponde a una tarjeta de crédito válida 
**CUANDO** el cliente ingrese el número de tarjeta 1234, ell código 654, y el vencimiento 06/2030 y presione “pagar”  
**ENTONCES** el sistema indicará que el número ingresado no corresponde a una tarjeta de crédito válida

**Escenario 3:Pago fallido por fondos insuficientes**  
**DADO** que la conexión con el servidor del banco es exitosa, el número de tarjeta 222555566 corresponde a una tarjeta existente y no tiene fondos suficientes  
**CUANDO** el cliente ingrese el número de tarjeta 222555566, ell código 654, y el vencimiento 06/2030 y presione “pagar"  
**ENTONCES** el sistema indicará que la tarjeta ingresada no posee fondos suficientes para pagar  

**Escenario 4:Pago fallido por conexion fallida**  
**DADO** que nos se puede establecer conexión con el servidor del banco  
**CUANDO** el cliente ingrese el número de tarjeta 222555566, ell código 654, y el vencimiento 06/2030 y presione “pagar"  
**ENTONCES** el sistema indicará que hubo un error por conexión fallida  