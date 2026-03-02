-- Insert sample restaurant (needed for foreign key)
--
INSERT INTO 
    restaurant (id, name, kitchen_type, owner_id)
VALUES (1, 'Test Restaurant', 'BRAZILIAN', 1);

-- Insert sample menu items for restaurant 1
INSERT INTO 
    menu_items (id, name, description, price, is_only_local_consumption, photo_path, restaurant_id)
VALUES 
    (1, 'Feijoada', 'Traditional Brazilian black bean stew with pork', 45.90, false, '/images/feijoada.jpg', 1),
    (2, 'Caipirinha', 'Classic Brazilian cocktail', 15.00, false, '/images/caipirinha.jpg', 1);
