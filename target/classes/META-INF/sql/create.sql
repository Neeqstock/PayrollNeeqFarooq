CREATE TABLE Employee ( 
employeeID int not null AUTO_INCREMENT, 
name varchar(30), 
surname varchar(30), 
address varchar(30), 
contractType varchar(20), 
methodOfPayment varchar(20) not null default "Pickup", 
bankAccount varchar(40), 
lastPaid date,
inUnion boolean not null default false, 
unionDues float not null default 0, 
salary float not null default 0, /*FLATEMPLOYEE*/
rate float not null default 0, /*HOURLYEMPLOYEE*/
commissionRate float not null default 0, 
PRIMARY KEY (id));

CREATE TABLE Account ( 
accountID int not null AUTO_INCREMENT, 
isAdmin boolean not null default false,
username varchar(40),
password varchar(40), 
employeeID int, 
PRIMARY KEY (accountID), 
FOREIGN KEY (employeeID) REFERENCES Employee (employeeID));

CREATE TABLE Payment ( 
paymentID int not null AUTO_INCREMENT, 
employeeID int not null,
paymentDate date not null,
paymentAmount float not null default 0,
PRIMARY KEY (paymentID), 
FOREIGN KEY (employeeID) REFERENCES Employee (employeeID));

CREATE TABLE SalesReceipt( 
receiptID int not null AUTO_INCREMENT, 
employeeID int not null,
amount float not null default 0,
receiptDate date,
company varchar(40),
additionalInfo varchar(1000),
paid boolean not null default false,
PRIMARY KEY (receiptID), 
FOREIGN KEY (employeeID) REFERENCES Employee (employeeID));

CREATE TABLE ServiceCharge( 
chargeID int not null AUTO_INCREMENT, 
employeeID int not null,
chargeDate date,
amount float not null default 0,
deducted boolean not null default false,
PRIMARY KEY (chargeID), 
FOREIGN KEY (employeeID) REFERENCES Employee (employeeID));

CREATE TABLE TimeCard ( 
timecardID int not null AUTO_INCREMENT,
employeeID int not null,
timeCardDate date,
hoursWorked int,
paid boolean not null default false,
additionalInfo varchar(1000),
PRIMARY KEY (timecardID), FOREIGN KEY (employeeID) REFERENCES Employee (employeeID));