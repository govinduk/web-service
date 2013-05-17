-- Create database
CREATE DATABASE incidents CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create user

USE incidents;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON incidents.* TO 'user'@'localhost';
