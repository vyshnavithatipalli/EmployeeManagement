Requirements to execute the code
java installation
sts
MONGO DB connectivity-----download zip file and run mongod command
KAFFA setup--download kafka Zip file
inside zookeeper.properties file(located inside kafka zip under config):
dataDir=C:/Users/thati/Downloads/kafka_2.12-3.4.0/kafka_2.12-3.4.0/zookeeper-data
inside server.properties file(located inside kafka zip under config):
log.dirs=C:/Users/thati/Downloads/kafka_2.12-3.4.0/kafka_2.12-3.4.0/kafka-logs

to start Zookeeper-.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 
to start kafka.\bin\windows\kafka-server-start.bat .\config\server.properties



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
