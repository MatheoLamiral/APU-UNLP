# Práctica 3 - DNS

### Ejercicio 1

DNS (Domain Name System) **traduce los nombres de host en direcciones IP**. Es una base de datos distribuida implementada en una jerarquía de servidores DNS y un **protocolo de la capa de aplicación** que permite a los hosts consultar la base de datos distribuida. Los servidores DNS suelen ser máquinas UNIX que ejecutan el software BIND (Berkeley Internet Name Domain,Dominio de nombres de Internet de Berkeley). El protocolo DNS **se ejecuta sobre UDP** y utiliza el **puerto 53**. Su principal objetivo es, como se mencionó anteriormente, traducir los nombres de host en direcciones IP.

### Ejercicio 2

Un Servidor DNS Raíz (Root DNS server) es el componente que se encuentra en el **nivel superior** de la **jerarquía** de la base de datos distribuida del **Sistema de Nombres de Dominio** (DNS). Su tarea es delegar autoridad al siguiente nivel de la jerarquía, que son los Servidores de Dominio de Nivel Superior (TLD). Cuando un cliente consulta un nombre, el servidor raíz devuelve las direcciones IP de los servidores TLD relevantes (por ejemplo, .com o .edu).
Un Dominio Genérico de Nivel Superior (gTLD, Generic Top-Level Domain) es una clasificación de los Dominios de Nivel Superior (TLDs) dentro del Sistema de Nombres de Dominio (DNS). La función principal de los gTLDs es contener dominios con propósitos particulares, categorizados de acuerdo a diferentes actividades. La Corporación de Internet para los Números y Nombres Asignados (ICANN) define las políticas para estos dominios. Los gTLDs se clasifican generalmente en dos tipos, según las políticas de ICANN:
- Unsponsored TLD (TLDs no patrocinados)
- Sponsored TLD (TLDs patrocinados), definidos por otra organización  

ICANN es la entidad responsable de gestionar el espacio de direcciones IP y de gestionar los servidores raíz DNS, además de tener el trabajo de asignar nombres de dominio. Algunos ejemplos de gTLDs son, `.com`, `.org`, `.net`, `.edu`, `.gov`, etc.

### Ejercicio 3

Una respuesta del tipo autoritativa se refiere a una respuesta de un servidor DNS que es considerado **autoritativo para el nombre que fue solicitado**. Si un servidor DNS es **autoritativo** para un determinado nombre de host, entonces el servidor DNS **contendrá un registro de tipo A para el nombre de host**. (Incluso aunque el servidor DNS no sea autoritativo, puede contener un registro de tipo A en su caché) Si un servidor **no** es **autoritativo** para un nombre de host, entonces el servidor **contendrá un registro de tipo NS para el dominio que incluye el nombre de host**.
Todas las organizaciones que tienen hosts accesibles públicamente (como son los servidores web y los servidores de correo) a través de Internet deben proporcionar registros DNS accesibles públicamente que establezcan la correspondencia entre los nombres de dichos hosts y sus direcciones IP. Un servidor DNS autoritativo de una organización alberga estos registros DNS.

### Ejercicio 4

La diferencia principal entre una consulta recursiva y una iterativa radica en la responsabilidad de la resolución de nombres. En una consulta DNS Recursiva, el cliente solicita al servidor DNS (usualmente el servidor DNS local) que obtenga la correspondencia por sí mismo, y este le devuelve la respuesta final. En cambio, en una consulta DNS Iterativa, cuando el servidor es consultado, devuelve una referencia a otro servidor DNS, y es el cliente quien debe continuar la búsqueda, repitiendo la consulta con el nuevo servidor proporcionado.
En resumen, en una consulta recursiva, el cliente espera la respuesta final, y en una consulta iterativa, el cliente le pide al root pistas de como buscar, y arranca a buscar de forma iterativa 

### Ejercicio 5

