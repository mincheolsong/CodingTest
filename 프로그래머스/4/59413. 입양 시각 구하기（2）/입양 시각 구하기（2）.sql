set @hour := -1;

select A.HOUR, IFNULL(B.COUNT,0) count
from (select (@hour := @hour + 1) as HOUR from animal_outs where @hour < 23) A
left join (select hour(datetime) as HOUR, count(*) COUNT from animal_outs group by hour(datetime)) B
on A.HOUR = B.HOUR
order by A.HOUR asc;
