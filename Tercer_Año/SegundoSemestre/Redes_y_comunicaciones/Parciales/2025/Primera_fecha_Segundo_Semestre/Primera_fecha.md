# Primera fecha Segundo semestre 2025

### Ejercicio 1

El ejercicio de subnetting y la asignación de direcciones está realizada en el archivo [Subnetting.pd](Subnetting.pdf)

### Ejercicio 2

|Destino|Mask|Next-Hop|Iface|
|--|--|--|--|
|49.183.69.128|/29|-|eth2|
|49.183.69.136|/30|-|eth0|
|49.183.69.140|/30|-|eth1|
|49.183.68.0|/25|49.183.69.130|eth2|
|49.183.68.128|/25|49.183.69.128|eth2|
|49.183.69.64|/27|49.183.69.141|eth1|
|49.183.69.0|/26|49.183.69.141|eth1|
|49.183.69.96|/27|49.183.69.141|eth1|
|0.0.0.0|/0|49.183.69.136|eth0|

- Después de sumarizar `49.183.69.64/27` y `49.183.69.96/27`

|Destino|Mask|Next-Hop|Iface|
|--|--|--|--|
|49.183.69.128|/29|-|eth2|
|49.183.69.136|/30|-|eth0|
|49.183.69.140|/30|-|eth1|
|49.183.68.0|/25|49.183.69.130|eth2|
|49.183.68.128|/25|49.183.69.128|eth2|
|49.183.69.64|/26|49.183.69.141|eth1|
|49.183.69.0|/26|49.183.69.141|eth1|
|0.0.0.0|/0|49.183.69.136|eth0|

- Después de sumarizar `49.183.69.0/26` y `49.183.69.64/26`

|Destino|Mask|Next-Hop|Iface|
|--|--|--|--|
|49.183.69.128|/29|-|eth2|
|49.183.69.136|/30|-|eth0|
|49.183.69.140|/30|-|eth1|
|49.183.68.0|/25|49.183.69.130|eth2|
|49.183.68.128|/25|49.183.69.128|eth2|
|49.183.69.0|/25|49.183.69.141|eth1|
|0.0.0.0|/0|49.183.69.136|eth0|

### Ejercicio 3

- La dirección MAC que necesita PC-D para poder comunicarse es la del Router 4
- Por lo tanto la dirección IP del requerimiento ARP será `49.183.69.97`(Router 4)
- Datos del ARP Request y su trama ethernet que envía la interfaz eth0 de Router 4
  - ARP Request
    |MAC Origen|IP Origen|MAC Destino|IP Destino|
    |--|--|--|--|
    |MAC_Router4_eth0|49.183.69.141|00:00:00:00:00:00|49.183.69.142|
  - Trama ethernet  

    |MAC Origen|MAC Destino|
    |--|--|
    |MAC_Router4_eth0|FF:FF:FF:FF:FF:FF|

### Ejercicio 4

- Los dispositivos que dividen dominios de broadcast son los Routers. La cantidad de dominios de broadcast en el diagrama 1 es 8
- Los disposittivos que dividen dominios de colisión son los Switches. La cantidad de dominios de colisión en el diagrama 1 es 16

### Ejercicio 5

`redes.unlp.edu.ar IN A 49.183.69.68`

### Ejercicio 6

- No se obtuvo el recurso solicitado ya que la respuesta indica 301 Moved Permanently, lo que significa que el recurso ha sido movido a una nueva URL de forma permanente. El cliente debe realizar una nueva solicitud a la URL proporcionada en la cabecera "Location" para acceder al recurso.
- El protocolo de capa de transporte utilizado es TCP, ya que HTTP se basa en TCP para la transmisión de datos. Y el puerto de destino es el 80, que es el puerto estándar para HTTP.

### Ejercicio 7

- El host `127.217.29.145` está intentando realizar un trhee-way handshake con el host `172.217.29.253` para establecer una conexión TCP 
- Como la respuesta del host `172.217.29.253` es el flag RST, significa que el host está rechazando la conexión. Esto puede deberse a varias razones, como que el puerto esté cerrado o no haya ningún proceso escuchando en ese puerto.
- el host `172.217.29.253` podría tener los puertos 110 (UDP) y el 110 (TCP) abiertos al mismo tiempo, ya que, TCP y UDP son protocolos de la capa de transporte distintos y utilizan mecanismos de identificación y desmultiplexación separados.  Un socket TCP se identifica mediante una cuádrupla (4-upla) única que incluye la dirección IP de origen, el número de puerto de origen, la dirección IP de destino y el número de puerto de destino. El receptor utiliza estos cuatro valores para redirigir el segmento al socket adecuado. La desmultiplexación UDP es más simple, el host de destino comprueba el número de puerto de destino del segmento y lo dirige al socket correspondiente.