El resolver es un **componente** que se encuentra **en la máquina cliente** (host), que se encarga de **recorrer el espacio de nombres** (estructura de árbol) y **traer la IP correspondiente**. **Puede cachear** las IPs y en el caso de buscar una que no se encuentre cacheada, es ahí donde tiene que ir a recorrer el esquema a partir de la raíz. El resolver va a conocer la IP de las raices 

### Ejercicio 6

Un registro de recurso está formado por los siguientes cuatro campos: `(Nombre, Valor, Tipo, TTL)`. `TTL` es el tiempo de vida del registro de recurso y determina cuándo un recurso debería ser eliminado de una caché. El significado de `Nombre` y `Valor` dependen del campo `Tipo`:

- **A (Adress)**: proporciona la correspondencia estándar **nombre de host -> dirección IP**. En este registro, el campo `Nombre` es un nombre de host y el campo `Valor` es la dirección IP correspondiente. 
  - Ej: `www.ejemplo.com. IN A 192.0.2.1`
- **MX (Mail Exchanger)**: se utiliza para especificar el servidor o servidores de correo asociados a un nombre de dominio. Entonces `Valor` es el nombre canónico de un servidor de correo que tiene un alias dado por `Nombre`. Además, el registro MX incluye un campo de prioridad que indica el orden en que se deben utilizar los servidores de correo cuando hay múltiples registros MX para un dominio (más bajo, más prioridad). 
  - Ej: `ejemplo.com IN MX 10 mail.ejemplo.com`
- **PTR (Pointer)**: se utiliza para la resolución inversa. Mapea una dirección IP a un nombre de dominio o de host
  - Ej: `1.2.0.192.in-addr.arpa IN PTR www.ejemplo.com`
- **AAAA (IPv6 Adress)**: se utiliza para mapear un nombre de host a una dirección IPv6. Funciona de manera similar al registro A, pero para la versión 6 del Protocolo de Internet
  - Ej: `www.ejemplo.com. IN AAAA 2001:0db8:85a3:0000:0000:8a2e:0370:7334`
- **SRV (Service)**: Define la ubicación (host y puerto) de servicios específicos asociados a un dominio
  - Ej: `_sip._tcp.ejemplo.com. IN SRV 10 60 5060 sipserver.ejemplo.com.`
- **NS (Name Server)**: se utiliza para identificar los servidores DNS que son autoritativos para un dominio o subdominio. El campo `Nombre` es un dominio y el campo `Valor` es el nombre de host de un servidor DNS autoritativo que sabe cómo obtener las direcciones IP de los hosts de ese dominio. Este registro se emplea para encaminar las consultas DNS a lo largo de la cadena de resolución
  - Ej: `ejemplo.com. IN NS ns1.ejemplo.com.`
- **CNAME (Canonical Name)**: se utiliza para establecer un alias para un nombre de host. El campo `Valor` es el nombre de host canónico correspondiente al alias especificado por el campo `Nombre`
  - Ej: `alias.ejemplo.com. IN CNAME www.ejemplo.com.`
- **SOA (Start Of Authority)**: se utiliza para definir los parámetros de la zona o dominio y contiene información fundamental para la administración de la zona. Es un registro obligatorio en cada zona DNS. El campo `Nombre` es el nombre del dominio o zona, y el campo `Valor` incluye varios subcampos que proporcionan detalles como el servidor **DNS primario**, el **correo** electrónico del **administrador** de la zona, el n**úmero de serie** de la zona, y los **tiempos** de **actualización** y **expiración**.
  - Ej: `ejemplo.com IN SOA ns1.ejemplo.com. admin.ejemplo.com. (2021010101 7200 3600 1209600 3600)`
- **TXT (Textual)**: El registro TXT se utiliza para almacenar información textual o descriptiva asociada con un nombre de dominio
  - Ej: `ejemplo.com. IN TXT "v=spf1 include:_spf.ejemplo.com ~all"`
  
### Ejercicio 7

La existencia de múltiples servidores DNS, a menudo replicados, es esencial para la fiabilidad y la **tolerancia a fallos** de Internet, ya que elimina los puntos únicos de fallo y asegura la **continuidad del servicio** ante un colapso de un servidor. Esta arquitectura distribuida también es crucial para la **escalabilidad** y la **distribución de la carga de trabajo**, permitiendo manejar el enorme volumen de tráfico de consultas y reducir los retardos

