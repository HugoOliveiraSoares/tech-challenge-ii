INSERT INTO address (id, street, number, neighborhood, city, zip_code)
VALUES (1, 'Main St', '123', 'Downtown', 'São Paulo', '01000-000');

INSERT INTO app_user (id, name, email, login, password, user_type, last_modified_date)
VALUES (1, 'Test Owner', 'owner@test.com', 'owner', 'password', 'OWNER', CURRENT_DATE);

INSERT INTO restaurant
(id, name, opening_hours, address_id, kitchen_type, owner_id)
VALUES(1, 'Test Restaurant', '10h', 1, 'Brazilian', 1);

INSERT INTO 
    menu_item (id, name, description, price, only_local_consumption, photo_path, restaurant_id)
VALUES 
    (1, 'Feijoada', 'Traditional Brazilian black bean stew with pork', 45.90, false, '/images/feijoada.jpg', 1),
    (2, 'Caipirinha', 'Classic Brazilian cocktail', 15.00, false, '/images/caipirinha.jpg', 1);
