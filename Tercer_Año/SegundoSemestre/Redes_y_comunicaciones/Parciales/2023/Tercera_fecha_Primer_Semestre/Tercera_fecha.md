# Tercera fecha 2023

### Ejercicio 1

>[!note]NOTA
> La resolución de este ejercicio está en el archivo [Subnetting.pdf](Subnetting.pdf).

### Ejercicio 2

|Destino|Mask|Next-Hop|Iface|
|-------|----|--------|-----|
|160.10.0.0|/30|-|eth0|
|200.4.0.128|/26|-|eth3|
|10.1.1.0|/30|-|eth2|
|10.1.1.8|/29|-|eth1|
|200.4.0.192|/26|10.1.1.2|eth2|
|54.5.0.0|/30|10.1.1.11|eth1|
|50.0.0.0|/26|10.1.1.11|eth1|
|50.0.0.64|/26|10.1.1.11|eth1|
|0.0.0.0|/0|160.10.0.1|eth0|

- Después de sumarizar
  
|Destino|Mask|Next-Hop|Iface|
|-------|----|--------|-----|
|160.10.0.0|/30|-|eth0|
|200.4.0.128|/26|-|eth3|
|10.1.1.0|/30|-|eth2|
|10.1.1.8|/29|-|eth1|
|200.4.0.192|/26|10.1.1.2|eth2|
|54.5.0.0|/30|10.1.1.11|eth1|
|50.0.0.0|/25|10.1.1.11|eth1|
|0.0.0.0|/0|160.10.0.1|eth0|

- En el caso de que la interfaz eth1 de R-A se desactivara, solo deberíamos cambiar los next-hops de las rutas que utilizan eth1 como interfaz de salida, quedando la tabla de la siguiente manera:

|Destino|Mask|Next-Hop|Iface|
|-------|----|--------|-----|
|160.10.0.0|/30|-|eth0|
|200.4.0.128|/26|-|eth3|
|10.1.1.0|/30|-|eth2|
|10.1.1.8|/29|10.1.1.2|eth2|
|200.4.0.192|/26|10.1.1.2|eth2|
|54.5.0.0|/30|10.1.1.2|eth2|
|50.0.0.0|/25|10.1.1.2|eth2|
|0.0.0.0|/0|160.10.0.1|eth0|


### Ejercicio 3

1. El comando curl completo es:
   ```bash
      $ curl -X GET -v HTTP://www.info.unlp.edu.ar/index.html
      * Connected to www.info.unlp.edu.ar (163.10.5.71) port 80 (#0)
      < HTTP/1.1 301 Moved Permanently
      < Host: www.info.unlp.edu.ar
      < User-Agent: curl/7.88.1
      < Location: https://www.info.unlp.edu.ar/index.html
      < Content-Type: text/html; charset=UTF-8
      < Server: Apache2
      < Content-Length: 52
      <
      < <html> <body> mensaje personalizado </body> </html>
   ```
2. Teniendo en cuenta que los protocolos intervinientes son DNS, HTTP y HTTPS, el protocolo de la capa de aplicación que salió primero fue DNS, ya que es necesario para resolver el nombre de dominio a una dirección IP antes de que se pueda establecer una conexión HTTP o HTTPS. Luego, se establece la conexión HTTP solicitando el recurso ``/index.html`` del servidor web. Finalmente, el servidor responde con un código 301, indicando que el recurso ha sido movido permanentemente a una nueva ubicación, que es la versión segura del sitio web (HTTPS). Por lo tanto, el orden de aparición de los protocolos en la comunicación es: DNS, HTTP y luego HTTPS.
3. En base a la respuesta recibida por el comando, no se obtuvo el recurso ``/index.html`` solicitado inicialmente, sino que se recibió una redirección (código 301) a la versión segura del sitio web (HTTPS). Esto indica que el servidor web ha movido permanentemente el recurso a una nueva ubicación y que se debe acceder a él utilizando HTTPS en lugar de HTTP. Por lo tanto, el recurso solicitado originalmente no fue entregado, sino que se proporcionó una nueva ubicación para acceder a él de manera segura.
4. Con la información disponible se puede deducir que la conexión es persistente, ya que se utiliza HTTP 1.1 y no se observa el encabezado ``Connection: close`` en la respuesta del servidor. En HTTP 1.1, las conexiones son persistentes por defecto, lo que significa que la conexión TCP se mantiene abierta para permitir múltiples solicitudes y respuestas entre el cliente y el servidor sin necesidad de abrir una nueva conexión para cada intercambio de datos.

