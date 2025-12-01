# Práctica 7 - Capa de red, direccionamiento 

## Introducción

### Ejercicio 1

La función principal de la capa de red es el transporte de paquetes desde un host emisor a un host receptor. Un protocolo de la capa de red proporciona una comunicación lógica entre hosts.

#### Los servicios que presta son:
- **Enrutamiento (Routing)**: Determina la ruta o camino que deben seguir los paquetes a medida que fluyen de un emisor a un receptor. Los algoritmos de enrutamiento calculan las tablas de reenvío que se utilizan para transmitir los paquetes a través de la red.
- **Reenvío (Forwarding)**: Cuando un paquete llega a un enlace de entrada de un router, este debe pasarlo al enlace de salida apropiado. El reenvío implica la transferencia de un paquete desde un enlace de entrada a un enlace de salida dentro de un mismo router. Los routers llevan a cabo el reenvío examinando el valor de un campo en la cabecera del paquete entrante y utilizándolo para indexar su tabla de reenvío.
- **Configuración de la conexión**: Este proceso implica que los routers a lo largo de la ruta seleccionada negocien entre sí para configurar un estado antes de que los paquetes de datos puedan fluir.

#### PDU de la capa de red

La unidad de datos de protocolo (PDU) es el **datagrama**. En el host emisor, la capa de red crea un datagrama encapsulando el segmento de la capa de transporte. Específicamente, la capa de red añade la información de cabecera de la capa de red al segmento de la capa de transporte, creando así un datagrama de la capa de red. 

#### Dispositivo considerado solo de la capa de red

El dispositivo clave considerado como de la capa de red es el router. Los routers son dispositivos de conmutación de paquetes que basan su decisión de reenvío en el valor almacenado en el campo de la capa de red. El router se considera el dispositivo central de la capa de red porque su función principal es realizar el enrutamiento y el reenvío de datagramas, y no implementa protocolos de la capa de transporte ni de la capa de aplicación (excepto para propósitos de control)

### Ejercicio 2

Se considera a **IP** un protocolo de mejor esfuerzo (Best Effort) porque, aunque hace todo lo posible por entregar los paquetes o datagramas desde el host emisor al host receptor tan rápido como sea posible, no proporciona ninguna garantía sobre la entrega, el orden, la integridad de los datos o la temporización

### Ejercicio 3

| **Clase** | **Bits Red** | **Rango Primer Octeto** | **Nº de Redes** | **Hosts Máx. por Red** | **Dirección Red Ejemplo** |
| --------- | ------------ | ----------------------- | --------------- | ---------------------- | ------------------------- |
| **A** | 8 | 1-126 | **128** | **16M** | 10.0.0.0 |
| **B** | 16 | 128-191 | **16,384** | **64k** | 172.16.0.0 |
| **C** | 24 | 192-223 | **2,097,152** | **254** | 192.168.1.0 |

### Ejercicio 4

Una subred se define como una **red interconectada que une interfaces de host y de router que comparten el mismo prefijo de dirección IP**. Por ejemplo, si tres hosts y una interfaz de router están interconectados y todos tienen direcciones IP con el formato `223.1.1.xxx`, donde los 24 bits más a la izquierda son iguales, esa red forma una subred, y su dirección de subred sería `223.1.1.0/24`. Es **fundamental especificar siempre la máscara de subred** asociada (o la longitud del prefijo) porque esta **información define explícitamente qué parte de la dirección IP corresponde a la red y qué parte corresponde al host**. La notación `/x`, que a veces se denomina máscara de subred, indica que los `x` bits más a la izquierda de la dirección IP definen la dirección de subred o el prefijo de red. Sin esta especificación, un dispositivo no podría saber cuántos bits utilizar para identificar la red. Por ejemplo, en el caso de `223.1.1.0/24`, el `/24` indica que los primeros 24 bits (223.1.1) identifican la subred.
El prefijo de red que la máscara de subred define es crucial para el reenvío de paquetes por parte de los routers. Los routers externos a una organización solo necesitan considerar estos x primeros bits del prefijo para reenviar un datagrama hacia el destino dentro de esa organización. Esto permite que **una única entrada en la tabla de reenvío sea suficiente para dirigir paquetes a cualquier destino dentro de esa organización**, reduciendo considerablemente el tamaño de las tablas de reenvío. Además, cuando una dirección de destino coincide con varias entradas en la tabla de reenvío, el router aplica la regla de **coincidencia con el prefijo más largo** (Longest Prefix Match). La máscara de subred (/x) es **esencial para determinar la longitud exacta del prefijo con el que se está buscando la coincidencia**.

