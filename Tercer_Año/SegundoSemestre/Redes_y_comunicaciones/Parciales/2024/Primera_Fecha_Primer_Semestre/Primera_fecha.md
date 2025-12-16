# Primera fecha Primer Semestre 2024

### Ejercicio 1

#### Inciso a

Si un cliente, desde su red doméstica, desea acceder al sitio https://www.redes.unlp.edu.ar/contacto.html, los mensajes que envía y recibe de la capa de aplicación son:
- Consulta DNS para resolver el nombre de dominio www.redes.unlp.edu.ar
- Respuesta DNS con la dirección IP del servidor web
- Solicitud HTTP GET para el recurso /contacto.html
- Respuesta HTTP con el contenido del recurso /contacto.html

#### Inciso b

- La respuesta que recibe el cliente del DNS Local no es autoritativa, ya que el DNS local no es el dueño de la zona `unlp.edu.ar`. Por ende, el DNS local obtendrá la respuesta de otros servidores DNS para obtener la respuesta correcta, pero la respuesta que él le envía al cliente no es autoritativa.
- La respuesta no es iterativa, ya que la comunicación entre el cliente y el DNS local es recursiva. El DNS local se encarga de resolver la consulta en nombre del cliente, buscando en otros servidores DNS si es necesario, y luego le devuelve la respuesta final al cliente. El cliente delega la resolución al DNS local y espera la respuesta.

#### Inciso c

En base a la respuesta obtenida:
- La versión de HTTP utilizada es HTTP/1.1, ya que vemos el header `Host: www.redes.unlp.edu.ar (obligatorio en HTTP/1.1)` en la petición que tenemos que completar en el inciso siguiente
- El requerimiento sería:
    ```
        GET /contacto.html HTTP/1.1
        Host: www.redes.unlp.edu.ar
    ```

#### Inciso d 

Si el servidor DNS de `primerafecha.redes.unlp.edu.ar` tiene dos registros MX configurados, uno con prioridad 5 y otro con prioridad 6, si se quiere enviar un correo a un usuario con ese dominio, asumiendo que el remitente usa un dominio distinto al destinatario:
- El sevidor que se usará es el que tiene prioridad 5, ya que en los registros MX, un número más bajo indica una mayor prioridad. Por lo tanto, el servidor con prioridad 5 será el primero para la entrega del correo electrónico, si este no está disponible, se intentará con el servidor de prioridad 6.
- El dominio del remitente no debe tener necesariamente configurado un registro MX para enviar el correo. Los registros MX son utilizados para recibir correos electrónicos, no para enviarlos. El servidor de correo del remitente puede enviar correos a cualquier dominio, independientemente de si tiene o no un registro MX configurado.

#### Inciso e

La respuesta `301 Moved Permanently` indica que el recurso solicitado ha sido movido permanentemente a una nueva URL. En este caso, el cliente debe realizar una nueva solicitud HTTP GET a la URL proporcionada en el encabezado `Location:https://www.redes.unlp.edu.ar/acerca-de.html` para acceder al recurso deseado. 

### Ejercicio 2
```
    redes.unlp.edu.ar IN SOA ns1.redes.unlp.edu.ar admin.redes.unlp.edu.ar (
                                    2024060101 ; Serial
                                    3600       ; Refresh
                                    1800       ; Retry
                                    604800     ; Expire
                                    86400 )    ; Minimum TTL
    redes.unlp.edu.ar IN NS ns1.redes.unlp.edu.ar
    ns1.redes.unlp.edu.ar IN A 192.168.1.2
    www.redes.unlp.edu.ar IN A 192.168.1.3
    redes.unlp.edu.ar IN MX 10 mail.redes.unlp.edu.ar
    mail.redes.unlp.edu.ar IN A 192.168.1.4
    primerafecha IN NS ns2.primerafecha.redes.unlp.edu.ar
    ns2.primerafecha.redes.unlp.edu.ar IN A 192.168.1.5
    primerafecha.redes.unlp.edu.ar IN MX 5 mail.primerafecha.redes.unlp.edu.ar
    mail.primerafecha.redes.unlp.edu.ar IN A 192.168.1.6
    primerafecha.redes.unlp.edu.ar IN MX 6 mail2.primerafecha.redes.unlp.edu.ar
    mail2.primerafecha.redes.unlp.edu.ar IN A 192.168.1.7
```
### Ejercicio 3

