### <mark>ID: Reservar hospedaje</mark>
### TÍTULO: Como usuario quiero reservar un hospedaje para poder dsifrutar del mismo
### REGLAS DE NEGOCIO:
- la fecha de ingreso debe estar dentro de los 90 días a partir de la fecha de la reserva
- las estadías no pueden durar más de 15 días
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Reserva exitosa**    
**DADO**  la fecha de ingreso 10/10/2024 que se encuentra dentro de los 90 días a partir de la fehca de la reserva, la fehca de egreso 14/10/2024, 10 peronas como cantidad ingresada, y que el hotel hotel1 posee disponibilidad en esas fechas para esa cantidad de personas  
**CUANDO**  el usuario ingrese fecha de ingreso 10/10/2024, fehca de egreso 14/10/2024, 10 peronas, hotel hotel1 y presione "reservar"  
**ENTONCES** el sistema genera un código de reserva asociado al usuario y se lo envía por mail junto con un enlace para continuar con el proceso de pago  

**Escenario 2: Reserva faliida por fecha de ingreso posterior a los 90 días de la reserva**  
**DADO**  la fecha de ingreso 10/10/2024 que se encuentra fuera de los 90 días a partir de la fehca de la reserva, la fehca de egreso 14/10/2024, 10 peronas como cantidad ingresada, y que el hotel hotel1 posee disponibilidad en esas fechas para esa cantidad de personas   
**CUANDO**  el usuario ingrese fecha de ingreso 10/10/2024, fehca de egreso 14/10/2024, 10 peronas, hotel hotel1 y presione "reservar"  
**ENTONCES** el sistema informará que no se pudo realizar la reserva ya que la fecha de ingreso no debe superar los 90 días posteriores  a la reserva del hospedaje   

**Escenario 4: Reserva faliida por estadía superior a 15 días**  
**DADO**  la fecha de ingreso 10/10/2024 que se encuentra dentro de los 90 días a partir de la fehca de la reserva, la fehca de egreso 10/11/2024, 10 peronas como cantidad ingresada, y que el hotel hotel1 posee disponibilidad en esas fechas para esa cantidad de personas   
**CUANDO**  el usuario ingrese fecha de ingreso 10/10/2024, fehca de egreso 14/10/2024, 10 peronas, hotel hotel1 y presione "reservar"  
**ENTONCES** el sistema informa que no se pudo realizar la reserva ya que la cantidad máxima de días que puede durar el hospedaje es de 15 días  

**Escenario 4: Reserva faliida por hotel sin disponibilidad**  
**DADO**  la fecha de ingreso 10/10/2024 que se encuentra dentro de los 90 días a partir de la fehca de la reserva, la fehca de egreso 14/10/2024, 10 peronas como cantidad ingresada, y que el hotel hotel1 no posee disponibilidad en esas fechas para esa cantidad de personas   
**CUANDO**  el usuario ingrese fecha de ingreso 10/10/2024, fehca de egreso 14/10/2024, 10 peronas, hotel hotel1 y presione "reservar"  
**ENTONCES** el sistema informa que el hotel seleccionado no posee disponibilidad para la cantidad de personas ingresadas en las fechas ingresadas  

---

### <mark>ID: Check in</mark>
### TÍTULO: Como usuario quiero realizar el check in en el sistema para poder comenzar mi estadía
### REGLAS DE NEGOCIO:
- el check in debe realizarse entre las 10:00 am y las 23:59 pm
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Check in exitoso**    
**DADO** el código de reserva 1234 que corresponde a una reserva para la fecha en la que se está realizando el check in y que este mismo se está ralizando a las 11:00 am  
**CUANDO** el usuario ingrese el código de reserva 1234 y presione "cehck in"   
**ENTONCES** el sistema informa la habitacion asignada, manda un mensaje a alguno de los conserjes para guiar al usuario a la habitación y otro a los botones para que se hagan cargo de las valijas  

**Escenario 2: Check in fallido por código de reserva inválido**  
**DADO** el código de reserva 1234 que no corresponde a una reserva para la fecha en la que se está realizando el check in y que este mismo se está ralizando a las 11:00 am  
**CUANDO** el usuario ingrese el código de reserva 1234 y presione "cehck in"   
**ENTONCES** el sistema informa que el código ingresado no corresponde a una reserva en la fecha que se está realizando el chek in    

**Escenario 3: Check in fallido por intento fuera del horario permitido**  
**DADO** el código de reserva 1234 que no corresponde a una reserva para la fecha en la que se está realizando el check in, y que este mismo se está ralizando a las 09:00 am  
**CUANDO** el usuario ingrese el código de reserva 1234 y presione "cehck in"   
**ENTONCES** el sistema informa que todavía no se encuentran habilitados los ingresos al hotel    

---

### <mark>ID: Check out</mark>
### TÍTULO: Como conserje quiero realizar el check out para poder liberar la habitación y dejarla disponible para futuras reservas
### REGLAS DE NEGOCIO:
- Solo se puede realizar el check out de una habitación si la misma no posee gastos 
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Check out exitoso**    
**DADO** el número 123 correspondiente a una habitación del hotel, la cual no posee gastos  
**CUANDO** el conserje ingrese el numero de habitación 123 y presione "check out"  
**ENTONCES** el sistema libera la habitación y envía un mensaje a las mucamas avisando que la habitación puede limpiarse 

**Escenario 2: Check out fallido por habitación con gastos**  
**DADO** el número 123 correspondiente a una habitación del hotel, la cual posee gastos  
**CUANDO** el conserje ingrese el numero de habitación 123 y presione "check out"  
**ENTONCES** el sistema informa que no puede realizarse el check out hasta que se abonen los gastos realizados  

**Escenario 3: Check out fallido por número de habitación inválido**  
**DADO** el número 312 que no corresponde a ninguna habitación del hotel  
**CUANDO** el conserje ingrese el numero de habitación 123 y presione "check out"  
**ENTONCES** el sistema informa que el número ingresado no corresponde a una habitación del hotel  


