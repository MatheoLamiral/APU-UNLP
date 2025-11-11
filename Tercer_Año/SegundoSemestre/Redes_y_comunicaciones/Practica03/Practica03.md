# Práctica 3: DNS

### Ejercicio 1:

Necesitamos un servicio de directorio que traduzca los nombres de host en direcciones IP. Esta es la tarea principal que lleva a cabo el Sistema de nombres de dominio (DNS, Domain Name System) de Internet. DNS es una base de datos distribuida implementada en una jerarquía de servidores DNS y un protocolo de la capa de aplicación que permite a los hosts consultar la base de datos distribuida. Los servidores DNS suelen ser máquinas UNIX que ejecutan el software BIND (Berkeley Internet Name Domain,Dominio de nombres de Internet de Berkeley). El protocolo DNS se ejecuta sobre UDP y utiliza el puerto 53. Su principal objetivo es traducir los nombres de host en direcciones IP.

### Ejercicio 2:

Un Servidor DNS Raíz (Root DNS server) es el componente que se encuentra en el nivel superior de la jerarquía de la base de datos distribuida del Sistema de Nombres de Dominio (DNS). Su tarea es delegar autoridad al siguiente nivel de la jerarquía, que son los Servidores de Dominio de Nivel Superior (TLD). Cuando un cliente consulta un nombre, el servidor raíz devuelve las direcciones IP de los servidores TLD relevantes (por ejemplo, .com o .edu).
Un Dominio Genérico de Nivel Superior (gTLD, Generic Top-Level Domain) es una clasificación de los Dominios de Nivel Superior (TLDs) dentro del Sistema de Nombres de Dominio (DNS). La función principal de los gTLDs es contener dominios con propósitos particulares, categorizados de acuerdo a diferentes actividades. La Corporación de Internet para los Números y Nombres Asignados (ICANN) define las políticas para estos dominios. Los gTLDs se clasifican generalmente en dos tipos, según las políticas de ICANN:
- Unsponsored TLD (TLDs no patrocinados)
- Sponsored TLD (TLDs patrocinados), definidos por otra organización  

ICANN es la entidad responsable de gestionar el espacio de direcciones IP y de gestionar los servidores raíz DNS, además de tener el trabajo de asignar nombres de dominio. Algunos ejemplos de gTLDs son, .com, .org, .net, .edu, .gov, etc.

### Ejercicio 3

Una respuesta del tipo autoritativa se refiere a una respuesta de un servidor DNS que es considerado autoritativo para el nombre que fue solicitado. Si un servidor DNS es autoritativo para un determinado nombre de host, entonces el servidor DNS contendrá un registro de tipo A para el nombre de host. (Incluso aunque el servidor DNS no sea autoritativo, puede contener un registro de tipo A en su caché.) Si un servidor no es autoritativo para un nombre de host, entonces el servidor contendrá un registro de tipo NS para el dominio que incluye el nombre de host.
Todas las organizaciones que tienen hosts accesibles públicamente (como son los servidores web y los servidores de correo) a través de Internet deben proporcionar registros DNS accesibles públicamente que establezcan la correspondencia entre los nombres de dichos hosts y sus direcciones IP. Un servidor DNS autoritativo de una organización alberga estos registros DNS.

### Ejercicio 4

La diferencia principal entre una consulta recursiva y una iterativa radica en la responsabilidad de la resolución de nombres. En una consulta DNS Recursiva, el cliente solicita al servidor DNS (usualmente el servidor DNS local) que obtenga la correspondencia por sí mismo, y este le devuelve la respuesta final. En cambio, en una consulta DNS Iterativa, cuando el servidor es consultado, devuelve una referencia a otro servidor DNS, y es el cliente quien debe continuar la búsqueda, repitiendo la consulta con el nuevo servidor proporcionado.
En resumen una consulta recursiva, el cliente espera la respuesta final, y en una consulta iterativa, el cliente le pide al root pistas de como buscar, y arranca a buscar de forma iterativa 

### Ejercicio 5

El resolver es un componente que se encuentra en la máquina cliente (host), que se encarga de recorrer el espacio de nombres (estructura de érbol) y traer la IP correspondiente. Puede cacear las IPs y en el caso de buscar una que no se encuentre cacheada, es ahí donde tiene que ir a recorrer el esquema a partir de la raíz. El resolver va a conocer la IP de las raices 

