### <mark>ID: Realizar un pago </mark>
### TÍTULO: Como empleado/gerente quiero efectuar el pago de un cliente 
### REGLAS DE NEGOCIO: 
- si el primer vencimiento está vencido se le aplica un recargo al monto original de la factura
- si el segundo vencimiento está vencido la factura no se puede cobrar
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Pago exitoso**    
**DADO** que el código 123 es válido, las condiciones son adecuadas para recuperar datos de la central de cobros y no se encuentra vencido ningún vencimiento  
**CUANDO**  el empleado/gerente ingrese el código de pago 123 y presione "pagar"  
**ENTONCES** el sistema se redirige a la seccion de recuperar datos, espera respuesta y realiza el pago satisfactoriamente sin agregar recargo al monto original  

**Escenario 2: Pago exitoso con recargo**  
**DADO** que el código 321 corresponde a un pago registrado el sistema, las condiciones son adecuadas para recuperar datos de la central de cobros y se encuentra vencido el primer vencimiento  
**CUANDO**  el empleado/gerente ingrese el código de pago 321 y presione "pagar"  
**ENTONCES** el sistema se redirige a la seccion de recuperar datos, espera respuesta e informa que se encuentra vencido el primer vencimiento y realizará el pago satisfactoriamente agregando un recargo al monto original   

**Escenario 3: Pago fallido por segundo vencimiento vencido**  
**DADO** que el código de pago 432 es válido, las condiciones son adecuadas para recuperar datos de la central de cobros y se encuentra vencido el segundo vencimiento  
**CUANDO** el empleado/gerente ingrese el código de pago 432 y presione "pagar"  
**ENTONCES**  el sistema se redirige a la sección de recuperar datos, espera respuesta e informa que se encuentra vencido el segundo vencimiento por lo cual no se puede ralizar el pago  

**Escenario 4: Pago fallido error en la recuperación de datos**  
**DADO** las condiciones no son adecuadas para recuperar datos de la central de cobros  
**CUANDO** el empleado/gerente ingrese el código de pago 432 y presione "pagar"  
**ENTONCES**  el sistema informa que hubo un fallo en la recuperación de datos 

---

### <mark>ID:Recuperar datos</mark>
### TÍTULO: Como sistema quiero recuperar datos de una factura de la cetral de cobros 
### REGLAS DE NEGOCIO:

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Recuperación exitosa**    
**DADO** que la conexión con la central es exitosa, que el token 3 es válido y que el códgioo de pago 124 corresponde a una factura de la central  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y el código 124  
**ENTONCES**  la central envía los datos correspondientes al sistema 

**Escenario 2:Recuperación fallida por token inválido**  
**DADO** que la conexión con la central es exitosa, que el código de pago 124 corresponde a una factura de la central y que el token 3 es inválido  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y el código 124  
**ENTONCES** la central informa que el token es inválido  

**Escenario 3:Recuperación fallida por códgio de pago inválido**  
**DADO** que la conexión con la central es exitosa, que el token 3 es válido y que el códgioo de pago 124 no corresponde a una factura de la central  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y el código 124  
**ENTONCES** la central informa que el código ingresado no corresponde a ninguna factura 

**Escenario 4:Recuperación fallida por error en la conexión**  
**DADO** que no se puede establecer conexión con la central
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y el código 124  
**ENTONCES** la central informa que no se pudo establecer conexión

---

### <mark>ID:Registrar pago</mark>
### TÍTULO: Como gerente quiero registrar pagos y enviarlos a la cetral de cobros 
### REGLAS DE NEGOCIO:
- las transacciones no pueden enviarse 2 veces

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Registro exitoso**    
**DADO** que la clave maestra "clave" es válida, que el pago no se encuentra ya registrado en el sistema y que las condiciones son adecuadas para enviar datos de la central de cobros  
**CUANDO**  el gerente ingrese la clave maestra "clave" y presione "envíar"  
**ENTONCES** el sistema recupera las transacciones de los impuestos y servicios cobrados en el día y se redirige a la sección de enviar datos, espera respuesta e informa que la central de cobros recibió con exito la información de los pagos y registra las transacciones como enviadas  

