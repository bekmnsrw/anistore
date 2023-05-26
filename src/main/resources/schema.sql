CREATE TABLE IF NOT EXISTS persistent_logins(
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

SELECT p FROM products p WHERE p.id NOT IN (SELECT c.product_id FROM cart_items c WHERE c.cart_id = 39);
SELECT c.product_id FROM cart_items c WHERE c.cart_id = 39