### Ejercicio 5

La principal finalidad del campo Protocolo es facilitar las funciones de multiplexación y desmultiplexación (mux/demux) de protocolos superiores. El número de protocolo es el elemento que enlaza las capas de red y de transporte.  Cuando un datagrama IP llega a su destino final, el valor contenido en este campo de 1 byte indica el protocolo específico de la capa de transporte al que deben pasarse los datos encapsulados dentro de ese datagrama IP. 
El campo Protocolo en la cabecera IP desempeña un papel similar al del campo que almacena el número de puerto de un segmento de la capa de transporte. El campo Protocolo enlaza la capa de red con la capa de transporte y los campos de número de puerto enlaza la capa de transporte con la capa de aplicación, en general, ambos conjuntos de campos (Protocolo y Números de Puerto) sirven para enlazar un protocolo de una capa con un protocolo de la capa inmediatamente superior. Permiten que los datos sean dirigidos al protocolo o proceso correcto en la siguiente capa superior

## División en subredes

### Ejercicio 6

- `172.16.58.223/26`
  - **Clase**: teniendo en cuenta que el primer octecto (172) se encuentra en el rango de 128-191, es de clase B.
  - **Dirección de la subred**: 
    - Como es /26 aplicamos la máscara de subred con los primeros 26 bits de la dirección IP en 1 y el resto en 0:
    - 10101100.00010000.00111010.11011111 AND 11111111.11111111.11111111.11000000 = 1010110.00010000.00111010.11000000 = `172.16.58.192/26` 
  - **Cantidad máxima de hosts**: La cantidad máxima de hosts que se pueden representar son 2^(32-26) - 2 = 62 hosts, se restan 2 por la dirección de red y la de broadcast.
  - **Dirección de broadcast**: La dirección de broadcast es `172.16.58.255`, ya que es la última dirección de la subred.
  - **Rango de direcciones IP válidas dentro de la subred**: El rango de direcciones IP válidas dentro de la subred es `172.16.58.193` a `172.16.58.254`.
- `163.10.5.49/27`  
  - **Clase**: teniendo en cuenta que el primer octecto (163) se encuentra en el rango de 128-191, es de clase B.
  - **Dirección de la subred**: 
    - Como es /27 aplicamos la máscara de subred con los primeros 27 bits de la dirección IP en 1 y el resto en 0:
    - 10100011.00001010.00000101.00110001 AND 11111111.11111111.11111111.11100000 = 10100011.00001010.00000101.00100000 = `163.10.5.32/27`
  - **Cantidad máxima de hosts**: La cantidad máxima de hosts que se pueden representar son 2^(32-27) - 2 = 30 hosts, se restan 2 por la dirección de red y la de broadcast 
  - **Dirección de broadcast**: La ddirección de broadcast es `163.10.5.255`, ya que es la última dirección de la subred.
  - **Rango de direcciones IP válidas dentro de la subred**: El rango de direcciones IP válidas dentro de la subred es `163.10.5.33` a `163.10.5.254`
- `128.10.1.0/23`
  - **Clase**: teniendo en cuenta que el priner octeto (128) se encuentra en el rango de 128-191, es de clase B.
  - **Dirección de la subred**: 
    - Como es /23 aplicamos la máscara de sbred con los primeros 23 bits de la ddirección IP en 1 y el resto en 0:
    - 10000000.00001010.00000001.00000000 AND 11111111.11111111.11111110.00000000 = 10000000.00001010.00000000.00000000 = `128.10.0.0/23`
  - **Cantidad máxima de hosts**: La cantidad máxima de hosts que se pueden representar son 2^(32-23) - 2 = 510 hosts, se restan 2 por la dirección de red y la de broadcast
  - **Dirección de broadcast**: La dirección de broadcast es `128.10.0.255`, ya que es la última dirección de la subred
  - **Rango de direcciones IP válidas dentro de la subred**: El rango de direcciones IP válidas dentro de la subred es `128.10.0.1` a `128.10.0.254` 
- `10.1.0.0/24`
  - **Clase**: teniendo en cuenta que el primer octeto (10) se encuentra en el rango de 1-126, es de clase A.
  - **Dirección de la subred**: 
    - Como es /24 aplicamos la máscara de subred con los primeros 24 bits de la dirección IP en 1 y el resto en 0:
    - 00001010.00000001.00000000.00000000 AND 11111111.11111111.11111111.00000000 = 00001010.00000001.00000000.00000000 = `10.1.0.0/24`
  - **Cantidad máxima de hosts**: La cantidad máxima de hosts que se pueden representar son 2^(32-24) - 2 = 254 hosts, se restan 2 por la dirección de red y la de broadcast.
  - **Dirección de broadcast**: La dirección de broadcast es `10.1.0.255`, ya que es la última dirección de la subred.
  - **Rango de direcciones IP válidas dentro de la subred**: El rango de direcciones IP válidas dentro de la subred es `10.1.0.1` a `10.1.0.254`.
