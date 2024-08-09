CREATE TABLE IF NOT EXISTS t_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number varchar(255) DEFAULT NULL,
    sku_code  varchar(255),
    price decimal(19,2),
    quantity  int(11)
);
