# Primera Fecha 2023

### Ejercicio 1

#### Inciso a

1. Como `unlp.unlp.edu.ar` perdió conectividad a internet, cuando PC-A acceda mediante un navegador a `www.unlp.edu.ar`, el navegador realizará una consulta DNS para resolver el nombre de dominio `www.unlp.edu.ar` a una dirección IP. Dado que `unlp.unlp.edu.ar` no tiene conectividad a internet, no podrá comunicarse con los servidores DNS externos para resolver el nombre de dominio. Por lo tanto, la consulta DNS fallará y el navegador no podrá obtener la dirección IP correspondiente a `www.unlp.edu.ar`. Por lo tanto, no habra respuestas TCP o HTTP, ya que la conexión no podrá establecerse sin la resolución DNS previa. La respuesta será un error ICMP de destino inalcanzable.
2. No podría obtenerse de algún servidor DNS información autoritativa sobre el nombre `unlp.edu.ar`, ya que el servidor DNS  autoritativo para el dominio `unlp.edu.ar` es `unlp.unlp.edu.ar`, y como este servidor ha perdido conectividad a internet, no podrá responder a las consultas DNS para el dominio `unlp.edu.ar`. Por lo tanto, no se podrá obtener información autoritativa sobre el nombre `unlp.edu.ar` desde ningún otro servidor DNS.

#### Inciso b

1. Las configuraciones DNS y de Red son:
   - Red:
     - como tenemos la red `192.168.10.0` y asignadas las IPs `192.168.10.1`, `192.168.10.2` y `192.168.10.5`, podemos asignar a nuestro nuevo servidor DNS tanto la IP `192.168.10.3`, como la IP `192.168.10.4`
     - luego de asignar la IP `192.168.10.3` a nuestro servidor DNS, deberíamos conectarlo al switch Sw2
   - DNS:
     - Agregamos las siguientes entradas en los registros de zona del servidor DNS existente `unlp.unlp.edu.ar`:
       - unlp.edu.ar 300 IN NS ns1.unlp.edu.ar 
       - ns1.unlp.edu.ar 300 IN A `192.168.10.3`
2. Las configuraciones DNS y de Red son:
   - Red:
     - Le asignamos a nuestro nuevo servidor de correo saliente la IP `192.168.10.4`
     - conectamos a Sw2 el nuevo servidor de correo saliente 
   - DNS:
     - Agregamos en el servidor DNS existente `unlp.unlp.edu.ar` la siguiente entrada:
       - servidorsaliente 300 IN A `192.168.10.4`
3. Las configuraciones DNS y de Red son:
   - Red:
     - Le asignamos a mail-uno la IP `192.168.10.6`
     - Le asignamos a mail-dos la IP `192.168.10.7`
     - Conectamos ambos servidores de correo a Sw2
   - DNS:
     - Agregamos en el servidor DNS existente `unlp.unlp.edu.ar` las siguientes entradas:
       - unlp.edu.ar 300 IN MX 10 mail-uno.unlp.edu.ar.
       - mail-uno.unlp.edu.ar 300 IN A `192.168.10.6`
       - unlp.edu.ar 300 IN MX 20 mail-dos.unlp.edu.ar.
       - mail-dos.unlp.edu.ar 300 IN A `192.168.10.7`
4. Las configuraciones DNS y de Red son:
   - Red:
     - No hace falta ninguna configuración de red adicional
   - DNS: 
     - Agregamos en el servidor DNS existente `unlp.unlp.edu.ar` la siguiente entrada:
       - unlp.edu.ar 300 IN TXT "v=spf1 ip4:<IP_PUBLICA_ROUTER_A> -all"

### Ejercicio 2

#### Inciso a

- Utilizamos la dirección `127.0.0.1` de localhost de PC-A para que el servicio solo escuche conexiones locales y no esté expuesto a la red externa, así no será alcanzable por PC-B.
- `cliente.exe -s 127.0.0.1 -l 3306`
- `servidor.exe -l 127.0.0.1 -p 3306`

#### Inciso b y c

- En el inciso b, lo que está ocurriendo es que PC-B no está recibiendo el primer segmento de 30 bytes que envió PC-A, por eso PC-B envía el ACK correspondiente con el segmento anteriror. Y lo reenviará hasta que PC-A detecte un triple ACK duplicado y realice la retransmisión del segmento perdido.

>[!note]
> El grafico del inciso b y c están el archivo [Tcp.pdf](Tcp.pdf)

### Ejercicio 3

El ejercicio está resuelto en el archivo [Subnetting.pdf](Subnetting.pdf)

### Ejercicio 4

- Tabla de ruteo de RTR-E

| Destino        | Máscara        | Next-Hop       | Iface  |
|----------------|----------------|----------------|--------|
|180.0.0.8|/30|-|eth0|
|180.0.0.4|/30|-|eth1|
|190.10.3.192|/26|-|eth3|
|190.10.3.198|/26|-|eth2|
|190.10.3.0|/25|180.0.0.5|eth1|
|190.10.2.0|/24|180.0.0.10|eth1|
|180.0.0.0|/30|10.0.0.10|eth0|

### Ejercicio 5

#### Inciso a

- La cantidad de dominios de colisión en la red es 14
- La cantidad de dominios de broadcast en la red es 7

#### Inciso b

La PC-E recibirá la ARP request, ARP replay y la respuesta ICMP del Eco (echo reply) de tipo 0 y código 0. Estos mensajes los recibirá por estar conectado al mismo hub que la PC-D.

#### Inciso c

- Trama Ethernet cuando el mensaje sale de RTR-A
  |MAC_ORIGEN|MAC_DESTINO|
  |----------|-----------|
  |Mac_RTR_A_eth2|FF:FF:FF:FF:FF:FF|

- ARP Request cuando el mensaje sale de RTR-A
  |IP Origen| IP Destino |Mac Origen| Mac Destino|
  |---------|------------|----------|------------|
  |180.0.0.6|180.0.0.5|Mac_RTR_A_eth2|00:00:00:00:00:00|

### Ejercicio 6

a) Verdadero. El nodo no podrá navegar porque la configuración es inválida. Para una máscara `/26` (64 bits de host), la dirección `180.10.80.68` pertenece a la subred que va desde la `.64` hasta la `.127`. En este rango, la dirección 180.10.80.127 corresponde a la dirección de Broadcast, la cual es utilizada para comunicar a todos los hosts de la red y no puede ser asignada a la interfaz de un router para enrutar tráfico.
b) Falso. Aunque ambos estándares pertenecen a la misma familia IEEE, un dispositivo inalámbrico (802.11) y uno Ethernet (802.3) utilizan formatos de trama diferentes en la Capa de Enlace de Datos. Por lo tanto, no existe una comunicación "directa" entre ellos, es necesario un dispositivo intermediario (como un Access Point o Bridge) que realice la traducción de tramas para convertir las cabeceras de un formato al otro y permitir el flujo de datos.