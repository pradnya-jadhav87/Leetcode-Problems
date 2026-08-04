# Write your MySQL query statement below
SELECT name  from SalesPerson S

where sales_id  not  in (
SELECT O.sales_id from Company C
join Orders O 
on C.com_id = O.com_id where C.name='RED')  ; 