- `8.40.11.179/12`
  - **Clase**: teniendo en cuenta que el primer octeto (8) se encuentra en el rango de 1-126, es de clase A.
  - **Dirección de la subred**: 
    - Como es /12 aplicamos la máscara de subred con los primeros 12 bits de la dirección IP en 1 y el resto en 0:
    - 00001000.00101000.00001011.10110011 AND 11111111.11110000.00000000.00000000 = 00001000.00100000.00000000.00000000 = `8.32.0.0/12`
  - **Cantidad máxima de hosts**: La cantidad máxima de hosts que se pueden representar son 2^(32-12) - 2 = 1048574 hosts, se restan 2 por la dirección de red y la de broadcast.
  - **Dirección de broadcast**: La dirección de broadcast es `8.32.0.255`, ya que es la última dirección de la subred.
  - **Rango de direcciones IP válidas dentro de la subred**: El rango de direcciones IP válidas dentro de la subred es `8.32.0.1` a `8.47.0.254`.

### Ejercicio 7

- Dada la dirección IP `128.50.10.0`
  - **Dirección de red o de host?**
    - Para determinar si es una dirección de red o de host, necesitamos conocer la máscara de subred, como no la conocemos, asociamos la máscara por defecto que le corresponda a la clase que pertenezca la dirección. En este caso, el primer octeto (128) se encuentra en el rango de 128-191, por lo que es de clase B, y su máscara por defecto es `/16`, es decir los primeros 2 octetos (16 bits) son de red y los otros 2 octetos (16 bits) son de host. Por lo tanto, la dirección es de host, ya que si fuese de red, los últimos 2 octetos (host) deberían ser 0.
  - **Clase y máscara de clase**
    - Como se explicó en el punto anterior, es de clase B y su máscara por defecto es `/16`.
  - **Cantidad de hosts posibles**
    - La cantidad máxima de hosts que se pueden representar son 2^(32-16) = 65536 hosts, 65534 si descontamos la dirección de red y la de broadcast.
- Si se necesitan crear, al menos, 513 subredes:
  - **Máscara necesaria**:
    - Necesitamos saber cuántos bits (n) tomar de la parte de host para crear al menos 513 subredes, teniendo en cuenta que el número de subredes asignables está dado por 2^n, necesitamos que 2^n >= 513.
      - 2^9 = 512 < 513
      - 2^10 = 1024 >= 513
    - Necesitamos tomar 10 bits de la parte de host para crear al menos 513 subredes.
    - Por lo tanto, la máscara necesaria sera `16 + 10 = /26`. A la máscara base le sumamos los 10 bits que necesitamos para representar las subredes.
  - **Cantidad de redes asignables**:
    - Podemos representar 2^10 = 1024 subredes.
  - **Cantidad de hosts por subred**:
    - La cantidad máxima de hosts que se pueden representar por subred son 2^(32-26) -2 = 62 hosts, se restan 2 por la dirección de red y la de broadcast.
  - **Dirección de la subred 710**:
    - Primero convertimoos 710 a binario de 10 bits: `1011000110`.
    - Ahora aplicamos un OR entre la dirección IP inicial y la representación binaria de la subred 710, teniendo en cuenta que los primeros 16 bits son de red y los siguientes 10 bits son de subred:
      - `128.50.[00001010.00000000] OR [1011000110.000000] = 128.50.10110001.00000000`
      - En decimal sería `128.50.177.0`
  - **Dirección de broadcast de la subred 710**:
    - La dirección de broadcast se obtiene poniendo todos los bits de host a 1.
    - Como la máscara es `/26`, los primeros 26 bits son de red y los últimos 6 bits son de host.
      - `128.50.10110001.10111111` sería la dirección de broadcast en binario.
      - En decimal sería `128.50.177.191`

### Ejercicio 8

#### Inciso a

- Dada la dirección IP `195.200.45.0/24`
  - **Máscara para definir al menos 9 subredes**
    - Necesitamos saber cuántos bits (n) tomar de la parte de host para crear al menos 9 subredes, teniendo en cuenta que el número de subredes asignables está dado por 2^n, necesitamos que 2^n >= 9.
      - 2^3 = 8 < 9
      - 2^4 = 16 >= 9
    - Necesitamos tomar 4 bits de la parte de host para crear al menos 9 subredes
    - Por lo tanto, la máscara necesaria será `24 + 4 = /28`. A la máscara base le sumamos los 10 bits que necesitamos para representar las subredes
  - **Dirección de subred de las primeras 9 subredes**
  - **Dirección de broadcast y rango de direcciones asignables de una de las 9 subredes**

