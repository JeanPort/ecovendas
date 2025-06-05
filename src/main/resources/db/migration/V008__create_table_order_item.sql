CREATE TABLE tbl_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES tbl_order(id),
    FOREIGN KEY (product_id) REFERENCES tbl_product(id)
);