### Ejercicio 6:

Un registro de recurso está formado por los siguientes cuatro campos: `(Nombre, Valor, Tipo, TTL)`
`TTL` es el tiempo de vida del registro de recurso; determina cuándo un recurso debería ser eliminado de una caché, y el significado de `Nombre` y `Valor` dependen del campo `Tipo`:

- **A (Adress)**: proporciona la correspondencia estándar **nombre de host-dirección IP**. En este registro, el campo `Nombre` es un nombre de host y el campo `Valor` es la dirección IP correspondiente
- **MX (Mail Exchanger)**: se utiliza para especificar el servidor o servidores de correo asociados a un nombre de dominio. Entonces `Valor` es el nombre canónico de un servidor de correo que tiene un alias dado por `Nombre`
- **PTR (Pointer)**: se utiliza para la resolución inversa. Mapea una dirección IP a un nombre de dominio o de host
- **AAAA (IPv6 Adress)**: se utiliza para mapear un nombre de host a una dirección IPv6. Funciona de manera similar al registro A, pero para la versión 6 del Protocolo de Internet
- **SRV (Service)**: Define la ubicación (host y puerto) de servicios específicos asociados a un dominio
- **NS (Name Server)**: se utiliza para identificar los servidores DNS que son autoritativos para un dominio o subdominio. El campo `Nombre` es un dominio y el campo `Valor` es el nombre de host de un servidor DNS autoritativo que sabe cómo obtener las direcciones IP de los hosts de ese dominio. Este registro se emplea para encaminar las consultas DNS a lo largo de la cadena de resolución
- **CNAME (Canonical Name)**: se utiliza para establecer un alias para un nombre de host. El campo `Valor` es el nombre de host canónico correspondiente al alias especificado por el campo `Nombre`
- **SOA (Start Of Authority)**: se utiliza para definir los parámetros de la zona o dominio y contiene información fundamental para la administración de la zona. Es un registro obligatorio en cada zona DNS
- **TXT (Textual)**: El registro TXT se utiliza para almacenar información textual o descriptiva asociada con un nombre de dominio
  
### Ejercicio 7:

La existencia de múltiples servidores DNS, a menudo replicados, es esencial para la fiabilidad y la tolerancia a fallos de Internet, ya que elimina los puntos únicos de fallo y asegura la continuidad del servicio ante un colapso de un servidor. Esta arquitectura distribuida también es crucial para la escalabilidad y la distribución de la carga de trabajo, permitiendo manejar el enorme volumen de tráfico de consultas y reducir los retardos

### Ejercicio 8:

La estructura de servidores DNS con roles **primario (maestro)** y **secundario (esclavo)** es una decisión de implementación y gestión de datos que garantiza la fiabilidad y la tolerancia a fallos. El servidor primario es la fuente de verdad y el punto donde se modifican los datos de la zona de autoridad (la copia maestra). Los servidores secundarios mantienen copias replicadas de esta base de datos como respaldo (backup), asegurando que si el servidor principal deja de funcionar, el servicio de resolución de nombres del dominio permanece operativo, mejorando así la seguridad y la continuidad del servicio

### Ejercicio 9:

El mecanismo de transferencia de zona (Zone Transfer) consiste en el proceso mediante el cual un servidor DNS secundario (o esclavo) obtiene y mantiene una copia replicada de la base de datos de nombres (la zona de autoridad) a partir del servidor primario (o maestro). El mecanismo de transferencia de zona es fundamental para sincronizar la información autoritativa. La finalidad principal de la transferencia de zona es garantizar **fiabilidad y tolerancia a fallos**, permitiendo que el servidor secundario mantenga una copia de respaldo (backup) de la base de datos de nombres, y **redundancia** proporcionando un entorno distribuido que mejora la fiabilidad y la seguridad del servicio DNS

### Ejercicio 10:

Para que el administrador de la Facultad de Redes pueda manejar su propio dominio `redes.unlp.edu.ar`, primero se debe delegar el subdominio desde el DNS del dominio principal `unlp.edu.ar.` Esto se hace agregando registros NS que indiquen qué servidores serán responsables de redes.unlp.edu.ar, si esos servidores están dentro del mismo subdominio, también se deben incluir sus registros `A` para que se puedan localizar en la red. Luego, el administrador de Redes configura su propia zona con un registro `SOA` (que indica el inicio de autoridad de la zona), sus propios registros `NS` y los registros `A` de sus servidores, de manera que pueda gestionar el dominio de forma independiente y completa.

