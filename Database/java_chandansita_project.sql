--  Create a Database
CREATE DATABASE IF NOT EXISTS java_chandansita_project;

--: Use the Database
USE java_chandansita_project;



-- Create user_table
CREATE TABLE IF NOT EXISTS user_table (
    user_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

-- Create order_table
CREATE TABLE IF NOT EXISTS order_table (
    order_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    user_id INT(11),
    item_name VARCHAR(255),
    item_quantity INT(11),
    item_price DECIMAL(10,2),
    order_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (user_id) REFERENCES user_table(user_id)
);