### Ejercicio 4

#### Inciso a

1. Hay 5 conexiones establecidas
   1. `211.10.2.2:44696` con `50.33.9.14:443`
   2. `211.10.2.2:37610` con `211.10.2.2:38696`
   3. `211.10.2.2:33692` con `20.10.22.11:443`
   4. `211.10.2.2:25` con `8.10.123.12:8273`
   5. `211.10.2.2:45042` con `142.14.175.16:993`

>[!note]NOTA
> 211.10.2.2:38696 -> 211.10.2.2:37610 es una conexión que ya se estableció previamente en "2.", es simplemente una respuesta, no una nueva conexión. 

- En la conexión 1, actúa como cliente
- En la conexión 2, actúa como cliente y servidor
- En la conexión 3, actúa como cliente
- En la conexión 4, actúa como cliente
- En la conexión 5, actúa como cliente

#### Inciso b

Lo que ocurre es que enviará a quien solicito la conexión un SYN ACK, ya que tiene al puerto 993 escuchando. Esto puede observarse en la linea `tcp 0.0.0.0:993 0.0.0.0:* Listen`

#### Inciso c

No va a poder recibir datagramas UDP de ningún otro host en el puerto 53, ya que esta mapeado en la IP ``127.0.0.1``, es decir, localhost. Por ende, solo podrá recibir datagramas UDP de la misma máquina.

#### Inciso d

No va a poder recibir paquetes TCP de ningún otro host en el puerto 8080, ya que esta mapeado en la IP ``127.0.0.1``, es decir, localhost. Por ende, solo podrá recibir paquetes TCP de la misma máquina.

### Ejercicio 5

>[!note]NOTA
> La resolución de este ejercicio está en el archivo [Tcp.pdf](Tcp.pdf).

### Ejercicio 6

- ARP Request
   |Ip Origen|MAC Origen|IP Destino|MAC Destino|
   |---------|----------|----------|-----------|
   |10.1.1.1|MAC_R-A|10.1.1.2|00:00:00:00:00:00|
- ARP Reply
   |Ip Origen|MAC Origen|IP Destino|MAC Destino|
   |---------|----------|----------|-----------|
   |10.1.1.2|MAC_R-B|10.1.1.1|MAC_R-A|

### Ejercicio 7

1. En total, hay 10 dominios de colision en la red.

>[!note]NOTA
> Los hubs no segmentan dominios de colisión, por lo que todos los puertos de un hub pertenecen al mismo dominio de colisión.

2. Como el problema es que se producen muchas colisiones, se podría reemplazar el hub por un switch. De esta manera, cada puerto del switch pertenecería a un dominio de colisión independiente, eliminando las colisiones en la red.

3. Los dominios de broadcast en la red siguen siendo los mismos, en cambio los dominios de colisión han aumentado a 12.

### Ejercicio 8

1. Falso. PC-B podría ver tráfico de ARP por ejemplo, ya que los mensajes ARP son enviados en broadcast a todos los dispositivos de la red local.
2. Falso. Si una de las partes finaliza la comunicación con FIN durante una conexión TCP, esa parte no podrá enviar más datos, pero la otra parte aún podrá enviar datos hasta que también envíe un FIN para finalizar la conexión por completo.