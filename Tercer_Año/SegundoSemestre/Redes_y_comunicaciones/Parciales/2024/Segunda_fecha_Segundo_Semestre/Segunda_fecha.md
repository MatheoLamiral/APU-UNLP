# Segunda fecha - Segundo Semestre 2024

### Ejercicio 1

#### Inciso a

- Ida: `Router-A -> ISP -> Internet`
- Vuelta: `Internet -> ISP -> Router-A`

#### Inciso b

- Ida: `Router-B -> Router-C -> Router-A`
- Vuelta: De `Router-A` intentará ir al next-hop con IP `10.10.10.11` ya que tiene la entrada para RED B en su tabla de ruteo, pero como no existe la IP del next-hop, la comunicación fallará y no llegará a destino

#### Inciso c

- La comunicaión falla, ya que como `Router-B` no tiene una entrada en su tabla para la red `8.8.8.8`, saldrá por su defalut gateway hacia `Router-C`, que tampoco tiene una entrada para la red `8.8.8.8` y tiene como default gateway a `Router-B`, lo que generará un bucle entre `Router-B` y `Router-C` hasta que se agote el tiempo de vida (TTL) de los paquetes.

#### Inciso d

- Ida: `Router-C -> Router-B`
- Vuelta: `Router-B -> Router-C`

### Ejercicio 2

### Ejercicio 3

#### Inciso a

- Tabla CAM de `SWT-01` despues de un ping de PC-A hacia PC-B:

|MAC Origen|Iface|
|----------|-----|
|MAC_PCA_ETH0|e0|
|MAC_PCB_ETH0|e2|

#### Inciso b

- Tabla CAM de `SWT-01` despues de un ping de PC-A hacia PC-C:

|MAC Origen|Iface|
|----------|-----|
|MAC_PCA_ETH0|e0|
|MAC_PCB_ETH0|e2|
|MAC_PCC_ETH0|e2|

#### Inciso C

- Tabla CAM de `SWT-01` despues de un ping de PC-B hacia Web:

|MAC Origen|Iface|
|----------|-----|
|MAC_PCA_ETH0|e0|
|MAC_PCB_ETH0|e2|
|MAC_PCC_ETH0|e2|
|MAC_ROUTER01_ETH0|e1|

>[!note]
> No se guarda la MAC de Web, ya que no pertenece a la misma red que SWT-01

### Ejercicio 4

- PC-A hacia router
  - Trama Ethernet
      |MAC Origen|MAC Destino|
      |----------|-----------|
      |MAC_PCA_ETH0|FF:FF:FF:FF:FF:FF|
  - ARP Request
      |IP Origen|MAC Origen|IP Destino|MAC Destino|
      |---------|----------|----------|-----------|
      |IP_PCA_ETH0|MAC_PCA_ETH0|IP_ROUTER01_ETH0|00:00:00:00:00:00|

- Router a PC-A
  - Trama Ethernet
    |MAC Origen|MAC Destino|
    |----------|-----------|
    |MAC_ROUTER01_ETH0|MAC_PCA_ETH0|
  - ARP Request
    |IP Origen|MAC Origen|IP Destino|MAC Destino|
    |---------|----------|----------|-----------|
    |IP_ROUTER01_ETH0|MAC_ROUTER01_ETH0|IP_PCA_ETH0|MAC_PCA_ETH0|

- PC-A a Router
  - Trama Ethernet
    |MAC Origen|MAC Destino|
    |----------|-----------|
    |MAC_PCA_ETH0|MAC_ROUTER01_ETH0|
  - Petición HTTP

### Ejercicio 5

#### Inciso a

Al enviar el mail, el MUA consultara por el registro `A` de su servidor SMTP (`gmail.com`)

#### Inciso b

Una vez que el mail fue recibido por el servidor para `gmail.com`, este consultará por el registro `MX` y respectivo `A` del destino `ejemplo.com`

#### Inciso c

Si en `ejemplo.com` fuese requerimiento hacer uso óptimo de los recursos, debido a limitaciones en las capacidades de almacenamiento, el protocol que debería usarse es `POP3`, ya que este descarga los correos al cliente y los elimina del servidor, liberando espacio en el mismo. En cambio, `IMAP` mantiene los correos en el servidor, lo que puede generar problemas de almacenamiento si la capacidad es limitada.

### Ejercicio 6

#### Inciso a

- El primer punto está hecho en el archivo [Tcp.pdf](Tcp.pdf)
- Vemos que el cliente se está comunicando con el puerto `443` del servidor, por lo que podría ser un indicio de que está intentando conectar con un protocolo `HTTPS`. Sin embargo, no podemos determinar esto al 100% solo con el número de puerto, porque si bien es el puerto estándar para `HTTPS`, el servidor podría tener cualquier otro servicio corriendo en ese puerto.

#### Inciso b

- En esta situación se observa que la ventana de recepción de `175.0.10.2` llega a 0 (`win=0`), lo que significa que el no puede recibir más datos en ese momento. El mecanismo que debe tomar acción en esta situación es el control de flujo.

### Ejercicio 7

- a) Falso
  - En HTTP 1.0 no se usan conexiones persistentes por defecto, pero en HTTP 1.1 sí.
- b) Falso
  - Los registros CNAME asocian un alias a un nombre de dominio canónico, no directamente a una dirección IP. Para asociar un nombre a una IP se utilizan registros tipo `A` (IPv4) o `AAAA` (IPv6).
  > [!note]
  > Si se refiere a lo que termina haciendo indirectamente en la práctica, podríamos decir que la afirmación es verdadera, ya que al final de todo, CNAME funciona como un puente para llegar a la IP, pero técnicamente no es correcto decir que asocia directamente un nombre de dominio a una IP.
- c) Falso
  - No es posible enviar segmentos con puerto origen  0 en TCP, ya que este puerto está reservado y no se utiliza para comunicaciones normales. En cambio, en UDP sí es posible usar el puerto 0 como puerto origen, si es solo de envío y no nos interesa la respuesta, no es necesario configurar como puerto origen un puerto válido.
