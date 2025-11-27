# Segunda Fecha 2025

### Ejercicio 1

#### Inciso a

Debería incluír el registro `NS` para poder resolver el dominio "redes.unlp.edu.ar", un registro `A` para resolver la dirección IP de "www.redes.unlp.edu.ar" y como es el único servidor dns autorizado para el dominio, también debería incluír un registro `SOA`.

- redes.unlp.edu.ar 777 IN `SOA` ns.redes.unlp.edu.ar  
  admin.redes.unlp.edu.ar. 
  2025120201 ; Serial  
  3600 ; Refresh  
  600 ; Retry  
  604800 ; Expire  
  86400 ; Minimum TTL
- redes.unlp.edu.ar 777 IN `NS` ns.redes.unlp.edu.ar
- ns.redes.unlp.edu.ar 777 IN `A` 100.11.0.58
- redes.unlp.edu.ar 777 IN `MX` 10 correo.redes.unlp.edu.ar
- correo.redes.unlp.edu.ar 777 IN `A` 100.11.0.50
- www.redes.unlp.edu.ar 777 IN `A` 100.11.0.50

> [!note] Nota
> admin.redes.unlp.edu.ar en el registro `SOA` representa la dirección de correo electrónico del administrador del dominio, donde el primer punto (.) se interpreta como una arroba (@).

#### Inciso b

- **i)** El cliente idica a cual de los dos dominios quiere acceder a través del nombre de dominio en la cabecera `Host` del mensaje HTTP. Un ejemplo del requerimiento HTTP 1.1 sería:

```
GET /recurso HTTP/1.1
Host: www.redes.unlp.edu.ar
```

- **ii)** Las ip origen y destino de los mensajes  DNS que envía y recibe PC-A para poder resolver el requerimiento anterior son:
  - Mensaje 1: Consulta DNS al servidor DNS local
    - IP Origen: `172.20.10.12`
    - IP Destino: `172.20.10.14`
  - Mensaje 2: Respuesta DNS del servidor DNS local
    - IP Origen: `172.20.10.14`
    - IP Destino: `172.20.10.12`

#### Inciso c

Si un usuario con cuenta de correo `@redes.unlp.edu.ar` quiere recuperar los correos recibidos, la consulta DNS que debería realizar a su User-Agent sería una consulta de tipo `MX` para obtener el servidor de correo, y luego una consulta de tipo `A` para obtener la dirección IP del mismo.

### Ejercicio 2

#### Inciso a

|MAC|Port|
|---|----|
|00:00:00:aa:00:03|e1|
|MAC_n1|e0|
|00:00:00:cc:00:04|e2|

#### Inciso b

El tráfico se está capturando por el Router en la interfaz con IP `10.0.0.1`, ya que n3 no puede ser porque vemos en TCPDUMP que la IP origen de los ARP request es `10.0.0.21` lo que descarta a n3 con su IP `10.0.1.20` por la máscara en /24. Por ende nos quedan como opciones n1, n2 o Router y como no vemos el  ARP replay de la primera request (`10.0.0.20`) pero si de la segunda request (`10.0.0.1`) podemos descartar a n1 y n2, quedando como única opción el Router.

#### Inciso c

- Dominio de colisión: 
  - En la red `10.0.0.0/24` hay 3 dominios de colision:
    - Entre n1 y el switch
    - Entre n2 y el switch
    - Entre el switch y el Router
  - En la red `10.0.1.0/24` hay 1 dominio de colisión:
    - Entre n3 y el Router ya que no hay switch en esa red.
  - En total hay 4 dominios de colisión.
- Dominio de broadcast:
  - Como los routers separan dominios de broadcast y hay un solo router, hay 2 dominios de broadcast

### Ejercicio 3

La dirección de red `185.100.60.0/23` es de clase B, ya que su primer octeto (185) está entre 128 y 191, y es pública porque no pertenece a los rangos reservados para redes privadas de la clase B.

>[!tip] Nota
> La resolución del subnetting está en el archivo [subnetting.pdf](Subnetting.pdf)

### Ejercicio 4

|Destino|Máscara|Next-Hop|Iface|
|-------|-------|--------|-----|
|172.20.10.0|/25|-|eth0|
|10.0.0.8|/30|-|eth2|
|10.0.0.0|/29|-|eth1|
|10.0.0.12|/30|10.0.0.2|eth1|
|172.20.10.128|/26|10.0.0.2|eth1|
|172.20.10.192|/26|10.0.0.2|eth1|
|0.0.0.0|/0|10.0.0.10|eth2|

- Luego de sumarizar

|Destino|Máscara|Next-Hop|Iface|
|-------|-------|--------|-----|
|172.20.10.0|/25|-|eth0|
|10.0.0.8|/30|-|eth2|
|10.0.0.0|/29|-|eth1|
|10.0.0.12|/30|10.0.0.2|eth1|
|172.20.10.128|/25|10.0.0.2|eth1|
|0.0.0.0|/0|10.0.0.10|eth2|

- Si se cae el enlace entre router 1 e ISP1:

|Destino|Máscara|Next-Hop|Iface|
|-------|-------|--------|-----|
|172.20.10.0|/25|-|eth0|
|10.0.0.8|/30|-|eth2|
|10.0.0.0|/29|-|eth1|
|10.0.0.12|/30|10.0.0.2|eth1|
|172.20.10.128|/25|10.0.0.2|eth1|
|0.0.0.0|/0|10.0.0.3|eth1|

>[!tip] Nota
> La aignación de direcciones a los dispositivos de la topología para poder resolver la tabla de ruteo está en el archivo [asignacion_direcciones.pdf](Asignacion_direcciones.pdf)

### Ejercicio 5

#### Inciso a

192.168.1.110:12576 163.45.10.12:443 [ S ] seq: 3065895943 len:0  
163.45.10.12:443 192.168.1.110:12576 [ SA ] seq: <u>3143359855</u> ack: 3065895944 len:0  
192.168.1.110:12576 163.45.10.12:443 [ A ] seq: <u>3065895944</u> ack: 3143359856 len:0  
192.168.1.110:12576 163.45.10.12:443 [ A ] seq: <u>3065895944</u> ack: 3143359856 len:50  
163.45.10.12:443 192.168.1.110:12576 [ A ] seq: 3143359856 ack: <u>3065895994</u> len:100  
163.45.10.12:443 192.168.1.110:12576 [ A ] seq: 3143359956 ack: <u>3065895994</u> len:20  
163.45.10.12:443 192.168.1.110:12576 [ A ] seq: 3143359976 ack: <u>3065895994</u> len:100  
192.168.1.110:12576 163.45.10.12:443 [ A ] seq: <u>3065895994</u> ack: 3143360076 len:0  

#### Inciso b

- El ISN de `192.168.1.110` es `3065895943`.
- El ISN de `163.45.10.12` es `3143359855`.

#### Inciso c

Como no se observa un vencimiento de RTO (Retransmission Time Out), ni tampoco un triple ACK duplicado podemos afirmar que no hay un disparador de control de congestión.