### Ejercicio 8

La estructura de servidores DNS con roles **primario (maestro)** y **secundario (esclavo)** es una decisión de implementación y gestión de datos que garantiza la fiabilidad y la tolerancia a fallos. El servidor **primario** es la fuente de verdad y el **punto donde se modifican los datos de la zona de autoridad (la copia maestra)**. Los servidores **secundarios mantienen copias replicadas de esta base de datos como respaldo (backup)**, asegurando que si el servidor principal deja de funcionar, el servicio de resolución de nombres del dominio permanece operativo, mejorando así la seguridad y la continuidad del servicio

### Ejercicio 9

El mecanismo de transferencia de zona (Zone Transfer) consiste en el proceso mediante el cual un servidor **DNS secundario (o esclavo) obtiene y mantiene una copia replicada de la base de datos de nombres (la zona de autoridad) a partir del servidor primario (o maestro)**. El mecanismo de transferencia de zona es fundamental para **sincronizar la información autoritativa**. La finalidad principal de la transferencia de zona es garantizar **fiabilidad y tolerancia a fallos**, permitiendo que el servidor secundario mantenga una copia de respaldo (backup) de la base de datos de nombres, y **redundancia** proporcionando un entorno distribuido que mejora la fiabilidad y la seguridad del servicio DNS

### Ejercicio 10

Para que el administrador de la Facultad de Redes pueda manejar su propio dominio `redes.unlp.edu.ar`, primero se debe delegar el subdominio desde el DNS del dominio principal `unlp.edu.ar.`. Lo que implica que el administrador de `unlp.edu.ar` debe delegar la autoridad sobre el subdominio `redes.unlp.edu.ar` a los servidores DNS que gestiona el administrador de la Facultad de Redes. Esto se hace agregando registros NS (en la zona de `unlp.edu.ar`) que indiquen qué servidores DNS de la Faucltad de redes serán responsables del dominio `redes.unlp.edu.ar`, si esos servidores están dentro del mismo subdominio, también se deben incluir sus registros `A` para que se puedan localizar en la red. Luego, el administrador de Redes configurará su propia zona con un registro `SOA` (que indica el inicio de autoridad de la zona), sus propios registros `NS` y los registros `A` de sus servidores, de manera que pueda gestionar el dominio de forma independiente y completa.

### Ejercicio 11

#### Inciso a 

- **i)** La solicitud fue recursiva, ya que le flag `rd` (recursion desired) esta activo, y la respuesta también lo fue ya que, el flag `ra`(recursion avaiable) también lo está. El flag `rd` indica que el usuario solicitó recursión, y el `ra` indica que el servidor tiene la recursión habilitada, si ambos flags están activos podemos afirmar que hubo recursión.
- **ii)** Se trata de una respuesta autoritativa ya que el flag `aa` (authority answer) está activo. Que una respuesta sea autoritativa indica que la **respuesta proviene de un servidor autoritativo**. Un servidor autoritativo es aquel que mantiene los registros oficiales de un nombre de dominio, actuando como la fuente definitiva de verdad para la dirección ip correspondiente a ese nombre.  
- **iii)** La dirección IP del resolver utilizado es 172.28.0.29. El comando `dig` muestra explícitamente en la línea `;;SERVER` qué server respondió la consulta. En este caso la respuesta fue, `;; SERVER: 172.28.0.29#53(172.28.0.29)`, donde la primera parte indica el nombre o IP del servidor (en este caso la IP mensionada anteriormente), `#53` indica el puerto (53 es el estándar de DNS) y `(172.28.0.29)` muestra la IP real, lo cual es útil en caso de que el nombre del servidor se muestre como un hostname en lugar de la IP.

#### Inciso b 

