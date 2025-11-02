- Crear un usuario con todos los permisos en la db appointments
    
    ```sql
    CREATE USER 'appointments_user'@'%' IDENTIFIED BY 'aca_va_la_contraseña';
    GRANT ALL PRIVILEGES ON appointments.* TO 'appointments_user'@'%';
    FLUSH PRIVILEGES;
    ```

`%` significa cualquier IP, poddría usarse en su lugar localhost o cualquier IP que se prefiera

- Crear un usuario solo con permisos de selección
    
    ```sql
    CREATE USER 'appointments_select'@'%' IDENTIFIED BY 'aca_va_la_contraseña';
    GRANT SELECT ON appointments.* TO 'appointments_select'@'%';
    FLUSH PRIVILEGES;
    ```
    
- Crea un usuario con permisos SELECT, INSERT, UPDATE, DELETE, pero no puede alterar el esquema (CREATE, DROP, ALTER)
    
    ```sql
    CREATE USER 'appointments_update'@'%' IDENTIFIED BY 'aca_va_la_contraseña';
    GRANT SELECT, INSERT, UPDATE, DELETE ON appointments.* TO 'appointments_update'@'%';
    FLUSH PRIVILEGES;
    ```
    
- Crear un usuario con todos los permisos anteriores, y además puede alterar el esquema
    
    ```sql
    CREATE USER 'appointments_schema'@'%' IDENTIFIED BY 'aca_va_la_contraseña';
    GRANT ALL PRIVILEGES ON appointments.* TO 'appointments_schema'@'%';
    FLUSH PRIVILEGES;
    ```