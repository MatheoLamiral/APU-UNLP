# Práctica 8 - Ruteo

## Fragmentación

### Ejercicio 1

- IP origen, IP destino y campos correspondientes a la fragmentación cuando el paquete sale de PC1:   
  - IP origen: `10.0.0.20/24`
  - IP destino: `10.0.2.20/24`
  - Header Length: `20 bytes`
  - Total Length: `1500 bytes`
  - id: `20543`
  - DF: `0`
  - MF: `0`
  - Fragment Offset: `0`
  >[!note]
  > - **DF (Don't Fragment)**: indica si el paquete puede ser fragmentado o no. En este caso, está en 0, lo que significa que el paquete puede ser fragmentado si es necesario. 
  > - **MF (More Fragments)**: indica si hay más fragmentos después de este. En este caso, está en 0, lo que significa que este es el único fragmento del paquete.
- ¿Qué sucede cuando el paquete debe ser reenviado por el router R1?
  - Cuando el paquete debe ser reenviado por el router R1, el mismo debe fragmentarse ya que su tamaño total de 1500 bytes supera al MTU máximo de 600 bytes
- Indicar cómo quedarían los paquetes fragmentados para ser enviados por el enlace entre R1 y R2
  - Al tener un tamaño de 1500, pero el MTU ser máximo  580B (600B - 20B(de cabecera)=580B), se debe dividir el datagrama de forma que se desperdicie la menor cantidad de espacio posible. Como El fragment offset (fragmento de desplazamiento) funciona de a 8 bytes, la cantidad máxima de datos que vamos a poder mandar, va a ser el múltiplo de 8 más cercano a el espacio disponible para datos (580), en este caso 576, que junto con los 20B de la cabecera, nos da un total de 596. Entonces como 1480 (restamos la cabecera)/576 = 3, debemos enviar 3 paquetes. El valor de fragment offset para cada caso es la `cantidad de bytes enviados/8`.
  - **Paquete 1**:
    - cabecera: 20 bytes
    - tamaño: 596
    - id: 20543
    - DF: 1
    - MF: 1
    - Fragment offset: 0 ya que en el primer envío no debemos desplazarnos 
  - **Paquete 2**:
    - cabecera: 20 bytes
    - tamaño: 596
    - id: 20543
    -   DF: 1
    - MF: 1
	- Fragment offset: 72 (por los 576 que mandamos en el primer paquete div 8) 
  - **Paquete 3**:
    - cabecera: 20 bytes
    - tamaño:  348(328 de los datos que nos faltaban + la cabecera)
    - id: 20543
    - DF: 1
    - MF: 0
    - Fragment offset: 144 (por los 1152 que mandamos en los primeros dos paquetes div 8) 
- ¿Dónde se unen nuevamente los fragmentos? ¿Qué sucede si un fragmento no llega?
  - Los paquetes se unen nuevamente en el host de destino, en este caso PC2. Si un fragmento no llega, el accionar dependerá del protocolo de capa de transporte que se utilice. Si se utiliza TCP, el host de destino no enviará un acuse de recibo (ACK) para los datos faltantes, generando una retransmisión. Si se utiliza UDP, que no tiene mecanismos de control de errores, los datos faltantes simplemente se perderán y no se retransmitirán.  
- Si un fragmento tiene que ser reenviado por un enlace con un MTU menor al tamaño del fragmento, ¿qué hará el router con ese fragmento?
  - El router volverá a fragmentar el fragmento que recibió, creando nuevos fragmentos con los datos del fragmento original.
 
## Ruteo

### Ejercicio 2

El ruteo se refiere al proceso que realiza la red en su conjunto para **determinar la ruta o camino que deben seguir los paquetes a medida que fluyen de un emisor a un receptor**. Se considera una **función de control**, es decir, que **no maneja directamente el tráfico de datos, sino que toma decisiones sobre como se debería enrutar el tráfico**. Los algoritmos de enrutamiento calculan estas rutas. El ruteo se encarga de determinar los valores que se introducen en las tablas de reenvío de los routers. Un **router despacha mensajes consultando su tabla de ruteo**, desde cualquier interfaz. La tabla de ruteo tiene una estructura que incluye la **Red Destino** (Net), **Máscara** (Mask), el **Próximo Salto** (Next Hop) y la **Interfaz de salida**. El ruteo es fundamental para cumplir la función principal de la Capa de Red, **transportar paquetes desde un host emisor a un host receptor**

### Ejercicio 3

- **Ruteo estático**
  - Las **rutas se configuran manualmente por el administrador**. No cambian a menos que haya una intervención humana directa. Es ideal para redes donde la topología es predecible y estable.
  - **Ventajas**
    - Fácil de implementar en redes pequeñas y simples
    - No consume recursos adicionales en el router para calcular rutas
    - Ofrece mayor control y seguridad
  - **Desventajas**
    - Es un método más propenso a errores y no es escalable
    - No se adapta automáticamente a los cambios en la topología de la red
- **Ruteo dinámico**
  - Utiliza **protocolos de enrutamiento para aprender y actualizar rutas automáticamente** aunque **requiere una configuración inicial**.
  - **Ventajas**
    - Se adaptan automáticamente a fallos y cambios en la topología de la red
    - Escalable y tolerante a fallos
    - Facilita la administración de redes grandes y complejas
  - **Desventajas**
    - Requiere más recursos de procesamiento y ancho de banda
    - La configuración inicial es más compleja

### Ejercicio 4

Si, una maquina conectada a una red, pero no a internet, sigue necesitando una tabla de ruteo para determinar cómo enviar los paquetes a otros dispositivos dentro de la misma red local. **Sin una tabla de ruteo, la máquina no podría comunicarse eficazmente con otros dispositivos en la red**, ya que no sabría cómo enrutar los paquetes correctamente.

### Ejercicio 5

#### Inciso a

- No está completa al tabla de ruteo. 
  - Falta la entrada para la red `10.0.0.8/30`
  - No existe una red `205.10.128.0/25`, debería ser `205.10.0.128/25`, además de que el next-hop debería ser `10.0.0.1` en lugar de `10.0.0.2`
  - No existe una red `205.20.0.193`, debería ser `205.20.0.192/26`, además de que, aunque el next-hop es "correcto", hay un camino más corto hacia `205.20.0.192/26` a través de `10.0.0.5` en lugar de `10.0.0.1`, por ende, deberíamos cambiar el next-hop y la iface

- Tabla de ruteo completa y corregida de Rtr-D:

|Red Destino|Máscara|Next-Hop|Iface|
|-----------|-------|--------|-----|
|153.10.20.128|/27| - |eth1|
|10.0.0.4|/30| - |eth0|
|10.0.0.0|/30| - |eth5|
|10.0.0.8|/30| - |eth3|
|10.0.0.16|/30|10.0.0.10|eth3|
|10.0.0.12|/30|10.0.0.5|eth0|
|163.10.5.64|/27|10.0.0.10|eth3|
|205.10.0.128|/25|10.0.0.1|eth5|
|205.20.0.192|/26|10.0.0.5|eth0|
|205.20.0.128|/26|10.0.0.5|eth0|

>[!important]
> Notar que no agregamos un default getaway ya que en el enunciado no se especifica que Rtr-D tenga que tener salida a internet. Además, se nos especifica que no es necesario agregar las redes que conectan contra los ISPs

#### Inciso b

- La tabla anterior no tiene salida a internet ya que no tiene configurado un default getaway
- Lo solucionaría confiugando un default getaway que tenga como next-hop Rtr-C en la tabla de ruteo.
    |Red Destino|Máscara|Next-Hop|Iface|
    |-----------|-------|--------|-----|
    |...|...|...|...|
    |0.0.0.0|/0|10.0.0.10|eth3|

#### Inciso c

Si Rtr-C tuviese esa entrada y un equipo en Red D intenta acceder a `163.10.5.15`, se producirá un bucle de ruteo. El paquete saldrá de Red D hacia Rtr-D, Rtr-D lo enviará a Rtr-C, y Rtr-C, por tener configurado como Next-Hop para `163.10.5.0` a Rtr-D, reenviará el paquete a Rtr-D. Entonces el paquete viajará de Rtr-D a Rtr-C en bucle hasta que el TTL expire, por lo que no habrá comunicación con `163.10.5.15`

#### Inciso d

- Sumarización
  - Técnica utilizada para **agrupar múltiples rutas de red en una sola entrada de tabla de ruteo**. Esto reduce el tamaño de las tablas de ruteo y mejora la eficiencia del enrutamiento.
  - **Consideraciones para sumarizar**
    - La **cantidad de bloques a sumarizar debe ser potencia de 2**
    - Los **bloques deben ser contiguos**
    - Todas las **direcciones deben compartir la misma secuencia de bits hasta el punto donde se cortará la máscara**
    - Todas las subredes deben ser **alcanzables a través de la misma interfáz y el mismo Next-Hop**
- Podemos aplicar sumarización para las siguientes entradas en la tabla de ruteo de Rtr-D:
  - `205.20.0.128` -> 205.20.0.10000000
  - `205.20.0.192` -> 205.20.0.11000000
  - La cantidad de bloques es potencia de 2 ya que estamos agrupando 2 bloques
  - Los bloques son contiguos ya que `205.20.0.128/26` cubre el rango de `205.20.0.128` a `205.20.0.191` y `205.20.0.192/26` cubre el rango de `205.20.0.192` a `205.20.0.255`
  - Ambas direcciones comparten la misma secuencia de bits hasta el bit 25 (205.20.0.1XXXXXXX)
  - Ambas subredes usan la misma interfáz `eth0` y el mismo Next-Hop `10.0.0.5`
- La nueva entrada en la tabla de ruteo de Rtr-D sería:
    |Red Destino|Máscara|Next-Hop|Iface|
    |-----------|-------|--------|-----|
    |205.20.0.128|/25|10.0.0.5|eth0|

#### Inciso e

La sumarización aplicada en el punto anterior no se podría aplicar en el Rtr-B ya que las redes `205.20.0.128` y `205.20.192` usan interfaces diferentes, `eth0` y `eth1` respectivamente, y por lo tanto no cumplen con todos los requisitos para poder sumarizar.

#### Inciso f

- Tabla de ruteo de Rtr-B
  - Debe llegarse a todas las redes del gráfico
  - Debe salir a Internet por Rtr-A
  - Debe pasar por Rtr-D para llegar a Red D
  - Sumarizar si es posible

|Red Destino|Máscara|Next-Hop|Iface|
|-----------|-------|--------|-----|
|205.20.0.128|/26|-|eth2|
|205.20.0.192|/26|-|eth0|
|10.0.0.12|/30|-|eth3|
|10.0.0.4|/30|-|eth1|
|10.0.0.0|/30|10.0.0.6|eth1|
|10.0.0.8|/30|10.0.0.6|eth1|
|10.0.0.16|/30|10.0.0.13|eth3|
|205.10.0.128|/25|10.0.0.13|eth3|
|153.10.20.128|/27|10.0.0.6|eth1|
|163.10.5.64|/27|10.0.0.6|eth1|
|120.0.0.0|/30|10.0.0.13|eth3|
|130.0.10.0|/30|10.0.0.13|eth3|
|0.0.0.0|/0|10.0.0.13|eth3|

#### Inciso g

Si es posible restablecer el acceso a Internet sin esperar a que vuelva la conectividad entre Rtr-C e ISP-2, pasando por Rtr-A y de ahí hacia ISP-1.

### Ejercicio 6

- **Mensaje ICMP enviado por PC-B a PC-C**
  - llega a destino?
    - si, llegará a destino
  - saltos que tomará
    - PC-B envía el mensaje con IP `10.0.7.20/24` a router2
    - router2 recibe el mensaje y como no tiene una entrada para el destino `10.0.7.20/24`(IP de PC-C), reenvía el mensaje al default gateway hacia router1 a través de la Iface `eth0` usando el Next-Hop `10.0.0.1`
    - router 1 recibe el mensaje y como tiene una entrada para `10.0.0.0/16`, que tiene como next hop a router3, lo reenvía a router3 a través de la Iface `eth1` usando el Next-Hop `10.0.3.1`
    - router 3 recibe el mensaje y como tiene una entrada para `10.0.7.0/24`, lo reenvia a PC-C a través de la Iface `eth2` con una conexión directa
  - tipo de respuesta recibida en el emisor
    - La respuesta ICMP que recibira esta de **tipo 0 con código 0 (Echo Reply)**
- **Mensaje ICMP enviado por PC-C a PC-B**
  - llegada a destino?
    - si, llegará a destino
    - saltos que tomará
      - PC-C envía el mensaje con IP `10.0.5.20/24` a router3
      - router3 recibe el mensaje y como no tiene una entrada para el destino `10.0.5.20/24`(IP de PC-B), reenvía el mensaje al default gateway hacia router4 a través de la Iface `eth0` usando el Next-Hop `10.0.2.1`
      - router 4 recibe el mensaje y como tiene una entrada para `10.0.0.0/16`, que tiene como next hop a router2, lo reenvía a router2 a través de la Iface `eth0` usando el Next-Hop `10.0.1.1`
      - router 2 recibe el mensaje y como tiene una entrada para `10.0.5.0/24`, lo reenvia a PC-B a través de la Iface `eth2` con una conexión directa
    - tipo de respuesta recibida en el emisor
      - La respuesta ICMP que recibira esta de **tipo 0 con código 0 (Echo Reply)**
- **Mensaje ICMP enviado por PC-C a 8.8.8.8**
  - llegada a destino?
    - no, no llegará a destino
  - saltos que tomará
    - PC-C envía el mensaje con IP `8.8.8.8` a router3
    - router3 recibe el mensaje y como no tiene una entrada para el destino `8.8.8.8`, reenvía el mensaje al default gateway hacia router4 a través de la Iface `eth0` usando el Next-Hop `10.0.2.1`
    - router 4 recibe el mensaje y como no tiene una entrada para el destino `8.8.8.8` y no tiene configurado un default gateway, descarta el paquete
  - tipo de respuesta recibida en el emisor
    - La respuesta que recibira esta de **tipo 3 con código 0 (Destination Unreachable)**
- **Mensaje ICMP enviado por PC-B a 8.8.8.8**
  - llegada a destino?
    - no, no llegará a destino
  - saltos que tomará
    - PC-B envía el mensaje con IP `8.8.8.8` a router2
    - router2 recibe el mensaje y como no tiene una entrada para el destino `8.8.8.8`, reenvía el mensaje al default gateway hacia router1 a través de la Iface `eth0` usando el Next-Hop `10.0.0.1`
    - router 1 recibe el mensaje y como no tiene una entrada para el destino `8.8.8.8`, reenvía el mensaje al default gateway hacia router2 a través de la Iface `eth0` usando el Next-Hop `10.0.0.2`
    - router 2 recibe el mensaje y como no tiene una entrada para el destino `8.8.8.8`, reenvía el mensaje nuevamente a router1, luego nuevamente router1 lo reenvía a router2, y así sucesivamente hasta que el TTL expire y el paquete sea descartado
  - tipo de respuesta recibida en el emisor
    - La respuesta que recibira esta de **tipo 11 con código 0 (Time Exceeded)**

## DHCP y NAT

### Ejercicio 7

#### Inciso a y b

Al iniciar Wireshark con el filtro bootp y ejecutar sudo /sbin/dhclient enp0s3, en la captura se observó la secuencia DHCP Request (enviada por el cliente) y DHCP ACK (enviada por el servidor), lo que indica que el cliente ya tenía un lease previo registrado en el archivo /var/lib/dhcp/dhclient.leases. Por este motivo, en lugar de iniciar el proceso completo (Discover → Offer → Request → ACK), el cliente simplemente intentó renovar la dirección IP que ya tenía asignada.
En la consola se observó el mensaje `RTNETLINK answers: File exists`, que significa que la interfaz ya tenía configurada una dirección IP o una ruta, y que dhclient no pudo volver a agregar una entrada de red que ya existía. Es decir, el cliente logró renovar la IP vía DHCP, pero el sistema no pudo reconfigurar ciertos parámetros porque ya estaban presentes.

>[!important]
> DHCP (Dynamic Host Configuration Protocol) es un protocolo de red de la Capa de Aplicación que funciona sobre UDP, diseñado para asignar automáticamente direcciones IP y otra información de configuración necesaria para que un dispositivo pueda comunicarse en una red

#### Inciso c

```bash
  root@debian:~# cat /var/lib/dhcp/dhclient.leases
  lease {
    interface "enp0s3";
    fixed-address 10.0.2.15;
    option subnet-mask 255.255.255.0;
    option dhcp-lease-time 86400;
    option routers 10.0.2.2;
    option dhcp-message-type 5;
    option domain-name-servers 181.30.140.195,181.30.140.134;
    option dhcp-server-identifier 10.0.2.2;
    option domain-name "fibertel.com.ar";
    renew 1 2025/11/24 01:57:15;
    rebind 1 2025/11/24 12:38:30;
    expire 1 2025/11/24 15:38:30;
  }
```

Este archivo guarda el historial de leases que el cliente DHCP obtuvo anteriormente.

#### Inciso d

```bash
  root@debian:~# rm /var/lib/dhcp/dhclient.leases
  root@debian:~# cat /var/lib/dhcp/dhclient.leases
  cat: /var/lib/dhcp/dhclient.leases: No such file or directory
```

#### Inciso e

Al eliminar el archivo `/var/lib/dhcp/dhclient.leases` en el punto anterior, el cliente DHCP pierde el registro de su asignación anterior. Por ese motivo, cuando se ejecuta nuevamente dhclient, el cliente no puede solicitar la misma IP anterior y comienza el proceso completo de DHCP como si fuera la primera vez, es decir realiza el proceso completo (Discover → Offer → Request → ACK).   
En la captura de Wireshark se observa un intercambio diferente al del punto b:
- En el punto b, como existía un lease previo, el cliente normalmente envía un DHCP Request directamente para intentar renovar la IP anterior.
- En el punto e, al no existir el archivo `dhclient.leases`, el cliente inicia el proceso desde cero enviando un DHCP Discover, seguido por DHCP Offer, Request y ACK.
- La diferencia principal, es que sin el archivo `dhclient.leases` el cliente no puede recordar su IP anterior y realiza el ciclo DHCP completo, generando más mensajes en la captura.

#### Inciso f

- Dirección IP asignada
- Máscara de subred
- Default gateway
- Servidores DNS
- Configuración proxy por WPAD (Web Proxy Auto-Discovery Protocol)
- Dirección IP del servidor DHCP que atendió la solicitud
- Duración del arrendamiento (lease time)

### Ejercicio 8

El **NAT (Network Address Translation, o Traducción de Direcciones de Red)** es un proceso que realiza la **traslación de direcciones IP de un espacio privado (no enrutable en Internet) a un espacio público**. Los procesos de **traslación se realizan sobre redes stubs (solo una salida) y se deben mantener tablas de traslaciones**.
En un entorno de servicio de internet hogareño donde varios dispositivos usan internet simultáneamente, la traduccion de direcciones se realiza mediante **NAPT (Network Address Port Translation)**, también conocida como **PAT (Port Address Translation)**. Esta técnica permite que múltiples hosts compartan una única dirección IP pública, modificando no solo la IP de origen sino también los números de puerto.
- **Direccionamiento interno**: dentro del hogar, los dispositivos utilizan direcciones IP privadas. Estas direcciones no son únicas y solo tienen significado dentro de la red privada, por lo tanto son habitualmente filtradas por los routers de borde de internet
- **Proceso de traslación (salida)**: cuando un host interno desea acceder a un servidor en internet, el router NAT realiza los siguientes pasos:
  - router NAT genera un nuevo número de puerto de origen
  - el router reescribe el datagrama IP, sustituyendo la dirección IP de origen privada por su dirección IP pública de la red WAN y sustituye el puerto de origen privado por el nuevo puerto público 
  - router crea una entrada en la tabla de traducciones NAT para registrar la correspondencia entre la tupla interna (IP privada y puerto privado) y la tupla externa (IP pública y puerto público)
- **Soporte de conexiones simultáneas**: Dado que el campo de puerto de origen tiene 16 bits, la traducción de puertos (NAPT) permite al router NAT dar soporte hasta a 60.000 conexiones simultáneas utilizando esa única dirección IP WAN
- **Proceso de retorno (entrada)**: Cuando el servidor externo responde, el datagrama tiene como destino la dirección IP pública del router NAT y el puerto público asignado
  -  El router NAT indexa la tabla de traducciones NAT utilizando la dirección IP de destino y el puerto de destino
  -  A partir de la tabla, obtiene la dirección IP y el número de puerto internos correctos
  -  El router reescribe la dirección y el puerto de destino del datagrama antes de reenviarlo al host interno correcto

Así, NAT/NAPT permite que varios dispositivos dentro de un hogar utilicen Internet simultáneamente, compartiendo una única dirección IP pública sin interferir entre sí.

### Ejercicio 9

La RFC 1918 define un conjunto de rangos de direcciones IPv4 que no son globalmente únicos y no tienen significado global. Estas direcciones están reservadas para ser utilizadas exclusivamente en redes privadas o Intranets (redes autónomas sin conexión a Internet).
Los tres bloques de direcciones privadas reservados por la RFC 1918 son:
- **Clase A**: `10.0.0.0/8` (`10.0.0.0` – `10.255.255.255`)
- **Clases B**: `172.16.0.0/12` (`172.16.0.0` – `172.31.255.255`)
- **Clases C**: `192.168.0.0/16` (`192.168.0.0` – `192.168.255.255`)

Estas direcciones privadas deben ser filtradas por los routers de borde y no deberían pasar a la Internet. Las direcciones RFC 1918 solo tienen validez dentro de la red local, por lo que no pueden comunicarse con Internet sin un mecanismo de traducción. **NAT toma estas direcciones privadas y las convierte en una dirección IP pública, permitiendo que los hosts internos accedan a servicios externos**. Sin direcciones privadas, NAT no tendría sentido, y sin NAT, las direcciones RFC 1918 no podrían salir de la LAN.

### Ejercicio 10

Al verificar la configuración de red de mi computadora observé que posee una dirección asignada dentro de la red local. Luego, al acceder al sitio `cualesmiip.com`, aparece una dirección diferente, que corresponde a la dirección visible públicamente en Internet.
La diferencia entre ambas ocurre porque el router utiliza NAT para permitir que todos los dispositivos de la red interna accedan a Internet usando una única dirección pública. Por eso, cada dispositivo tiene una dirección privada dentro de la red doméstica, pero hacia el exterior todos comparten la misma dirección pública que muestra el sitio web.

### Ejercicio 11

PC-A (ss)  
Local Address:Port Peer Address:Port  
192.168.1.2:49273  <u>190.50.10.63:25</u>  
<u>192.168.1.2:49273</u>  190.50.10.63:25  
192.168.1.2:<u>50000</u> 190.50.10.81:8080  

### Ejercicio 12

- **Direcciones privadas**
  - todas las que empiecen con `10`
  - todas las que estén entre `172.16` a `172.31`
  - todas las que empiecen con `192.168`
- **Direcciones especiales**
  - Loopback: todas las que empiecen con `127`
  - direccion de red: todos los bits de host en 0
  - direccion de broadcast: todos los bits de host en 1
  - cuando el host no tiene dirección asociada: `0.0.0.0`
- **Rangos de las clases**
  - Clase A: `1` a `126`
  - Clase B: `128` a `191`
  - Clase C: `192` a `223`
  - Clase D: `224` a `239` (Multicast)
  - Clase E: `240` a `255` (Experimental)

- Bloques a considerar para asignar las redes faltantes en la topología:
  - `226.10.20.128/27`
    - Al ser de clase D, está reservada para multicast por lo que no es asignable
  - `200.30.55.64/26`
    - Al ser de clase C, podemos asignarla, es pública
  - `127.0.0.0/24`
    - Al empezar com 127, está reservada para Loopback por lo que no es asignable
  - `192.168.10.0/29`
    - Al ser de clase C, podemos asignarla, es privada
  - `224.10.0.128/27 `
    - Al ser de clase D, está reservada para multicast por lo que no es asignable
  - `224.10.0.64/26`
    - Al ser de clase D, está reservada para multicast por lo que no es asignable
  - `192.168.10.0/24`
    - Al ser de clase C, podemos asignarla, es privada 
  - `10.10.10.0/27`
    - Al ser de clase A, podemos asignarla, es privada 
- En este punto las redes asignables son:
  - `200.30.55.64/26`
    - Clase C
    - Pública
  - `192.168.10.0/29`
    - Clase C
    - Privada
  - `192.168.10.0/24`
    - Clase C
    - Privada
  - `10.10.10.0/27`
    - Clase A
    - Privada
- Análisis
  - RED A
    - 100 host
      - lo más cercano a 100 es 2^7=128, 32-7=25, por ende, mínimo /25
    - podemos utilzar `192.168.10.0/24`
  - RED B
    - 70 host
      - lo mas cercano a 70 es 2^7=128, 32-7=25, por ende, mínimo /25
    - podemos utilzar `192.168.10.0/24`
  - RED C
    - 14 hosts
      - lo más cercano a 14 es 2^4=16, 32-4=28, por ende, mínimo /28
    - como se nos especifica que la red debe ser pública, nuestra única opción es `200.30.55.64/26`
  - RED D
    - 16 host
      - lo más cercano a 16 es 2^5=32 (no nos sirve 2^4 porque tenemos que representar 16+2 hosts contando red y broadcast), 32-5=27, por ende, mínimo /27
    - como se nos especifica que la red debe ser pública, nuestra única opción es `200.30.55.64/26`
  - RED (RouterA - RouterB - RouterE)
    - tenemos tres routers, además de red y broadcast, es decir 5 host
      - lo más cercano a 5 es 2^3=8, 32-3=29, por ende, mínimo /29
    - como se nos especifica que la red debe ser privada, podemos usar `192.168.10.0/29` o `10.10.10.0/27`
  - RED (RouterC - RouterD)
    - tenemos dos routers, además de red y broadcast, es decir 4 host
      - lo más cercano a 4 es 2^2=4, 32-2=30, por ende, mínimo /30
    - como se nos especifica que la red debe ser privada, podemos usar `192.168.10.0/29` o `10.10.10.0/27`
- Asignaciones finales
  - Como necesitamos dos /25 tanto para RED A como para RED B subneteamos el bloque `192.168.10.0/24` quedando `192.168.10.0/25` para RED A y `192.168.20.128/25` para RED B
  - Como el único bloque público que tenemos es `200.30.55.64/26` y tanto RED C como RED D deben ser públicas, subneteamos el mismo, quedando `200.30.55.96/28` para RED C y `200.30.55.64/27` para RED D
  - Despues, solo nos quedan por asignar dos redes, a RED (RouterA - RouterB - RouterE), como necesita mínimo un /29 y debe ser privada subneteamos el bloque `10.10.10.0/27`, quedando `10.10.10.16/29`. Luego para RED (RouterC - RouterD), como necesita un /30 y debe ser privada, subneteamos la red que nos quedo `10.10.10.16/29`, quedano `10.10.10.24/30`

>[!note]
> el desarrollo del ejercicio y la asignación se encuentra en el archivo [Punto12.pdf](Punto12.pdf)

### Ejercicio 13

- PC-A: `192.168.10.2/25`
- PC-B: `192.168.10.3/25`
- PC-C: `192.168.10.4/25`
- PC-D: `192.168.10.5/25`
- RouterA (eth2): `192.168.10.1/25`
- RouterA (eth0): `10.10.10.17/29`
- RouterB (eth1): `10.10.10.18/29`
- RouterB (eth0): `192.168.10.129/25`
- PC-E: `192.168.10.130/25`
- RouterE (eth0): `10.10.10.19/29`
- RouterD (eth0): `10.10.10.26/30`  
- RouterD (eth1): `200.30.55.65/27`
- WebServer2: `200.30.55.66/27`
- DNSResolver: `200.30.55.67/27`
- RouterC (eth1): `10.10.10.25/30`
- RouterC (eth2): `200.30.55.97/28`
- MailServer: `200.30.55.98/28`
- WebServer1: `200.30.55.99/28`

### Ejercicio 14

- Tabla de ruteo de RouterE

  |Red Destino|Máscara|Next-Hop|Iface|
  |-----------|-------|--------|-----|
  |10.10.10.16|/29|-|eth0|
  |192.168.10.0|/25|10.10.10.17|eth0|
  |192.168.10.128|/25|10.10.10.18|eth0|
  |10.10.10.4|/30|-|eth3|
  |127.16.0.0|/24|10.10.10.6|eth3|
  |10.10.10.8|/30|10.10.10.6|eth3|
  |10.10.10.24|/30|10.10.10.14|eth1|
  |200.30.55.96|/28|10.10.10.14|eth1|
  |200.30.55.64|/27|10.10.10.2|eth2|
  |10.10.10.0|/30|-|eth2|
  |10.10.10.12|/30|-|eth1|
  |0.0.0.0|/0|10.10.10.6|eth3|

- Tabla de routeo de BORDER

  |Red Destino|Máscara|Next-Hop|Iface|
  |-----------|-------|--------|-----|
  |10.10.10.8|/30|-|eth0|
  |10.10.10.4|/30|-|eth2|
  |10.10.10.12|/30|10.10.10.5|eth2|
  |10.10.10.0|/30|10.10.10.5|eth2|
  |10.10.10.24|/30|10.10.10.9|eth0|
  |127.16.0.0|/24|-|eth1|
  |10.10.10.16|/29|10.10.10.5|eth2|
  |192.168.10.0|/25|10.10.10.5|eth2|
  |192.168.10.128|/25|10.10.10.5|eth2|
  |200.30.55.96|/28|10.10.10.9|eth0|
  |200.30.55.64|/27|10.10.10.9|eth0|
  |0.0.0.0|/0|127.16.0.1|eth1|
  
  - Despues de sumarizar
    |Red Destino|Máscara|Next-Hop|Iface|
    |-----------|-------|--------|-----|
    |10.10.10.8|/30|-|eth0|
    |10.10.10.4|/30|-|eth2|
    |10.10.10.12|/30|10.10.10.5|eth2|
    |10.10.10.0|/30|10.10.10.5|eth2|
    |10.10.10.24|/30|10.10.10.9|eth0|
    |127.16.0.0|/24|-|eth1|
    |10.10.10.16|/29|10.10.10.5|eth2|
    |192.168.10.0|/24|10.10.10.5|eth2|
    |200.30.55.96|/28|10.10.10.9|eth0|
    |200.30.55.64|/27|10.10.10.9|eth0|
    |0.0.0.0|/0|127.16.0.1|eth1|