#### Inciso b

- Dirección  de subred de las primeras 9 subredes
  - `[195.200.45.0000]0000/28` = `195.200.45.0/28`
  - `[195.200.45.0001]0000/28` = `195.200.45.16/28`
  - `[195.200.45.0010]0000/28` = `195.200.45.32/28`
  - `[195.200.45.0011]0000/28` = `195.200.45.48/28`
  - `[195.200.45.0100]0000/28` = `195.200.45.64/28`
  - `[195.200.45.0101]0000/28` = `195.200.45.80/28`
  - `[195.200.45.0110]0000/28` = `195.200.45.96/28`
  - `[195.200.45.0111]0000/28` = `195.200.45.112/28`
  - `[195.200.45.1000]0000/28` = `195.200.45.128/28`

#### Inciso c

- si seleccionamos por ejemplo la subred `195.200.45.0/28`
  - **Dirección de broadcast**: se obtiene con todos los bit de host en 1
    - `195.200.45.00001111` = `195.200.45.15`
  - **Rango de direcciones asignables**: son las direcciones entre la dirección de subred y la dirección de broadcast
    - desde `195.200.45.1` hasta `195.200.45.14`

### Ejercicio 9

#### Inciso a

- `191.26.145.1/24`: Correcta
- `191.26.145.20/24`: Correcta
- `172.26.22.1/30`: Correcta
- `172.26.22.3/30`: Correcta
- `172.17.10.14/28`: Correcta
- `172.17.10.17/28`: Incorrecta, ya que en una subred /28 solo hay 16 direcciones (14 utilizables para hosts), por lo que la dirección 17 no pertenece a esa subred.
  - Correxión: `172.17.10.1/28`
- `192.168.5.1/24`: Correcta
- `10.0.10.1/24`: Correcta

#### Inciso b

En la red 10.0.10.0/24, se tomaron para hacer subredes 16 bits. Al ser una IP de clase A, la máscara por defecto es /8 y como ahora es una /24, se tomaron 16 bits de la parte de host para hacer subredes. Por lo tanto, el número de subredes que se pueden crear es 2^16 = 65536 subredes.

#### Inciso c

En cuanto a la privacidad de las redes:
- `191.26.145.0/24`: Pública
- `172.26.22.0/30`: Privada, ya que, las direcciones IP privadas en clase B van desde `172.16.0.0` hasta `172.31.255.255`
- `172.17.10.0/28`: Privada, ya que, las direcciones IP privadas en clase B van desde `172.16.0.0` hasta `172.31.255.255`
- `192.168.5.0/24`: Privada, ya que, las direcciones IP privadas en clase C van desde `192.168.0.0` hasta `192.168.255.255`
- `10.0.10.0/24`: Privada, ya que, las direcciones IP privadas en clase A van desde `10.0.0.0` hasta `10.255.255.255`

## CIDR

### Ejercicio 10

CIDR significa **Classless Interdomain Routing** (Enrutamiento entre Dominios sin Clase), es una estrategia de asignación de direcciones que elimina las clases (Classless), y determina que la parte de redd de una dirección IP puede tener longitudes arbitrarias. En el esquema CIDR, una dirección IP se expresa en notación decimal con punto como `a.c.c.d/x`, donde el valor x (la longitud del prefijo) indica el número de bits más significativos (de izquierda a derecha) que constituyen la parte de red o prefijo de la dirección IP.
CIDR es útil por varias razones:
- **Mejor uso del espacio de direcciones**: al no tener clases no genera el ineficiente uso del espacio de direcciones asignado que estas hacían. Ya que, por ejemplo, una red de clase B (/16) podríá soportar hasta 63534 host, lo cual era demasiado grande para muchas organizaciones. CIDR resolvió el problma permitiendo que la parte de red de una dirección IP tuviera una longitud arbitraria de bits
- **Agregación de direcciones (supernetting) y reducción de tablas de ruteo**: permite agrupar bloques de direcciones de forma contigua. Esta capacidad de emplear un mismo prefijo para anunciar múltiples redes se denomina agregación de direcciones. La agregación es crucial porque reduce considerablemente el tamaño de la tabla de ruteo de los routers. Por ejemplo, un router externo a una organización solo necesita considerar los primeros x bits del prefijo. Una única entrada en la tabla de reenvío con el formato a.b.c.d/x es suficiente para dirigir paquetes a cualquier destino dentro de la organización
- **Soporte del algoritmo de coincidencia con el prefijo más largo**: Dado que las longitudes de los prefijos pueden variar, los routers utilizan la regla de coincidencia con el prefijo más largo (Longest Prefix Match) para tomar decisiones de reenvío. Si una dirección de destino coincide con varias entradas en la tabla de reenvío (por ejemplo, con un prefijo agregado y otro más específico), el router selecciona la entrada que tenga el prefijo más largo (más específico) para reenviar el paquete.

