select a.item_id, a.item_name
from item_info a
join
(select item_id from item_tree where parent_item_id is null) b
on a.item_id = b.item_id
order by a.item_id asc;