- Los servidores de correo del dominio `redes.unlp.edu.ar son` (lo sé usando el comando `dig MX redes.unlp.edu.ar`):
  - `redes.unlp.edu.ar.	86400	IN	MX	5 mail.redes.unlp.edu.ar.`
  - `redes.unlp.edu.ar.	86400	IN	MX	10 mail2.redes.unlp.edu.ar.`
- Hay más de uno ya que tener varios servidores MX permite redundancia y balanceo. Si el servidor principal falla o no está disponible, el servidor secundario puede recibir el correo, evitando pérdida de mensajes.
- Los números entre MX y el nombre indican el valor de prioridad del servidor de correo, donde un número más bajo indica una mayor prioridad.
- Primero se le entregará al servidor de mayor prioridad (en este caso `mail.redes.unlp.edu.ar.`) y si este no está disponible, se le entregará al otro servidor (`mail2.redes.unlp.edu.ar.`)

#### Inciso c 

- Los servidores DNS del dominio `redes.unlp.edu.ar` son (lo sé usando el comando `dig NS redes.unlp.edu.ar`):
  - `redes.unlp.edu.ar.	86400	IN	NS	ns-sv-a.redes.unlp.edu.ar.`
  - `redes.unlp.edu.ar.	86400	IN	NS	ns-sv-b.redes.unlp.edu.ar.`

#### Inciso d

- Se observa que se invierte el orden de los servidores DNS para balancear la carga entre ellos, es decir que las consultas no vayan siempre al mismo servidor

#### Inciso e

- No es posible identificar al servidor primario en base a la salida, para esto deberia consultarse explicitamente por el registro SOA con el comando `dig SOA redes.unlp.edu.ar`

#### Inciso f

- **i)** El servidor primario es `ns-sv-b.redes.unlp.edu.ar.`. La respuesta fue `redes.unlp.edu.ar.	86400	IN	SOA	ns-sv-b.redes.unlp.edu.ar. root.redes.unlp.edu.ar. 2020031700 604800 86400 2419200 86400`
- **ii)** El número de serie es `2020031700`, sigue el formato  YYYYMMDD, que es la fecha del úlimo cambio de la zona, en este caso 17 de marzo de 2020.`nn`, los ultmios dos números, significan el número de revisión del día (00 indica que es la primera modificación). Es importante actualizarlo cuando se hacen cambios en la zona  DNS del servidor primaro, para que los servidores secundarios detecten el cambio y sincronicen los datos
- **iii)** El segundo campo tiene el valor `604800` y es el campo de intervalo de actualización (refresh interval), el cual indica el intervalo de tiempo en segundos que los servidores DNS secundarios deben esperar antes de verificar con el servidor DNS primario si hay acutalizaciones en la zona DNS.
- **iv)** Tiene el valor `86400` y este especifica cuanto tiempo los servidores DNS que realizan consultas (resolvers) deben almacenar en caché una respuesta negativa. Hasta que la caché negativa no expire los servidores que hayan cacheado esa información (que un registro no existe) seguirán dando como respuesta que el registro no existe, pese a que se haya agregado 

#### Inciso g

- El valor del registro TXT para el nombre `saludo.redes.unlp.edu.ar` es `HOLA`. El registro TXT sirve para almacenar información arbitraria asociada a un dominio en forma de texto en el sistema DNS. Se obtuvo con el comando `dig TXT saludo.redes.unlp.edu.ar`

#### Inciso h