**Escenario 2: Registro fallido por clave maestra inválida**  
**DADO** que la clave maestra "clave" es inválida, que el pago no se encuentra ya registrado en el sistema y que las condiciones son adecuadas para enviar datos de la central de cobros   
**CUANDO**  el gerente ingrese la clave maestra "clave" y presione "envíar"  
**ENTONCES** el sistema informa que la clave es incorrecta 

**Escenario 3: Registro fallido por pago ya registrado**  
**DADO** que la clave maestra "clave" es válida, que el pago se encuentra ya registrado en el sistema y que las condiciones son adecuadas para enviar datos de la central de cobros  
**CUANDO**  el gerente ingrese la clave maestra "clave" y presione "envíar"  
**ENTONCES** el sistema recupera las transacciones de los impuestos y servicios cobrados en el día e informa que los pagos ya se encuentran registrados en el sistema como enviados

**Escenario 4: Pago fallido error en el envío de datos**  
**DADO** las condiciones no son adecuadas para enviar datos a la central de cobros  
**CUANDO** el gerente ingrese la clave maestra "clave" y presione "enviar"  
**ENTONCES**  el sistema informa que hubo un fallo en la recuperación de datos  

---

### <mark>ID:Enviar datos</mark>
### TÍTULO: Como sistema quiero Enviar datos de pagos a la cetral de cobros 
### REGLAS DE NEGOCIO:

### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Envío exitoso**    
**DADO** que la conexión con la central es exitosa y que el token 3 es válido  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y la informacion de pagos  
**ENTONCES**  la central envía respuesta confirmando la recepción exitosa

**Escenario 2:Envío fallido por token inválido**  
**DADO** que la conexión con la central es exitosa y que el token 3 es inválido  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y la información de pagos 
**ENTONCES** la central informa que el token es inválido 

**Escenario 4:Envío fallido por error en la conexión**  
**DADO** que no se puede establecer conexión con la central  
**CUANDO** el sistema solicita conexión con la central, envía el token 3 y la información de pagos  
**ENTONCES** la central informa que no se pudo establecer conexión  

---

### <mark>ID:Ver estadísticas</mark>
### TÍTULO:Como gerente quiero ver las estadísticas de los impuestos y servicios cobrados 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1:Visualización exitosa**    
**DADO** que la clave maestra "clave" es correcta y que las fehcas desde 10/10/2024 hasta 12/10/2024 son válidas   
**CUANDO** el gerente ingrese la clave maestra "clave" y las fechas desde 10/10/2024 hasta 12/10/2024  
**ENTONCES** el sistema los montos y la cantidad de cobros realizados agrupados por empresa 

**Escenario 2:Visualización fallida por clave incorrecta**  
**DADO** que la clave maestra "clave" es incorrecta y que las fehcas desde 10/10/2024 hasta 12/10/2024 son válidas   
**CUANDO** el gerente ingrese la clave maestra "clave" y las fechas desde 10/10/2024 hasta 12/10/2024  
**ENTONCES** el sistema informa que la clave es incorrecta 

**Escenario 3:Visualización fallida por fechas inválidas**  
**DADO** que la clave maestra "clave" es correcta y que las fehcas desde 10/10/2024 hasta 09/09/2024 son inválidas   
**CUANDO** el gerente ingrese la clave maestra "clave" y las fechas desde 10/10/2024 hasta 09/09/2024  
**ENTONCES** el sistema informa que el rango de fechas ingresado es inválido  

**Escenario 4:Visualización fallida por falta de datos**  
**DADO** que la clave maestra "clave" es correcta y que las fehcas desde 10/10/2024 hasta --- son inválidas   
**CUANDO** el gerente ingrese la clave maestra "clave" y las fechas desde 10/10/2024 hasta ---  
**ENTONCES** el sistema informa que el rango de fechas ingresado es inválido ya que faltan ingresar datos


