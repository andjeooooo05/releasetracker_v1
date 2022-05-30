CREATE USER 'neonassignment'@'localhost' IDENTIFIED BY 'neonassignment';

GRANT ALL PRIVILEGES ON * . * TO 'neonassignment'@'localhost';

ALTER USER 'neonassignment'@'localhost' IDENTIFIED WITH mysql_native_password BY 'neonassignment';