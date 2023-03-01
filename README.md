Employee Management

CRUD Operations Included
C-create an Employee
R-Read all employees
U-update employee
D-delete Employee

kafka Integration
when updating ctc value in employee table, it will asynchronous produce the data, payrollmanagement service will consume that data and update ctc/12 value in payroll collection(Monthlysalary field)

springboot-swagger implementation
can be checked with https://localhost:8082/swagger-ui.html

Implemented Cache mechanism with caffaeine cache

MongoDB Auditing is done
to check this--have to uncomment the lines in employee model class