### Ejercicio 11

- Dadas las siguientes redes:
  - a) `198.10.1.0/24`
  - b) `198.10.0.0/24`
  - c) `198.10.3.0/24`
  - d) `198.10.2.0/24`
- Si un router aplica CIDR las publicaría de la siguiente manera:
  - primero identificamos los bits en común
  - a) [11000110.00001010.000000]01.00000000
  - b) [11000110.00001010.000000]00.00000000
  - c) [11000110.00001010.000000]11.00000000
  - d) [11000110.00001010.000000]10.00000000
  - vemos que los primeros 22 bits son iguales, por lo que la red resultante sería `198.10.0.0/22`

### Ejercicio 12

- Dadas los siguientes bloques CIDR, las redes involucradas son
  - `200.56.168.0/21`
    - en binario: 11001000.00111000.10101000.00000000
    - los primeros 21 bits son de red, por lo que los últimos 11 bits son de host [11001000.00111000.10101]000.00000000
    - el mínimo sera como está la red actualmente con los últimos 11 bits en 0 y el máximo con los últimos 11 bits en 1
    - por ende, el rango de redes involucradas va desde `200.56.168.0` hasta `200.56.175.255`
  - `195.24.0.0/13`
    - en binario: 11000011.00011000.00000000.00000000
    - los primeros 13 bits son de red, por lo que los últimos 19 bits son de host [11000011.00011]000.00000000.00000000
    - el mínimo sera como está la red actualmente con los últimos 19 bits en 0 y el máximo con los últimos 19 bits en 1
    - por ende, el rango de redes involucradas va desde `195.24.0.0` hasta `195.31.255.255`
  - `195.24/13`
    - es lo mismo que el punto anterior, por lo que el rango de redes involucradas va desde `195.24.0.0` hasta `195.31.255.255`

### Ejercicio 13

- El bloque CIDR `128.0.0.0/2` es exactamente lo mismo que listar todas las direcciones de red de clase B. El primer octeto de las redes de clase B comienza con los bits `10`. El bloque CIDR `128.0.0.0/2` indica que los primeros 2 bits son fijos. Al convertir 128 a binario (`10000000`), vemos que los dos primeros bits son precisamente `10`. Por lo tanto, este bloque abarca desde la dirección `128.0.0.0` hasta la `191.255.255.255`, cubriendo la totalidad del espacio de direcciones de la Clase B.
- El bloque CIDR que agrupa todas las redes de clase A es `0.0.0.0/1`, ya que las direcciones de clase A comienzan con un bit `0`. Esto abarca desde `0.0.0.0` hasta `127.255.255.255`, cubriendo todas las direcciones de clase A.

## VSLM

### Ejercicio 14

VLSM (Variable Length Subnet Mask) significa **Máscara de Subred de Longitud Variable**. Es una técnica de direccionamiento IP que permite usar diferentes máscaras de subred dentro de una misma red base, adaptando el tamaño de cada subred a la cantidad real de hosts que necesita. Antes del VLSM, todas las subredes dentro de una red debían tener la misma máscara (por ejemplo, todas /24). Esto generaba desperdicio de direcciones IP, ya que una red con pocos hosts recibía el mismo número de direcciones que una red grande. Con VLSM, se pueden crear subredes más pequeñas o más grandes según sea necesario, optimizando el uso de direcciones IP.

### Ejercicio 15

- El proceso generalmente implica:
  - Calcular la máscara para la/s subred/es de mayor cantidad de hosts
  - Escribir la subred asignada en binario y marcar donde finaliza la máscara
  - Desplazar el límite de subred hacia la derecha según la cantidad de bits necesarios para la siguiente subred
  - Calcular las diferentes combinaciones posbiles de subredes en base a los bits que nos desplazamos hacia la derecha 

### Ejercicio 16

#### Inciso a