### Ejercicio 11:

- Inciso a  
  - **i)** La solicitud fue recursiva, ya que le flag `rd` (recursion desired) esta activo, y la respuesta también lo fue ya que, el flag `ra`(recursion avaiable) también lo está. El flag `rd` indica que el usuario solicitó recursión, y el `ra` indica que el servidor tiene la recursión habilitada, si ambos flags estan activos podemos afirmar que hubo recursión.
  - **ii)** Se trata de una respuesta autoritativa ya que el flag `aa` (authority answer) está activo. Que una respuesta sea autoritativa indica que la respuesta proviene de un servidor autoritativo. Un servidor autoritativo es aquel que mantiene los registros oficiales de un nombre de dominio, actuando como la fuente definitiva de verdad para la dirección ip correspondiente a ese nombre.  
  - **iii)** La dirección IP del resolver utilizado es 172.28.0.29. El comando `dig` muestra explícitamente en la línea `;;SERVER` qué server respondió la consulta. En este caso la respuesta fue, `;; SERVER: 172.28.0.29#53(172.28.0.29)`, donde la primera parte indica el nombre o IP del servidor (en este caso la IP mensionada anteriormente), `#53` indica el puerto (53 es el estándar de DNS) y `(172.28.0.29)` muestra la IP real, lo cual es útil en caso de que el nombre del servidor se muestre como un hostname en lugar de la IP.
- Inciso b 
  - Los servidores de correo del dominio `redes.unlp.edu.ar son` (lo sé usando el comando `dig MX redes.unlp.edu.ar`):
    - `redes.unlp.edu.ar.	86400	IN	MX	5 mail.redes.unlp.edu.ar.`
    - `redes.unlp.edu.ar.	86400	IN	MX	10 mail2.redes.unlp.edu.ar.`
  - Hay más de uno ya que tener varios servidores MX permite redundancia y balanceo. Si el servidor principal falla o no está disponible, el servidor secundario puede recibir el correo, evitando pérdida de mensajes.
  - Los números entre MX y el nombre indican el valor de prioridad del servidor de correo
  - Primero se le entregará al servidor de menor prioridad (en este caso `mail.redes.unlp.edu.ar.`) y si este no está disponible, se le entregará al otro servidor (`mail2.redes.unlp.edu.ar.`)
- Inciso c 
  - Los servidores DNS del dominio `redes.unlp.edu.ar` son (lo sé usando el comando `dig NS redes.unlp.edu.ar`):
    - `redes.unlp.edu.ar.	86400	IN	NS	ns-sv-a.redes.unlp.edu.ar.`
    - `redes.unlp.edu.ar.	86400	IN	NS	ns-sv-b.redes.unlp.edu.ar.`
- Inciso d
  - Se observa que se invierte el orden de los servidores DNS para balancear la carga entre ellos, es decir que las consultas no vayan siempre al mismo servidor
- Inciso e
  - No es posible identificar al servidor primario en base a la salida, para esto deberia consultarse explicitamente por el registro SOA
- Inciso f
  - **i)** El servidor primario es `redes.unlp.edu.ar.	86400	IN	SOA	ns-sv-b.redes.unlp.edu.ar. root.redes.unlp.edu.ar. 2020031700 604800 86400 2419200 86400`
  - **ii)** El número de serie es `2020031700`, sigue el formato  YYYYMMDD, que es la fecha del úlimo cambio de la zona, en este caso 17 de marzo de 2020.`nn`, los ultmios dos números, significan el número de revisión del día (00 indica que es la primera modificación). Es importante actualizarlo cuando se hacen cambios en la zona  DNS del servidor primaro, para que los servidores secundarios detecten el cambio y sincronicen los datos
  - **iii)** El segundo campo tiene el valor `604800` y este es el campo refresh el cual indica el intervalo de tiempo en segundos que los servidores DNS secundarios deben esperar antes de verificar con el servidor DNS primario si hay acutalizaciones en la zona DNS.
  - **iv)** Tiene el valor `86400` y este especifica cuanto tiempo los servidores DNS que realizan consultas(resolvers) deben almacenar en caché una respuesta negativa. Hasta que la caché negativa no expire los servidores que hayan cacheado esa información (que un registro no existe) seguirán dando como respuesta que el registro no existe, pese a que se haya agregado 
