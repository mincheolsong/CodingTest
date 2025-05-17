-- 코드를 작성해주세요

select item_id, item_name, rarity from item_info where item_id in (select a.item_id from item_tree as a join item_info  as b on a.parent_item_id = b.item_id where b.rarity = 'RARE')
    order by item_id desc;

