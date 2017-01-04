# Write your MySQL query statement below
select staff.name as Employee
from Employee staff, Employee manager
where staff.ManagerId IS NOT NULL AND staff.ManagerId = manager.Id AND staff.salary > manager.salary;