- se utilizó el comando `dig -t axfr redes.unlp.edu.ar` 
- **i)** es el **TTL** que indica el tiempo en segundos que un registro DNS puede ser almacenado en al caché **antes de que tenga que volver a ser consultado** nuevamente al **servidor DNS autoritativo**. Un **TTL alto** significa que los la información se mantendrá en caché por más tiempo, lo que puede **reducir la carga en los servidores** DNS y mejorar el rendimiento, pero también puede hacer que los cambios en los registros tarden más en propagarse. Un **TTL bajo** puede resultar en una propagación más rápida de los cambios, pero puede **aumentar la carga en los servidores** DNS debido a consultas más frecuentes.
- **ii)** se observan 4 registros NS:
  ```bash
    ...
    redes.unlp.edu.ar.	86400	IN	NS	ns-sv-a.redes.unlp.edu.ar.
    redes.unlp.edu.ar.	86400	IN	NS	ns-sv-b.redes.unlp.edu.ar.
    ...
    practica.redes.unlp.edu.ar. 86400 IN	NS	ns1.practica.redes.unlp.edu.ar.
    practica.redes.unlp.edu.ar. 86400 IN	NS	ns2.practica.redes.unlp.edu.ar.
  ```
  Se observan 4 registros NS. La consulta NS inicial solo muestra los servidores de nombres para el dominio `redes.unlp.edu.ar` y no incluye los registros NS de los subdominios, como `practica.redes.unlp.edu.ar`, por eso solo se visualizan 2 NS. Ahora se visualizan los 4 debido a que la consulta AXFR (transferencia de zona) devuelve todos los registros DNS para el dominio, incluyendo los subdominios, como es el caso de `practica.redes.unlp.edu.ar`, que tiene sus propios NS. 
  
#### Inciso i

- Lo que se puede ver es que el TTL de las respuestas que no fueron autoritativas disminuye mientras el de las autoritativas se mantiene estatico. Esto se debe a que el servidor `172.28.0.29` es autoritativo para `redes.unlp.edu.ar` y responde directamente con el TTL original configurado en la zona (300 segundos). No hay caché intermedia que afecte el TTL, por lo que el valor permanece constante. El servidor `172.28.0.29` no es autoritativo para `practica.redes.unlp.edu.ar` y responde con datos cacheados obtenidos de los servidores DNS delegados (`ns1.practica` o `ns2.practica`). El TTL disminuye porque refleja el tiempo restante en la caché del servidor, que cuenta hacia abajo desde que el registro fue almacenado (60 segundos inicialmente, reduciéndose en 29 y 18 segundos en las consultas posteriores).

#### Inciso j

- La consulta devolvió una respuesta con código `NXDOMAIN`, el cual indica que el nombre de dominio solicitado no existe o no está registrado en el sistema DNS. Es una respuesta que un servidor DNS envía cuando no puede encontrar un registro para el dominio consultado. Por otro lado el código `NOERROR` en el protocolo DNS indica que la consulta DNS se completó con éxito y sin errores, y que el servidor DNS encontró y devolvió los registros solicitados para el dominio consultado.

### Ejercicio 12

- `nslookup` es un programa para consultar servidores DNS en Internet. Tiene dos modos, interactivo y no interactivo. El modo interactivo permite al usuario consultar a los servidores de nombres para obtener información sobre distintos hosts y dominios, o imprimir una lista de hosts dentro de un dominio. El modo no interactivo muestra únicamente el nombre y la información solicitada para un host o dominio.
- `host` es una utilidad simple para realizar consultas DNS. Normalmente se utiliza para convertir nombres en direcciones IP y viceversa. Cuando no se proporcionan argumentos ni opciones, host muestra un breve resumen de los parámetros y opciones disponibles en la línea de comandos.
- Obtener la dirección IP de `www.redes.unlp.edu,ar`:
  - `nslookup www.redes.unlp.edu.ar`
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        Name:	www.redes.unlp.edu.ar
        Address: 172.28.0.50
    ```
  - `host www.redes.unlp.edu.ar`
    ```bash
        www.redes.unlp.edu.ar has address 172.28.0.50
    ```
- Obtener servidores de correo del dominio `redes.unlp.edu,ar`:
  - `nslookup -type=mx redes.unlp.edu.ar` 
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        redes.unlp.edu.ar	mail exchanger = 10 mail2.redes.unlp.edu.ar.
        redes.unlp.edu.ar	mail exchanger = 5 mail.redes.unlp.edu.ar.
    ```
  - `host -t MX redes.unlp.edu.ar`
    ```bash
        redes.unlp.edu.ar mail is handled by 10 mail2.redes.unlp.edu.ar.
        redes.unlp.edu.ar mail is handled by 5 mail.redes.unlp.edu.ar.  
    ```