No es posible asignar las subredes correspondientes a la topología utilizando subnetting sin VLSM. Debido a que la subred que más hosts necesita es la subred C con 1530 hosts. Por lo tanto, necesitamos una máscara que permita al menos 1530 hosts, lo que requiere 11 bits para los hosts (2^11 = 2048). La máscara necesaria sería /21 (32 - 11 = 21), lo que me permite definir 4 subredes con 2048 direcciones cada una (2046 utilizables para hosts), por lo cual no es posible asignar las 6 direcciones necesarias para cada red de la topología.
- En cada caso, si se pudiese asignar la red en /21 se desperdiciarán (en cada caso no contamos la dirección de red y la de broadcast, nos quedan 2046 direcciones utilizables por subred):
  - RED A: tiene 128 hosts, con /21 se desperdician 2046 - 128 = 1918 direcciones.
  - RED B: tiene 20 hosts, con /21 se desperdician 2046 - 20 = 2026 direcciones.
  - RED C: tiene 1530 hosts, con /21 se desperdician 2046 - 1530 = 516 direcciones.
  - RED D: tiene 7 hosts, con /21 se desperdician 2046 - 7 = 2039 direcciones.
  - RED R1 - R2: tiene 2 hosts (interfaces de los routers), con /21 se desperdician 2046 - 2 = 2044 direcciones.
  - RED R2 - R3: tiene 2 hosts (interfaces de los routers), con /21 se desperdician 2046 - 2 = 2044 direcciones.

#### Inciso b

- Direcciones asignadas a las redes de la topología:
  - RED A: `205.10.200.0/24`
  - RED B: `205.10.201.0/27`
  - RED C: `205.10.192.0/21`
  - RED D: `205.10.201.32/28`
  - RED (ROUTER 1 - ROUTER 2): `205.10.201.48/30`
  - RED (ROUTER 2 - ROUTER 3): `205.10.201.52/30`

#### Inciso c

- Direcciones que quedaron libres:
  - `205.10.208.0/20`
  - `205.10.204.0/22`
  - `205.10.202.0/23`
  - `205.10.201.128/25`
  - `205.10.201.64/26`
  - `205.10.201.56/29`

#### Inciso d

- Direcciones de las interfaces:
  - ROUTER 1 - RED A: `205.10.200.1/24`
  - ROUTER 1 - RED B: `205.10.201.1/27`
  - ROUTER 1 - RED C: `205.10.192.1/21`
  - ROUTER 1 - ROUTER 2: `205.10.201.49/30`
  - ROUTER 2 - RED D: `205.10.201.33/28`
  - ROUTER 2 - ROUTER 1: `205.10.201.50/30`
  - ROUTER 2 - ROUTER 3: `205.10.201.53/30`
  - ROUTER 3 - ROUTER 2: `205.10.201.54/30`

>[!note]NOTA
>El proceso de obtención de las direcciones esta detallado en el archivo [Punto16.pdf](Punto16.pdf)

### Ejercicio 17

- Direcciones asignadas a las redes y hosts de la topología:
  - RED A: `200.100.8.0/24`
    - n10: `200.100.8.1/24`
  - RED B: `200.100.11.128/26`
    - n14: `200.100.11.129/26`
    - n15: `200.100.11.130/26`
    - n16: `200.100.11.132/26`
  - RED X: `200.100.9.0/25`
    - n11: `200.100.9.1/25`
    - n12: `200.100.9.2/25`
  - RED Y: `200.100.9.128/25`
    - n13: `200.100.9.129/25`
  - RED (ROUTER n1 - ROUTER n2): `200.100.11.236/30`
  - RED (ROUTER n1 - ROUTER n3): `200.100.11.252/30`
  - RED (ROUTER n1 - ROUTER n4): `200.100.11.240/30`
  - RED (ROUTER n3 - ROUTER n4): `200.100.11.248/30`
  - RED (ROUTER n2 - ROUTER n4): `200.100.11.244/30`

- Direccciones asignadas a las interfaces:
  - ROUTER n1 <- RED A: `200.100.8.2/24`
  - ROUTER n1 <- ROUTER n2: `200.100.11.237/30`
  - ROUTER n1 <- ROUTER n3: `200.100.11.253/30`
  - ROUTER n1 <- ROUTER n4: `200.100.11.241/30`
  - ROUTER n2 <- RED X: `200.100.9.4/25`
  - ROUTER n2 <- ROUTER n1: `200.100.11.238/30`
  - ROUTER n2 <- ROUTER n4: `200.100.11.245/30`
  - ROUTER n3 <- ROUTER n1: `200.100.11.254/30`
  - ROUTER n3 <- ROUTER n4: `200.100.11.249/30`
  - ROUTER n4 <- RED B: `200.100.11.136/26`
  - ROUTER n4 <- RED Y: `200.100.9.130/25`
  - ROUTER n4 <- ROUTER n1: `200.100.11.242/30`
  - ROUTER n4 <- ROUTER n2: `200.100.11.246/30`
  - ROUTER n4 <- ROUTER n3: `200.100.11.250/30`

