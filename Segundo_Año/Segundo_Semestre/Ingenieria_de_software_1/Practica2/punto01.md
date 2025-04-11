### <mark>ID: dar de alta mobiliario</mark>
### TÍTULO:  Como encargado quiero dar de alta un mobiliario para poder venderlo  
### REGLAS DE NEGOCIO: 
- no pueden existir códigos de inventario repetidos    

### CRITERIOS DE ACEPTACIÓN: :  

**Escenario 1: Alta exitosa**  
**DADO** un código de inventario 537 que no existe en el sistema  
**CUANDO** el encargado ingresa  código de inventario 537, tipo de mueble 1, fecha de creación 12/12/2017, fecha de último mantenimiento 13/05/2024, estado libre y precio de alquiler USD $500. Y selecciona la opción “dar de alta”  
**ENTONCES** el sistema informa que el mueble se encuentra dado de alta correctamente 

**Escenario 2: Alta fallida por falta de datos**  
**DADO** los datos del mueble: código de inventario 598, tipo de mueble 2, fecha de creación 12/12/2018, fecha de último mantenimiento 13/06/2024, estado libre  
**CUANDO** el encargado ingresa los datos y selecciona la opción “dar de alta”  
**ENTONCES** el sistema informará que no se ingresaron todos los datos necesarios para dar de alta al mueble y marcará la casilla para ingresar el precio del mueble con un asterisco indicando que falta ingresar ese dato 

**Escenario 3: Alta fallida por código de inventario repetido**  
**DADO** el código de inventario 598 que ya existe en el sistema, tipo de mueble 2, fecha de creación 12/12/2018, fecha de último mantenimiento 13/06/2024, estado libre  
**CUANDO** el encargado ingresa código de inventario 598, tipo de mueble 2, fecha de creación 12/12/2018, fecha de último mantenimiento 13/06/2024, estado libre y selecciona la opción “dar de alta”  
**ENTONCES** el sistema informará que no se pudo dar de alta ya que el código de inventario ingresado ya está registrado  

---  

### <mark>ID: Iniciar sesión</mark> 
### TÍTULO:  Como encargado quiero iniciar sesión para dar de alta un mobiliario 
### REGLAS DE NEGOCIO: 



### CRITERIOS DE ACEPTACIÓN: :
**Escenario 1: Inicio de sesión exitoso**  
**DADO**  el usuario encargado_1 y la contraseña 1234 correspondientes a un usuario existente en el sistema   
**CUANDO** el encargado ingrese usuario encargado_1 y la contraseña 1234 y presione “iniciar sesión”  
**ENTONCES** el sistema se redirigirá a la pantalla principal e informará que se inició sesión correctamente 
 
**Escenario 2: Inicio de sesión fallido por información incorrecta**  
**DADO** el usuario encargado_3 y la contraseña 1342 que no corresponden a un usuario existente en el sistema  
**CUANDO** el encargado ingrese  usuario encargado_3 y la contraseña 1342 y presione “iniciar sesión”  
**ENTONCES** el sistema informará que hubo un error al ingresar el usuario o la contraseña  

---  

### <mark>ID: Cerrar sesión</mark> 
### TÍTULO:  Como encargado quiero cerrar sesión para poder salir de mi cuenta
### REGLAS DE NEGOCIO:    

### CRITERIOS DE ACEPTACIÓN: :

**Escenario 1: Cerrar sesión con éxito**  
**DADO**  una sesión abierta en el sistema con el usuario encargado_1 y la contraseña 1234  
**CUANDO** el encargado presione “cerrar sesión”  
**ENTONCES** el sistema se redirigirá a la pantalla de inicio de sesión e informará que se cerró la sesión correctamente  

---  

### <mark>ID: reserva de alquileres</mark>
### TÍTULO:  Como cliente quiero realizar una reserva del alquiler para alquilar el mueble
### REGLAS DE NEGOCIO: 
- incluir como mínimo 3 muebles  
- se debe abonar el 20% del total del alquiler



### CRITERIOS DE ACEPTACIÓN: :
**Escenario 1: Reserva realizada con éxito**  
**DADO** el 20% del total del alquiler, y 10 unidades del  mobiliario de nombre 1  
**CUANDO** el cliente ingrese la información:10 unidades del  mobiliario de nombre 1, fecha 12/10/2022, lugar La Plata, 15 días de duración del alquiler e ingrese el 20% del total como seña. Y luego seleccione “reserva de alquiler”  
**ENTONCES** el sistema se redirigirá a la sección de “pagar” y en caso de que se cumplan los requisitos para realizar un pago informará que se realizó con éxito la reserva del alquiler  

**Escenario 2: Reserva fallida por incluir menos de 3 muebles**  
**DADO** el 50% del total del alquiler y 2 unidades del  mobiliario de nombre 2  
**CUANDO** el cliente ingrese la información: 2 unidades del  mobiliario de nombre 2, fecha 12/9/2022, lugar La Plata, 3 días de duración del alquiler e ingrese el 50% del total como seña. Y luego seleccione “reserva de alquiler”  
**ENTONCES** el sistema informará que no se pudo realizar la reserva ya que no se cumple con la cantidad mínima de muebles requeridos para realizar el alquiler  

**Escenario 4: Reserva fallida por no pagar el 20%**  
**DADO** el 15% del total del alquiler, 5 unidades del  mobiliario de nombre 4, fecha 07/9/2022, lugar La Plata y 5 días de duración del alquiler  
**CUANDO** el cliente ingrese la información: 5 unidades del  mobiliario de nombre 4, fecha 07/9/2022, lugar La Plata, 5 días de duración del alquiler e ingrese el 15% del total como seña . Y luego seleccione “reserva de alquiler”   
**ENTONCES** el sistema informará que no se pudo realizar la reserva ya que el monto ingresado es menor al 20% del total del alquiler y pedirá que modifique el monto  

---  

### <mark>ID: Pago con tarjeta</mark>
### TÍTULO:  Como cliente quiero pagar con tarjeta de crédito para reservar un alquiler
### REGLAS DE NEGOCIO: 
- número de tarjeta y fondos deben ser válidos

### CRITERIOS DE ACEPTACIÓN: :
**Escenario 1: Pago exitoso**  
**DADO**  que la conexión con el servidor del banco es exitosa, el número de tarjeta 222555566 es válido y tiene fondos  
**CUANDO** el cliente ingrese el número de tarjeta 222555566 y presione “pagar”  
**ENTONCES** el sistema registrará el pago e indicará que el pago se realizó con éxito  

**Escenario 2: Pago fallido por tarjeta inexistente**  
**DADO** que la conexión con el servidor del banco es exitosa, el número 1234 no corresponde a una tarjeta de crédito válida  
**CUANDO** el cliente ingrese el número de tarjeta 1234 y presione “pagar”  
**ENTONCES** el sistema indicará que el número ingresado no corresponde a una tarjeta de crédito válida  

**Escenario 3: Pago fallido por fondos insuficientes**  
**DADO** que la conexión con el servidor del banco es exitosa, el número de tarjeta 222555599 es válido y no tiene fondos suficientes  
**CUANDO** el cliente ingrese el número de tarjeta 222555599 y presione “pagar”  
**ENTONCES** el sistema indicará que la tarjeta ingresada no posee fondos suficientes para pagar  

**Escenario 4: Pago fallido por conexión fallida**  
**DADO** que nos se puede establecer conexión con el servidor del banco  
**CUANDO** el cliente ingrese un número de tarjeta  y presione “pagar”  
**ENTONCES** el sistema indicará que hubo un error por conexión fallida  