- Inciso g
  - El valor del registro TXT para el nombre `saludo.redes.unlp.edu.ar` es `HOLA`. El registro TXT sirve para almacenar información arbitraria en forma de texto en el sistema DNS
- Inciso h
  - se utilizó el comando `dig -t axfr redes.unlp.edu.ar` 
  - **i)** es el TTL que indica el tiempo en segundos que un registro DNS puede ser almacenado en al caché antes de que tenga que revalidar
  - **ii)** se observan 4 registros NS:
    ```bash
      ...
      redes.unlp.edu.ar.	86400	IN	NS	ns-sv-a.redes.unlp.edu.ar.
      redes.unlp.edu.ar.	86400	IN	NS	ns-sv-b.redes.unlp.edu.ar.
      ...
      practica.redes.unlp.edu.ar. 86400 IN	NS	ns1.practica.redes.unlp.edu.ar.
      practica.redes.unlp.edu.ar. 86400 IN	NS	ns2.practica.redes.unlp.edu.ar.
    ```
    La respuesta anterior asumió 2 registros NS (ns1, ns2) para el dominio principal, basándose en una convención genérica. La transferencia de zona revela los nombres reales (ns-sv-a, ns-sv-b) y agrega los registros NS del subdominio practica, que no se incluyeron en la consulta NS porque esta solo cubre el dominio principal. La diferencia se debe a que la consulta NS solo devuelve los servidores DNS del dominio principal, mientras que la transferencia de zona incluye todos los registros de la zona, incluyendo delegaciones de subdominios. Además, la suposición de nombres genéricos (ns1, ns2) no coincidió con la nomenclatura real (ns-sv-a, ns-sv-b). La presencia de registros NS para practica.redes.unlp.edu.ar indica una delegación DNS, lo que permite una gestión separada del subdominio. Esto es típico en entornos institucionales para organizar y distribuir responsabilidades de administración DNS.
- Inciso i
  - Lo que se puede ver es que el TTL de las respuestas que no fueron autoritativas disminuye mientras el de las autoritativas se mantiene estatico. Esto se debe a que el servidor `172.28.0.29` es autoritativo para `redes.unlp.edu.ar` y responde directamente con el TTL original configurado en la zona (300 segundos). No hay caché intermedia que afecte el TTL, por lo que el valor permanece constante. El servidor `172.28.0.29` no es autoritativo para `practica.redes.unlp.edu.ar` y responde con datos cacheados obtenidos de los servidores DNS delegados (ns1.practica o ns2.practica). El TTL disminuye porque refleja el tiempo restante en la caché del servidor, que cuenta hacia abajo desde que el registro fue almacenado (60 segundos inicialmente, reduciéndose en 29 y 18 segundos en las consultas posteriores).
- Inciso j
  - No obtuve respuesta ya que el nombre de dominio no existe, esto lo sabemos por la presencia del codigo `NXDOMAIN` el cual indica que el nombre de dominio solicitado no existe o no está registrado en el sistema DNS. Es una respuesta que un servidor DNS envía cuando no puede encontrar un registro para el dominio consultado. Por otro lado el código `NOERROR` en el protocolo DNS indica que la consulta DNS se completó con éxito y sin errores, y que el servidor DNS encontró y devolvió los registros solicitados para el dominio consultado.

### Ejercicio 12

- `nslookup` es un programa para consultar servidores DNS en Internet. Tiene dos modos, interactivo y no interactivo. El modo interactivo permite al usuario consultar a los servidores de nombres para obtener información sobre distintos hosts y dominios, o imprimir una lista de hosts dentro de un dominio. El modo no interactivo muestra únicamente el nombre y la información solicitada para un host o dominio.
- `host` es una utilidad simple para realizar consultas DNS. Normalmente se utiliza para convertir nombres en direcciones IP y viceversa. Cuando no se proporcionan argumentos ni opciones, host muestra un breve resumen de los parámetros y opciones disponibles en la línea de comandos.
- Obtener la dirección IP de `www.redes.unlp.edu,ar`:
  - `nslookup`
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        Name:	www.redes.unlp.edu.ar
        Address: 172.28.0.50
    ```
  - `host`
    ```bash
        www.redes.unlp.edu.ar has address 172.28.0.50
    ```
- Obtener servidores de correo del dominio `redes.unlp.edu,ar`:
  - `nslookup -type=mx` 
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        redes.unlp.edu.ar	mail exchanger = 10 mail2.redes.unlp.edu.ar.
        redes.unlp.edu.ar	mail exchanger = 5 mail.redes.unlp.edu.ar.
    ```
  - `host -t MX`
    ```bash
        redes.unlp.edu.ar mail is handled by 10 mail2.redes.unlp.edu.ar.
        redes.unlp.edu.ar mail is handled by 5 mail.redes.unlp.edu.ar.  
    ```
