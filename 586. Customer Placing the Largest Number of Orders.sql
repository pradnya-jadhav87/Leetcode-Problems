# Write your MySQL query statement below
select customer_number 
from orders
Group by Customer_number
order by count(order_number) DESC
limit 1;
