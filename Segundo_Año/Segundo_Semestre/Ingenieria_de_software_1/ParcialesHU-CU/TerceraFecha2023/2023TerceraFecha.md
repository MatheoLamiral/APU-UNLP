### <mark>ID: Registrarse </mark>
### TÍTULO: Como usuario quiero registrarme en el sistema para poder realizar reservas
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Registro exitoso sin reocmendarción**    
**DADO**  el dni 45678920 que no se encuentra registrado en el sistema y que el registro no es por recomendación    
**CUANDO**  el usuario ingresa el dni 45678920, nombre pepe, apellido gonzales, edad 32, mail pepe@gmail.com, direccion calle 1 entre 500 y 600 y presione "registrarse"    
**ENTONCES** el sistema genera una contraseña asignada al usuario la cual se le envía por mail al mismo     

**Escenario 2: Registro exitoso con recomendación**  
**DADO**  el dni 45678920 que no se encuentra registrado en el sistema y que el registro es por recomendación del usuario con dni 23456739 que posee 60 puntos    
**CUANDO**  el usuario ingresa el dni 45678920, nombre pepe, apellido gonzales, edad 32, mail pepe@gmail.com, direccion calle 1 entre 500 y 600, el dni de quien hizo la recomendación dni 23456739 y presione "registrarse"    
**ENTONCES** el sistema genera una contraseña asignada al usuario la cual se le envía por mail al mismo y suma 10 puntos al usuario que realizó la recomendación    

**Escenario 3: Registro exitoso con recomendación mas bonificación por 100 puntos**  
**DADO**  el dni 45678920 que no se encuentra registrado en el sistema y que el registro es por recomendación del usuario con dni 23456739 que posee 90 puntos    
**CUANDO**  el usuario ingresa el dni 45678920, nombre pepe, apellido gonzales, edad 32, mail pepe@gmail.com, direccion calle 1 entre 500 y 600, el dni de quien hizo la recomendación dni 23456739 y presione "registrarse"    
**ENTONCES** el sistema genera una contraseña asignada al usuario la cual se le envía por mail al mismo y suma 10 puntos al usuario que realizó la recomendación, y envía un correo a este último avisando que es acreeedor de un turno gratis, incrementa en uno la cantidad de turnos gratis del usuartio que realizo la recomendación y setea a 0 su cantidad de puntos  

**Escenario 4: Registro fallido por dni repetido**  
**DADO**  el dni 45678920 que se encuentra registrado en el sistema  
**CUANDO**  el usuario ingresa el dni 45678920, nombre pepe, apellido gonzales, edad 32, mail pepe@gmail.com, direccion calle 1 entre 500 y 600 y presione "registrarse"  
**ENTONCES** el sistema genera informa que el dni ingresado ya se encuentra registrado  

---

### <mark>ID: Reserva </mark>
### TÍTULO: Como usuario quiero realizar una reserva para poder usar la cancha 
### REGLAS DE NEGOCIO:
### CRITERIOS DE ACEPTACIÓN:
**Escenario 1: Reserva exitosa**    
**DADO** la fecha 10/10/2024, hora 22:30 hs y la cancha 3 que se encuentra disponible en ese horario y esa fecha  
**CUANDO**  el usuario ingresa fecha 10/10/2024, hora 22:30 hs, cancha 3 y presiona "reservar"   
**ENTONCES** el sistema registra la reserva y envía un mail al usuario con un código de reserva y el precio del turno (0 si es bonificado)  

**Escenario 2: Reserva fallida por turno ocupado**  
**DADO** la fecha 10/10/2024, hora 22:30 hs y la cancha 3 que no se encuentra disponible en ese horario y esa fecha  
**CUANDO**  el usuario ingresa fecha 10/10/2024, hora 22:30 hs, cancha 3 y presiona "reservar"   
**ENTONCES** el sistema informa que la cancha se encuentra ocupada en la fecha y hora ingresadas  
 