- Obtener servidores de DNS del dominio `redes.unlp.edu,ar`:
  - `nslookup -type=ns`
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        redes.unlp.edu.ar	nameserver = ns-sv-b.redes.unlp.edu.ar.
        redes.unlp.edu.ar	nameserver = ns-sv-a.redes.unlp.edu.ar.
    ```
  - `host -t NS`
    ```bash
        redes.unlp.edu.ar name server ns-sv-b.redes.unlp.edu.ar.
        redes.unlp.edu.ar name server ns-sv-a.redes.unlp.edu.ar.
    ```

### Ejercicio 13

El archivo `/etc/hosts` en Linux/Unix y `C:\Windows\System32\drivers\etc\hosts` en Windows funciona como un mapeo local de nombres de host a direcciones IP, permitiendo que el sistema resuelva ciertos nombres sin necesidad de consultar un servidor DNS (se podrá ver como un DNS primitivo). Tiene prioridad en la resolución de nombres, por lo que si un dominio o hostname está definido allí, siempre se usará la dirección IP indicada en el archivo. Esto resulta útil para pruebas locales, para redirigir o bloquear dominios específicos y para simplificar el acceso a equipos dentro de una red interna.

### Ejercicio 14

Son distintas formas de organizar la misma información, solo que wireshark realiza la captura completa del trafico de red. El dig solo muestra lo referido a nuestra consulta.

### Ejercicio 15

- Inciso a
  - Por defecto la PC realizará consultas recursivas a su servidor DNS local
- Inciso b
  - El servidor DNS normalmente realizará consultas iterativas, primero a los root server que son el punto de acceso a la jerarquía de nombres, luego a los TLD, y así continuando por los subdominios hasta terminar o encontar al que se busca. 

### Ejercicio 16

El DNS (Sistema de Nombres de Dominio) y HTTP están estrechamente relacionados. Sin embargo, sí es posible navegar sin servicio de DNS, pero únicamente si se conoce la dirección IP del servidor al que queremos acceder, escribiéndola directamente en el navegador. En la práctica esto es poco conveniente porque sería necesario recordar las IP de todos los sitios, y muchas veces un mismo servidor aloja múltiples dominios (virtual hosting), lo que puede impedir acceder correctamente solo con la IP.

### Ejercicio 17

- PC-A consulta recursivamente a su servidor DNS local "DNS server"
- "DNS server" consulta iterativamente a "A.Root-Server", que lo delega a ".ar"
- "DNS server" consulta iterativamente a "a.dns.ar" el cual lo delega a "edu.ar"
- "DNS server" consulta a iterativamente a "ns1.riu.edu.ar" el cual lo delega a "unlp.edu.ar"
- "DNS server" consulta a iterativamente a "unlp.unlp.edu.ar" el cual le devolverá la IP de "www.unlp.edu.ar"
- "DNS serever" responde a PC-A recursivamente con la IP de "www.unlp.edu.ar"
- PC-A puede ahora consultar a "www.unlp.edu.ar" para obtener la página web

### Ejercicio 18

Deberíamos consultar cuáles son los servidores DNS de www.google.com (dig NS www.google.com) y luego hacer consultas directas a esos servidores para obtener la IP de www.google.com (dig @ns1.google.com www.google.com) de manera autoritativa. Ya que si consultamos a nuestro servidor DNS local, este podría tener la IP cacheada y no estaríamos haciendo una consulta autoritativa.

### Ejercicio 19:

```bash
  redes@debian:~$ dig A  @ns1.google.com. www.info.unlp.edu.ar

  ; <<>> DiG 9.16.27-Debian <<>> A @ns1.google.com. www.info.unlp.edu.ar
  ; (2 servers found)
  ;; global options: +cmd
  ;; Got answer:
  ;; ->>HEADER<<- opcode: QUERY, status: REFUSED, id: 65011
  ;; flags: qr rd; QUERY: 1, ANSWER: 0, AUTHORITY: 0, ADDITIONAL: 1
  ;; WARNING: recursion requested but not available

  ;; OPT PSEUDOSECTION:
  ; EDNS: version: 0, flags:; udp: 512
  ;; QUESTION SECTION:
  ;www.info.unlp.edu.ar.		IN	A

  ;; Query time: 28 msec
  ;; SERVER: 216.239.32.10#53(216.239.32.10)
  ;; WHEN: Thu Sep 25 10:30:51 -03 2025
  ;; MSG SIZE  rcvd: 49
