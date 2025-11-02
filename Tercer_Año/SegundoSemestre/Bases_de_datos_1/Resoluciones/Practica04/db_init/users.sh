#!/bin/bash
set -e

#Crea un usuario con todos los privilegios
#Crea un usuario con permisos de select
#Crea un usuario con permisos SELECT, INSERT, UPDATE, DELETE, pero no puede alterar el esquema (CREATE, DROP, ALTER)
#Crear un usuario con todos los permisos anteriores, y además puede alterar el esquema

mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<-EOSQL
CREATE USER 'appointments_user'@'%' IDENTIFIED BY '${MYSQL_APPOINTMENTS_USER_PASSWORD}';
GRANT ALL PRIVILEGES ON appointments.* TO 'appointments_user'@'%';
FLUSH PRIVILEGES;

CREATE USER 'appointments_select'@'%' IDENTIFIED BY '${MYSQL_SELECT_USER_PASSWORD}';
GRANT SELECT ON appointments.* TO 'appointments_select'@'%';
FLUSH PRIVILEGES;

CREATE USER 'appointments_update'@'%' IDENTIFIED BY '${MYSQL_UPDATE_PASSWORD}';
GRANT SELECT, INSERT, UPDATE, DELETE ON appointments.* TO 'appointments_update'@'%';
FLUSH PRIVILEGES;

CREATE USER 'appointments_schema'@'%' IDENTIFIED BY '${MYSQL_UPDATE_SCHEMA_PASSWORD}';
GRANT ALL PRIVILEGES ON appointments.* TO 'appointments_schema'@'%';
FLUSH PRIVILEGES;
EOSQL