**NOTA**: El proceso de obtención de las direcciones esta detallado en el archivo [Punto17.pdf](Punto17.pdf)

### Ejercicio 18

## ICMP y Configuraciones de IP

### Ejercicio 19

ICMP (Internet Control Message Protocol) es el **protocolo de mensajes de control de internet**, utilizado para el control y diagnóstico de las redes IP. A nivel arquitectónico, ICMP se encuentra justo encima de IP. Esto significa que los mensajes ICMP no transportan datos de usuario (por lo que no es un protocolo de transporte), sino que se transportan como carga útil dentro de los datagramas IP. La función principal de ICMP es permitir a los hosts y routers intercambiar información acerca de la capa de red. Dado que el Protocolo IP ofrece un servicio de "mejor esfuerzo" y, por lo tanto, no es confiable, carece de mecanismos de control incorporados. ICMP actúa como el protocolo auxiliar que brinda un "feedback" (retroalimentación) para resolver o diagnosticar problemas dentro de la red. ICMP no agrega confiabilidad a IP, ya que si un mensaje ICMP se pierde, no hay mecanismos para su recuperación.

#### Inciso a

- **Tipo (type)**: Indica qué clase de mensaje es. Define el propósito general (por ejemplo, solicitud de eco, respuesta, destino inalcanzable, etc.)
- **Código (code)**: Da más detalle dentro de ese tipo. Es una especie de "subtipo" que especifica la causa o variante del mensaje.
- ejemplos
  | Tipo | Código | Significado                                                                      |
  | :--: | :----: | :------------------------------------------------------------------------------- |
  |   0  |    0   | Echo Reply (respuesta al ping)                                                   |
  |   3  |  0 – 15  | Destination Unreachable (varios códigos según la causa: red, host, puerto, etc.) |
  |   5  |   0 – 3  | Redirect (redirigir el tráfico a otra ruta)                                      |
  |   8  |    0   | Echo Request (solicitud de ping)                                                 |
  |  11  |   0 – 1  | Time Exceeded (TTL expirado o reensamblado de fragmentos falló)                  |

- En base a lo anterior podemos determinar que:
  - para una solicitud de ping (ICMP Echo Request), el valor del campo Tipo es 8 y el valor del campo Código es 0.
  - para una respuesta de ping (ICMP Echo Reply), el valor del campo Tipo es 0 y el valor del campo Código es 0.

#### Inciso b

Los comandos `traceroute` (Linux) y `tracert` (Windows) se utilizan para **descubrir la ruta que siguen los paquetes desde un origen hasta un destino**. En lugar de responder directamente con una solicitud de eco (ping), `traceroute`/`tracert` muestra cada router intermedio (o salto, “hop”) por donde pasa el paquete en su camino hacia el destino final. Estos comandos aprovechan el campo TTL (Time To Live) del encabezado IP y los mensajes ICMP de tipo “tiempo excedido”.
- manipulación del campo TTL:
  - El campo TTL (Time To Live) es un campo de 8 bits en el encabezado IP, cuyo propósito es evitar bucles infinitos en la red. Cada vez que un paquete pasa por un router, el T**TL se reduce en 1, y si llega a 0, el router descarta el paquete** y envía un mensaje ICMP de tipo 11 y código 0 (tiempo excedido) al remitente.
  - `traceroute`/`tracert` manipula el campo TTL **enviando una serie de paquetes con valores de TTL crecientes, empezando en 1 y aumentando de uno en uno**. Cada router que reenvía el paquete reduce el TTL en 1, y cuando este llega a 0, el router descarta el paquete y envía un mensaje ICMP “Time Exceeded” (tipo 11, código 0) al origen. De esta manera, el programa recibe una respuesta de cada router intermedio y puede identificar la ruta completa hasta el destino, que responde finalmente con un mensaje ICMP “Echo Reply” (tipo 0, código 0) o “Port Unreachable” (tipo 3, código 0-15) cuando el paquete logra llegar

#### Inciso c