- Obtener servidores de DNS del dominio `redes.unlp.edu,ar`:
  - `nslookup -type=ns redes.unlp.edu.ar`
    ```bash
        Server:		172.28.0.29
        Address:	172.28.0.29#53

        redes.unlp.edu.ar	nameserver = ns-sv-b.redes.unlp.edu.ar.
        redes.unlp.edu.ar	nameserver = ns-sv-a.redes.unlp.edu.ar.
    ```
  - `host -t NS redes.unlp.edu.ar`
    ```bash
        redes.unlp.edu.ar name server ns-sv-b.redes.unlp.edu.ar.
        redes.unlp.edu.ar name server ns-sv-a.redes.unlp.edu.ar.
    ```

### Ejercicio 13

El archivo `/etc/hosts` en Linux/Unix y `C:\Windows\System32\drivers\etc\hosts` en Windows funciona como un **mapeo local de nombres de host a direcciones IP**, **permitiendo** que el **sistema resuelva** ciertos nombres **sin necesidad de consultar un servidor DNS** (se podrá ver como un DNS primitivo). Tiene prioridad en la resolución de nombres, por lo que si un dominio o hostname está definido allí, siempre se usará la dirección IP indicada en el archivo. Esto resulta útil para pruebas locales, para redirigir o bloquear dominios específicos y para simplificar el acceso a equipos dentro de una red interna.

### Ejercicio 14

Son distintas formas de organizar la misma información, solo que wireshark realiza la captura completa del trafico de red. El dig solo muestra lo referido a nuestra consulta.

### Ejercicio 15

#### Inciso a

- Por defecto la PC realizará **consultas recursivas** a su servidor DNS local, ya que no sabe cómo resolver el dominio por sí misma. Confiará en el servidor DNS para realizar la resolución, la PC solo espera la respuesta final.

#### Inciso b

- El servidor DNS local realizará **consultas iterativas**, primero a los root server que son el punto de acceso a la jerarquía de nombres, luego a los TLD, y así continuando por los subdominios hasta terminar o encontar al dominio buscado. 

### Ejercicio 16

DNS y HTTP están estrechamente relacionados. Sin embargo, sí es posible navegar sin servicio de DNS, pero únicamente si se conoce la dirección IP del servidor al que queremos acceder, escribiéndola directamente en el navegador. En la práctica esto es poco conveniente porque sería necesario recordar las IP de todos los sitios, y muchas veces un mismo servidor aloja múltiples dominios (virtual hosting), lo que puede impedir acceder correctamente solo con la IP.

### Ejercicio 17

#### Inciso a

1. PC-A (`192.168.10.5`) consulta recursivamente a DNS Server, que su servidor DNS configurado (`192.168.10.2`)
2. DNS server verifica en su caché si tiene la respuesta, si la tiene, la devuelve a PC-A. Si no la tiene, empieza una consulta iterativa.
3. DNS Server consulta iterativamente a un servidor raíz (root server), en este caso `A.Root-Server`(`205.10.100.10`), para obtener la dirección del servidor TLD para `.ar`
4. `A.Root-Server` responde iterativamente con el NS y la dirección del servidor TLD para `.ar` que sería `a.dns.ar`(`200.108.145.50`)
5. DNS Server consulta iterativamente al servidor TLD `a.dns.ar`(`200.108.145.50`) para obtener la dirección del servidor autoritativo para `.edu.ar`
6. `a.dns.ar` responde iterativamente con el NS y la dirección del servidor autoritativo para `.edu.ar`, que sería `ns1.riu.edu.ar`(`1770.210.0.18`)
7. DNS Server consulta iterativamente al servidor autoritativo `ns1.riu.edu.ar`(`1770.210.0.18`) para obtener la dirección del servidor autoritativo para `unlp.edu.ar`
8. `ns1.riu.edu.ar` responde iterativamente con el NS y la dirección del servidor autoritativo para `unlp.edu.ar`, que sería `unlp.unlp.edu.ar`(`163.10.0.67`)
9. DNS Server consulta iterativamente al servidor autoritativo `unlp.unlp.edu.ar`(`163.10.0.67`) para obtener la dirección de `www.redes.unlp.edu.ar`
10. `unlp.unlp.edu.ar` responde iterativamente con la dirección IP de `www.redes.unlp.edu.ar`, que es `163.10.0.54`
11. DNS Server cachea la respuesta y le responderá a PC-A con la dirección IP de `www.redes.unlp.edu.ar` (`163.10.0.54`)

