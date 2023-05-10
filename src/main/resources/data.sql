-- USERS
INSERT INTO accounts (email, first_name, password, second_name, user_role, user_status)
VALUES ('admin@admin.com', 'admin', 'admin', 'admin', 'ADMIN', 'CONFIRMED');

-- PRODUCTS
INSERT INTO products (category, description, image_url, price, title)
VALUES ('FIGURES', 'Small Naruto Uzumaki''s figure. Add it to your collection!', '', 1300, 'Naruto Uzumaki');

INSERT INTO products (category, description, image_url, price, title)
VALUES ('MANGA', 'This is a dark and thought-provoking story of fierce battles and ruthless fate', '', 540, 'Berserk');

INSERT INTO products (category, description, image_url, price, title)
VALUES ('SNACKS', 'Very tasty onigiri. Just taste them!', '', 100, 'Onigiri');

INSERT INTO products (category, description, image_url, price, title)
VALUES ('MANGA', 'This is the story of a young ninja who dreams of becoming the Hokage', '', 500, 'Naruto Chapter 1');

INSERT INTO products (category, description, image_url, price, title)
VALUES ('MANGA', 'This is the story of a young ninja who dreams of becoming the Hokage', '', 550, 'Naruto Chapter 6');

-- DISCOUNTS
INSERT INTO discounts (description, percentage, promo_code, title)
VALUES ('10% discount for one order', 10, 'MAY2023', 'Spring sales 10%');

INSERT INTO discounts (description, percentage, promo_code, title)
VALUES ('20% discount for the first order', 20, 'FIRST', 'First order 20%');
