### <mark>ID: Iniciar trámite</mark>
### TÍTULO: Como cliente quiero iniciar un trámite para pedir un crédito
### REGLAS DE NEGOCIO:
- el dni ingresado debe corresponder a un cliente del banco  
- el crédito solicitado no debe superar los $400.000  
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Inicio exitoso**    
**DADO** el dni 12234567 correspondiente a un cliente del banco, y un monto solicitado de $100.000   
**CUANDO** el cliente ingresa el dni 122345677, nombre pepe, apellido gonzales, mail pepe@gmail.com, tipo de crédito personal, monto $100.000 y selecciona "solicitar"  
**ENTONCES** el sistema almacena el trámite e imprime un número de comprobante para el cliente  

**Escenario 2: Inicio fallido por dni no asociado a un cliente**  
**DADO** el dni 12234567 que no correspone a un cliente del banco, y un monto solicitado de $100.000  
**CUANDO** el cliente ingresa el dni 122345677, nombre pepe, apellido gonzales, mail pepe@gmail.com, tipo de crédito personal, monto $100.000 y selecciona "solicitar"  
**ENTONCES** el sistema informa que no se pudo realizar la transacción y envía al mail del usuario un instructivo para hacerse cliente del banco    

**Escenario 3: Inicio fallido por sobrepasar el monto máximo**  
**DADO** el dni 12234567 correspondiente a un cliente del banco, y un monto solicitado de $600.000    
**CUANDO** el cliente ingresa el dni 122345677, nombre pepe, apellido gonzales, mail pepe@gmail.com, tipo de crédito personal, monto $600.000 y selecciona "solicitar"    
**ENTONCES** el sistema rechaza el inicio del trámite y muestra el mensaje "El monto solicitado excede el límite permitido"  

---

### <mark>ID: Consultar estado de trámite</mark>
### TÍTULO: Como cliente quiero consultar el estado de mi trámite para ver la situación del mismo 
### REGLAS DE NEGOCIO:
- 3 intentos para ingresar número de comprobante válido  
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Consulta exitosa**    
**DADO** el número de comprobante 123 correspondiente a un trámite y 0 intentos registrados en el día  
**CUANDO** el cliente ingresa el número de comprobante 123 y presiona "consultar"  
**ENTONCES** el sistema retorna un informe con el estado del trámite  

**Escenario 2: Consulta fallida por número de comprobante inválido**  
**DADO** el número de comprobante 123 que no correspone a un trámite y 2 intentos registrados en el día  
**CUANDO** el cliente ingresa el número de comprobante 123 y presiona "consultar"  
**ENTONCES** el sistema muestra el mensaje "trámite inexistente" y suma uno a la cantidad de intentos del día, si la cantidad de intentos llega a 3 bloquea la ip del usuario   

**Escenario 3: Consulta fallida por superar el máximo de intentos permitidos del día**  
**DADO** el número de comprobante 123 correspondiente a un trámite y 3 intentos realizados en el día    
**CUANDO** el cliente ingresa el número de comprobante 123 y presiona "consultar"   
**ENTONCES** el sistema informa que se supero el límite de intentos, y se puede volver a intentar dentro de 24 hs  

---

### <mark>ID:Pedir listado de créditos</mark>
### TÍTULO: Como gerente del banco quiero pedir un listado de créditos aprobados entre fechas para poder realizar mis tareas con ella
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Pedido exitoso**    
**DADO** las fechas 10/09/2024, 10/10/2024 y 56 créditos aprobados entre ellas   
**CUANDO** el gerente ingresa las fechas 10/09/2024, 10/10/2024 y presiona "generar listado"  
**ENTONCES** el sistema muestra un listado con los créditos aprobados  

**Escenario 2: Pedido fallido por fechas inválidas**  
**DADO** las fechas 10/11/2024, 10/10/2024  
**CUANDO** el gerente ingresa las fechas 10/11/2024, 10/10/2024 y presiona "generar listado"    
**ENTONCES** el sistema informa que las fechas ingresadas son inválidas     

**Escenario 3: Pedido fallido por perido de fechas sin créditos**  
**DADO** las fechas 10/09/2024, 10/10/2024 y 0 créditos aprobados entre ellas  
**CUANDO** el gerente ingresa las fechas 10/09/2024, 10/10/2024 y presiona "generar listado"  
**ENTONCES** el sistema informa que no hay créditos aprobados en las fechas ingresadas  




