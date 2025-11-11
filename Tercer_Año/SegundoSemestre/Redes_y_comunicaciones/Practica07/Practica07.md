# Práctica 7 - Capa de red, direccionamiento 

## Ejercicio 1

La función principal de la capa de red es el transporte de paquetes desde un host emisor a un host receptor. Un protocolo de la capa de red proporciona una comunicación lógica entre hosts.
#### Los servicios que presta son:
- **Enrutamiento (Routing)**: Determina la ruta o camino que deben seguir los paquetes a medida que fluyen de un emisor a un receptor. Los algoritmos de enrutamiento calculan las tablas de reenvío que se utilizan para transmitir los paquetes a través de la red.
- **Reenvío (Forwarding)**: Cuando un paquete llega a un enlace de entrada de un router, este debe pasarlo al enlace de salida apropiado. El reenvío implica la transferencia de un paquete desde un enlace de entrada a un enlace de salida dentro de un mismo router. Los routers llevan a cabo el reenvío examinando el valor de un campo en la cabecera del paquete entrante y utilizándolo para indexar su tabla de reenvío.
- **Configuración de la conexión**: Este proceso implica que los routers a lo largo de la ruta seleccionada negocien entre sí para configurar un estado antes de que los paquetes de datos puedan fluir.

#### PDU de la capa de red

La unidad de datos de protocolo (PDU) es el **datagrama**. En el host emisor, la capa de red crea un datagrama encapsulando el segmento de la capa de transporte. Específicamente, la capa de red añade la información de cabecera de la capa de red al segmento de la capa de transporte, creando así un datagrama de la capa de red. 

#### Dispositivo considerado solo de la capa de red

El dispositivo clave considerado como de la capa de red es el router. Los routers son dispositivos de conmutación de paquetes que basan su decisión de reenvío en el valor almacenado en el campo de la capa de red. El router se considera el dispositivo central de la capa de red porque su función principal es realizar el enrutamiento y el reenvío de datagramas, y no implementa protocolos de la capa de transporte ni de la capa de aplicación (excepto para propósitos de control)

## Ejercicio 2

Se considera a **IP** un protocolo de mejor esfuerzo (Best Effort) porque, aunque hace todo lo posible por entregar los paquetes o datagramas desde el host emisor al host receptor tan rápido como sea posible, no proporciona ninguna garantía sobre la entrega, el orden, la integridad de los datos o la temporización

## Ejercicio 3

- Redes Clase A:
  - Puede haber entre 1 y 126 redes Clase A. Y se pueden representar 16 millones de hosts por red.
- Redes Clase B:
  - Puede haber entre 128 y 191 redes Clase B. Y se pueden representar 64 mil hosts por red.
- Redes Clase C:
  - Puede haber entre 192 y 223 redes Clase C. Y se pueden representar 254 hosts por red.


  