- `traceroute/tracert www.nasa.gov` reatreará la ruta desde el host origen hasta el servidor web de la NASA, mostrando cada salto intermedio (router), su dirección IP o nombre de host y el tiempo de respuesta.
- Para evitar la resolución de nombres DNS de los routers intermedios, se puede usar la opción `-n` en `traceroute` (Linux) o `-d` en `tracert` (Windows). Esto hará que el comando muestre solo las direcciones IP.
- Si se ven como respuesta asteriscos (*), significa que no se recibió respuesta ICMP del router en ese salto dentro del tiempo límite establecido. Esto puede deberse a varias razones:
  - El router está configurado para no responder a solicitudes ICMP (por razones de seguridad).
  - El paquete ICMP fue bloqueado por un firewall.
  - Hubo congestión en la red o el router estaba demasiado ocupado para responder a tiempo.

#### Inciso d

- Obtengo los NS de unlp.edu.ar con `dig unlp.edu.ar ns`
  ```bash
    ...
    ;; ANSWER SECTION:
    unlp.edu.ar.            7200    IN      NS      anubis.unlp.edu.ar.
    unlp.edu.ar.            7200    IN      NS      hostex.unlp.edu.ar.
    unlp.edu.ar.            7200    IN      NS      ns1.riu.edu.ar.
    unlp.edu.ar.            7200    IN      NS      unlp.unlp.edu.ar.
    ...
  ```
- Ahora luego de un `traceroute` a cada uno de ellos se determina que:
  - Al realizar traceroute a los cuatro servidores de nombre del dominio `unlp.edu.ar`, se observa que tres de ellos (`anubis.unlp.edu.ar`, `hostex.unlp.edu.ar` y `unlp.unlp.edu.ar`) siguen rutas muy similares, salen por la red del proveedor local (Telecom) y luego acceden a redes académicas argentinas antes de llegar a la UNLP. En cambio, `ns1.riu.edu.ar` toma un camino diferente, después de los primeros hops dentro de Telecom, ingresa directamente a la red de la RIU (Red Interuniversitaria) a través del host `rgda2-dc-sol.riu.edu.ar`, sin pasar por las mismas redes académicas intermedias que los otros servidores. Por lo tanto, el único servidor que toma un camino distinto es `ns1.riu.edu.ar`.

### Ejercicio 20

El bloque `127.0.0.0/8` está clasificado como una dirección IP especial que se utiliza para la función de **loopback** (bucle de retorno) y es unicast. El propósito de este bloque es que las direcciones dirijan el tráfico hacia el propio host local. Se utiliza para probar la pila de protocolos de red sin necesidad de salir a la red física.

#### Inciso a y b

El comando ping se utiliza para probar la conectividad IP entre dos hosts. Cuando un host recibe un mensaje ICMP de Solicitud de eco (Echo Request), debe responder copiando el contenido con un mensaje de Respuesta de eco (Echo Reply). Dado que las direcciones en el bloque 127.0.0.0/8 son direcciones de loopback, el tráfico dirigido a ellas nunca sale de la máquina local que ejecuta el comando.
- No importan los números siguientes a 127.*, siempre responderá la máquina local y nunca se envían paquetes a la red real.
  ```bash
  $ ping 127.0.0.1

  Haciendo ping a 127.0.0.1 con 32 bytes de datos:
  Respuesta desde 127.0.0.1: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.0.1: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.0.1: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.0.1: bytes=32 tiempo<1m TTL=128

  Estadísticas de ping para 127.0.0.1:
      Paquetes: enviados = 4, recibidos = 4, perdidos = 0
      (0% perdidos),
  Tiempos aproximados de ida y vuelta en milisegundos:
      Mínimo = 0ms, Máximo = 0ms, Media = 0ms
  ```
  ```bash
  $ ping 127.0.54.43

  Haciendo ping a 127.0.54.43 con 32 bytes de datos:
  Respuesta desde 127.0.54.43: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.54.43: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.54.43: bytes=32 tiempo<1m TTL=128
  Respuesta desde 127.0.54.43: bytes=32 tiempo<1m TTL=128

  Estadísticas de ping para 127.0.54.43:
      Paquetes: enviados = 4, recibidos = 4, perdidos = 0
      (0% perdidos),
  Tiempos aproximados de ida y vuelta en milisegundos:
      Mínimo = 0ms, Máximo = 0ms, Media = 0ms
  ```

### Ejercicio 21

#### Comandos `ifconfig` y `route`

- El comando `ifconfig` (Interface Configuration) se utiliza para configurar interfaces de red, permitiendo asignar direcciones IP, activar o desactivar interfaces y ver estadísticas de red.
- El comando `route` se emplea para manipular la tabla de ruteo del host o router. La función principal del ruteo es seleccionar la interfaz de salida y el próximo salto para un paquete.
- Estos comandos se encuentran actualmente obsoletos y pueden reemplazarse por:
  - `ip addr` en lugar de `ifconfig`
  - `ip route` en lugar de `route`


  
