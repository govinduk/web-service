CREATE DATABASE incidents CHARACTER SET utf8 COLLATE utf8_general_ci;

USE incidents;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON incidents.* TO 'user'@'localhost';

-- SET PASSWORD FOR 'user'@'localhost' = PASSWORD('demo');