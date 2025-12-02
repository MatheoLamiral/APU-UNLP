# Segunda Fecha 2023

### Ejercicio 1

- Para red C, como nos pide que le asignemos un bloque con el cual sea posible sumarizar, le asignamos un `/26`, para luego poder sumarizarlo con la red A, y luego ese `/25` sumarizarlo con la red B. Quedando en la entrada de la tabla de rutas del Router R-D una sola entrada `/24` que englobe a las tres redes.

>[!note] NOTA
> La resolución esta en el archivo [subnetting.pdf](Subnetting.pdf). Tener en cuenta que por error de enunciado el bloque `172.16.3.64/25` se toma como `/26` en lugar de `/25`

#### Tabla de ruteo de R-D
|Destino|Mask|Next-Hop|Iface|
|---|---|---|---|
|172.16.3.72|/29|-|eth1|
|200.20.20.128|/30|-|eth0|
|191.8.1.0|/26|-|eth2|
|191.8.0.192|/26|172.16.3.75|eth1|
|191.8.0.0|/25|172.16.3.75|eth1|
|191.8.0.128|/26|172.16.3.74|eth1|
|100.10.10.64|/30|172.16.3.74|eth1|
|172.16.3.68|/30|172.16.3.75|eth1|
|172.16.3.64|/30|172.16.3.75|eth1|
|0.0.0.0|/0|200.20.20.129|eth0|

- Luego de sumarizar

|Destino|Mask|Next-Hop|Iface|
|---|---|---|---|
|172.16.3.64|/28|-|eth1|
|200.20.20.128|/30|-|eth0|
|191.8.1.0|/26|-|eth2|
|191.8.0.0|/24|172.16.3.75|eth1|
|100.10.10.64|/30|172.16.3.74|eth1|
|0.0.0.0|/0|200.20.20.129|eth0|

### Ejercicio 2

#### Inciso a

|Destino|Mask|Next-Hop|Iface|
|---|---|---|---|
|191.8.0.0|/25|-|eth0|
|172.16.3.68|/30|-|eth3|
|172.16.3.64|/30|-|eth1|
|172.16.3.72|/29|-|eth2|
|191.8.0.192|/26|172.16.3.70|eth3|
|191.8.0.128|/26|172.16.3.65|eth1|
|191.8.1.0|/26|172.16.3.73|eth2|
|100.10.10.64|/30|172.16.3.65|eth1|
|200.20.20.128|/30|172.16.3.73|eth2|
|0.0.0.0|/0|172.16.3.73|eth2|

#### Inciso b

En caso de que ocurra un evento y el enlace entre R-B y SW-1 queda fuera de servicio, lo único que cambiaría para que no se interrumpa el acceso a internet, es el default getaway de R-B, que pasaría a ser `172.16.3.65` (R-A) en lugar de `172.16.3.73` (R-D).

### Ejercicio 3

Agregaria en `DNS Resolver` un registro de tipo `MX` con una prioridad para un agregar un servidor de correo, luego un registo de tipo `A` para asignarle una IP a ese servidor de correo. Además, se agregaria un servicio de SMTP para realizar el envio entre el MUA y el MSA, el MSA y el MTA y el MTA emisor y el MTA receptor. Por último, se agregaria un servicio de POP3 para que el MUA pueda recuperar los correos desde el MTA. Elegimos POP3 ya que es un protocolo más simple y liviano que IMAP, pero la desventaja es que no permite la sincronización entre múltiples dispositivos, solo se comparte la carpeta inbox (cualquier carpeta que cree en una computadora no será creada en el servidor) y no mantiene estado (si leo desde una computadora, no se refleja en otra).

### Ejercicio 4

##### Inciso a

>[!note] NOTA
> El inciso está resuelto en el archivo [Tcp.pdf](Tcp.pdf)

##### Inciso b

Una conexión FTP activa se diferencia de una pasiva por el puerto utilizado, siendo el puerto 21 de control (pasivo) y el 20 de datos (activo). En este caso, es una conexión pasiva ya que solo se envían datos de control (puerto 21, pasivo) y no se observa transferencia de datos (puerto 20, activo).

### Ejercicio 5

### Ejercicio 6

- ETH al salir de R-A
    |MAC ORIGEN|MAC DESTINO|
    |---|---|
    |MAC_R-A|FF:FF:FF:FF:FF:FF|
- ARP Request al salir de R-A
    |MAC ORIGEN|IP ORIGEN|MAC DESTINO|IP DESTINO|
    |---|---|---|---|
    |MAC_R-A|172.16.3.74|00:00:00:00:00:00|172.16.3.73|
- ARP Reply al volver de R-D
    |MAC ORIGEN|IP ORIGEN|MAC DESTINO|IP DESTINO|
    |---|---|---|---|
    |MAC_R-D|172.16.3.73|MAC_R-A|172.16.3.74|
- ETH al volver de R-D
    |MAC ORIGEN|MAC DESTINO|
    |---|---|
    |MAC_R-D|MAC_R-A|

### Ejercicio 7

- Cuando PC-B accede a www.google.com, no hay entradas en SW-1 ya que sale directamente a través de R-A.
- Cuando PC-C hace un intercambio satisfactorio DNS con DNS Resolver, SW-1 tendrá dos entradas, al recibir el request de R-B y otra al recibir el reply de R-D
    |MAC|Puerto|
    |---|---|
    |MAC_R-B|eth2|
    |MAC_R-D|eth1|

### Ejercicio 8

1. Verdadero. Al enviar un segmeneto TCP con errores, el checksum no coincide, se descarta el paquete y al terminar el RTO se reenvia el segmento ya que no se recibió el ACK correspondiente.
2. Falso. El control de flujo se activa cuando el receptor no puede procesar los datos a la velocidad que el emisor los envía, no por errores en la transmisión, en ese caso se activaria el control de congestion. Es decir, el control de flujo se activa en conexiones end to end y el control de congestion en conexiones con intermediarios (routers).
3. Falso. El `SOA` indica informacion autoritativa como el servidor primario, el email del administrador, el serial del dominio, el refresh, retry, expire y el TTL por defecto. Sin embargo, no posee informacion sobre el DNS en si, sino que almacena el nombre del servidor autoritativo.
4. Verdadero. UDP responde con ICMP Destination, Host o Port Unreachable cuando no hay una red, un dispositivo o un puerto disponible para la comunicación.
5. Falso. En el header de un requerimiento HTTP no puedo indicar la versión de HTTP que deseo utilizar, ya que esto se define en la línea de requerimiento (Request Line, por ejemplo, `GET /recurso HTTP/1.1`).