#### Inciso b

La consulta es recursiva desde la PC-A hasta el DNS Server, ya que la PC-A no sabe cómo resolver el dominio por sí misma, le delega la tarea al servidor DNS y espera la respuesta final. Desde el DNS Server hacia los demás servidores de la jerarquía la consulta es iterativa, ya que el DNS Server va obteniendo referencias a otros servidores en cada paso y es él quien realiza las consultas sucesivas hasta obtener la respuesta final.

### Ejercicio 18

Deberíamos consultar cuáles son los servidores DNS de www.google.com (`dig NS www.google.com`), para ver cuales son autoritativos para ese dominio, y luego hacer consultas directas a esos servidores (`dig www.google.com @ns1.google.com`) para obtener la IP de www.google.com de manera autoritativa. Ya que si consultamos a nuestro servidor DNS local, este podría tener la IP cacheada y no estaríamos haciendo una consulta autoritativa.

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
Al hacer la consulta, en la respuesta se puede ver el estado `REFUSED` (consulta rechazada), en este caso, porque el servidor no permite consultas recursivas. Vemos el flag `rd` (recursion desired) activo, lo que indica que se solicitó recursión, pero el servidor no la tiene disponible, como se indica en la línea `;; WARNING: recursion requested but not available`. Por lo tanto, el servidor rechazó la consulta.

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
  El servidor `8.8.8.8` es un servidor DNS público de Google, y no genera error ya que es un Open Name Server que funciona como servidor local para cualquier cliente y puede resolver consultas para cualquier dominio en Internet.

### Ejercicio 20

#### Inciso a

;; flags: qr rd ra; QUERY: 1, ANSWER: 2, AUTHORITY: 4, ADDITIONAL: 4  
;; QUESTION SECTION:  
;ejemplo.com. IN <u> **MX**  </u> 

;; ANSWER SECTION:  
ejemplo.com. 1634 IN <u>**MX**</u> 10 srv01.ejemplo.com. (1)  
ejemplo.com. 1634 IN <u>**MX**</u> 5 srv00.ejemplo.com. (2)  

;; AUTHORITY SECTION:  
ejemplo.com. 92354 IN <u>**NS**</u> ss00.ejemplo.com.  
ejemplo.com. 92354 IN <u>**NS**</u> ss02.ejemplo.com.  
ejemplo.com. 92354 IN <u>**NS**</u> ss01.ejemplo.com.  
ejemplo.com. 92354 IN <u>**NS**</u> ss03.ejemplo.com.  

;; ADDITIONAL SECTION:  
srv01.ejemplo.com. 272 IN <u>**A**</u> 64.233.186.26  
srv01.ejemplo.com. 240 IN <u>**AAAA**</u> 2800:3f0:4003:c00::1a  
srv00.ejemplo.com. 272 IN <u>**A**</u> 74.125.133.26  
srv00.ejemplo.com. 240 IN <u>**AAAA**</u> 2a00:1450:400c:c07::1b  

#### Inciso b

- La respuesta no es autoritativa ya que no se encuentra el flag `aa`
- Para obtener una respuesta autoritativa, le preguntaría a cualquiera de los servidores devueltos en la `AUTHORITY SECTION` 

#### Inciso c

- La consulta fue recursiva ya que se encuentra el flag `rd` (recursion desired), y la respuesta también lo fue ya que se encuentra el flag `ra` (recursion available)

#### Inciso d

- Los valores 10 y 5 representan la prioridad de los servicios de mail `srv01.ejemplo.com.` y `srv00.ejemplo.com.` respectivamente.