```
El servidor ns1.google.com no es autoritativo para el dominio unlp.edu.ar, por lo que responde con un estado REFUSED (consulta rechazada). Además, el mensaje indica que se solicitó recursión, pero no está disponible en este servidor. Esto significa que el servidor no está dispuesto a realizar la consulta en nombre del cliente, probablemente porque no tiene la información necesaria o porque no está configurado para manejar consultas recursivas para ese dominio.

- Si consultamos al servidor 8.8.8.8
  ```bash
    redes@debian:~$ dig A  @8.8.8.8  www.info.unlp.edu.ar

    ; <<>> DiG 9.16.27-Debian <<>> A @8.8.8.8 www.info.unlp.edu.ar
    ; (1 server found)
    ;; global options: +cmd
    ;; Got answer:
    ;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 12511
    ;; flags: qr rd ra; QUERY: 1, ANSWER: 1, AUTHORITY: 0, ADDITIONAL: 1

    ;; OPT PSEUDOSECTION:
    ; EDNS: version: 0, flags:; udp: 512
    ;; QUESTION SECTION:
    ;www.info.unlp.edu.ar.		IN	A

    ;; ANSWER SECTION:
    www.info.unlp.edu.ar.	300	IN	A	163.10.5.71

    ;; Query time: 51 msec
    ;; SERVER: 8.8.8.8#53(8.8.8.8)
    ;; WHEN: Thu Sep 25 10:36:28 -03 2025
    ;; MSG SIZE  rcvd: 65
  ```
  El servidor 8.8.8.8 es un servidor DNS público de Google y, a diferencia de ns1.google.com, tiene información sobre el dominio unlp.edu.ar. Por lo tanto, puede responder a la consulta.

### Ejercicio 20

- Inciso a
  ;; flags: qr rd ra; QUERY: 1, ANSWER: 2, AUTHORITY: 4, ADDITIONAL: 4
  ;; QUESTION SECTION:
  ;ejemplo.com. IN __ **A**

  ;; ANSWER SECTION:
  ejemplo.com. 1634 IN __ **MX** 10 srv01.ejemplo.com. (1)
  ejemplo.com. 1634 IN __ **MX** 5 srv00.ejemplo.com. (2)

  ;; AUTHORITY SECTION:
  ejemplo.com. 92354 IN __ **NS** ss00.ejemplo.com.
  ejemplo.com. 92354 IN __ **NS** ss02.ejemplo.com.
  ejemplo.com. 92354 IN __ **NS** ss01.ejemplo.com.
  ejemplo.com. 92354 IN __ **NS** ss03.ejemplo.com.

  ;; ADDITIONAL SECTION:
  srv01.ejemplo.com. 272 IN __ **AAAA** 64.233.186.26
  srv01.ejemplo.com. 240 IN __ **AAAA** 2800:3f0:4003:c00::1a
  srv00.ejemplo.com. 272 IN __ **AAAA** 74.125.133.26
  srv00.ejemplo.com. 240 IN __ **AAAA** 2a00:1450:400c:c07::1b
- Inciso b
  - La respuesta no es autoritativa ya que no se encuentra el flag `aa`
  - Para obtener una respuesta autoritativa, le preguntaría a cualquiera de los servidores devueltos en la `AUTHORITY SECTION` 
- Inciso c
  - La consulta fue recursiva ya que se encuentra el flag `rd`, y la respuesta también lo fue ya que se encuentra el flag `ra`
- Inciso d
  - Los valores 10 y 5 representan la prioridad de los servicios de mail `srv01.ejemplo.com.` y `srv00.ejemplo.com.`