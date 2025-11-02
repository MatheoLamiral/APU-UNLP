# Práctica 4 - SQL

## Docker-compose

para levantar el contenedor de MySQL, se utiliza el siguiente comando:

```bash
    docker-compose up
```
Si se desea levantar el contenedor en segundo plano, se utiliza el siguiente comando:

```bash
    docker-compose up -d
```
Una vez levantado el contenedor de MySQL, se puede acceder a la base de datos utilizando el siguiente comando:

```bash
    docker exec -it mysql-container mysql -u root -p
```

Para conectarse desde MySQL Workbench:
- Hostname: localhost/127.0.0.1
- Port: 3306
- Username: root
- Password: ${MYSQL_ROOT_PASSWORD} (definida en el archivo docker-compose.yml)

## Inicialización de la base de datos

- La base de datos se crea directamente con los usuarios solicitados mediante el script `users.sh`, notar que se cambió el salto de linea de CRLF a LF para evitar problemas de compatibilidad con el sistema unix del contenedor.
- `store_procedures.sql` contiene los scripts para crear los procedimientos almacenados solicitados en la práctica.
- `triggers.sql` contiene los scripts para crear los triggers solicitados en la práctica.