#### Inciso a

> [!note] NOTA
> El inciso está resuelto en el archivo [Tcp.pdf](Tcp.pdf)

#### Inciso b

- Si antes de recibir los 1176 bytes, el cliente (191.0.1.3) informa una ventana de recepción con disponibilidad de
2000 bytes, y suponiendo que el cliente no consume ningún dato del buffer, el tamaño final de ventana luego de enviar el último ACK sera 780 (2000 - (1176 + 44)).
- Si el servidor hubiera recibido que la ventana de recepción del cliente tiene una disponibilidad de 0 bytes en vez
de 2000 bytes, antes de que pueda enviar los 1176 bytes, el servidor hubiese tenido que detener el envío de datos. Interviene el control de flujo.

### Ejercicio 4

#### Inciso a

> [!note] NOTA
> El inciso está resuelto en el archivo [Subnetting.pdf](Subnetting.pdf)

#### Inciso b

- Tabla de ruteo de Router B

| Destino        | Máscara         | Next-Hop       | Iface   |
|----------------|-----------------|---------------|---------|
|192.168.201.128|/26|-|eth0|
|192.168.201.0|/25|-|eth2|
|10.10.20.4|/30|-|eth3|
|10.10.20.16|/30|-|eth1|
|192.168.200.0|/24|10.10.20.5|eth3|
|10.10.20.0|/30|10.10.20.5|eth3|
|10.10.20.8|/30|10.10.20.18|eth1|
|200.10.10.0|/30|10.10.20.5|eth3|
|10.10.20.12|/30|10.10.20.18|eth1|
|192.168.201.192|/27|10.10.20.18|eth1|
|192.168.201.224|/27|10.10.20.18|eth1|
|0.0.0.0|/0|10.10.20.5|eth3|

- Después de sumarizar

| Destino        | Máscara         | Next-Hop       | Iface   |
|----------------|-----------------|---------------|---------|
|192.168.201.128|/26|-|eth0|
|192.168.201.0|/25|-|eth2|
|10.10.20.4|/30|-|eth3|
|10.10.20.16|/30|-|eth1|
|192.168.200.0|/24|10.10.20.5|eth3|
|10.10.20.0|/30|10.10.20.5|eth3|
|10.10.20.8|/29|10.10.20.18|eth1|
|200.10.10.0|/30|10.10.20.5|eth3|
|192.168.201.192|/26|10.10.20.18|eth1|
|0.0.0.0|/0|10.10.20.5|eth3|


### Ejercicio 5

#### Inciso a

Nunca llegarían a internet ya que desde Router D volvería a Router C y asumiendo que Router C tiene como default gateway a Router D, se generaría un loop entre ambos routers hasta que se venca el TTL del paquete.

#### Inciso b

Por más que Router B no conozca a la red `200.10.10.0/30`, podrá enviar y recibir paquetes desde y hacia internet, siempre y cuando Router B tenga un default getaway configurado y desde ese default gateway pueda llegar a internet.

#### Inciso c

PC-A debería recibir un mensaje ICMP de red inalcanzable (Destination Unreachable) proveniente de Router D, ya que Router D no pudo encontrar una ruta hacia Router C. Indendientemente de si es UDP o TCP, PC-A recibirá esta respuesta o no, dependiendo de la configuración de los routers intermedios.

>[!important]
> Los Routers pueden filtrar ICMP

### Ejercicio 6

1. Falso. Enviar un paquete ICMP al puerto 22 no puede hacerce por medio ni de TCP ni de UDP, ya que ICMP es un protocolo independiente de la capa de transporte, es un protocolo de la capa de red.
2. Falso. Los registros MX generan balanceo de carga al tener la misma prioridad en varios servidores, no al tener diferentes prioridades.
3. Falso. Si hay un servidor web atendiendo solicitudes HTTP 1.1 para diferentes dominios, serán identificados por el header Host, no por el GET ni por el nombre de la página.