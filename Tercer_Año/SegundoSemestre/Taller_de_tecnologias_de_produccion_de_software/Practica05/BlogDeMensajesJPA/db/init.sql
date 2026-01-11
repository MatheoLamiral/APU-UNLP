-- Crear esquema y configuración
CREATE DATABASE IF NOT EXISTS db_blog_mensajes
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;
USE db_blog_mensajes;

-- Tabla usuarios
CREATE TABLE IF NOT EXISTS Users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- Tabla mensajes
CREATE TABLE IF NOT EXISTS Messages (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id)
) ENGINE=InnoDB;