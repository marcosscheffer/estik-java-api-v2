ALTER TABLE items DROP CONSTRAINT items_category_id_fkey;
ALTER TABLE items ADD CONSTRAINT items_category_id_fkey
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT;

ALTER TABLE stock_balance DROP CONSTRAINT stock_balance_item_id_fkey;
ALTER TABLE stock_balance ADD CONSTRAINT stock_balance_item_id_fkey
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE RESTRICT;

ALTER TABLE stock_movement DROP CONSTRAINT stock_movement_item_id_fkey;
ALTER TABLE stock_movement ADD CONSTRAINT stock_movement_item_id_fkey
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE RESTRICT;