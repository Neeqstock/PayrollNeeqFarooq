/* FLAT EMPLOYEES ---------------------- */
/* 1 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Alan', /*name*/ 
'Avanzo', /*surname*/ 
'Antani St., Arizona', /*address*/ 
'Flat', /*contractType*/
'Mailed', /*methodOfPayment*/
'ASDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
true, /*inUnion*/ 
10, /*unionDues*/
1100, /*salary*/
0, /*rate*/
2) /*commissionRate*/

/* 2 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Brian', /*name*/ 
'Burglars', /*surname*/ 
'Boom St, Boston', /*address*/ 
'Flat', /*contractType*/
'Pickup', /*methodOfPayment*/
'BSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
true, /*inUnion*/ 
10, /*unionDues*/
1200, /*salary*/
0, /*rate*/
2) /*commissionRate*/

/* 3 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Charles', /*name*/ 
'Cort', /*surname*/ 
'Charleton St, Carolina', /*address*/ 
'Flat', /*contractType*/
'Bank', /*methodOfPayment*/
'CSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
false, /*inUnion*/ 
10, /*unionDues*/
1300, /*salary*/
0, /*rate*/
2) /*commissionRate*/

/* 4 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Douglas', /*name*/ 
'Donalds', /*surname*/ 
'Danaerys St, Darlington', /*address*/ 
'Flat', /*contractType*/
'Mailed', /*methodOfPayment*/
'DSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
false, /*inUnion*/ 
10, /*unionDues*/
1400, /*salary*/
0, /*rate*/
2) /*commissionRate*/

/* HOURLY EMPLOYEES ----------------- */

/* 5 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Ethan', /*name*/ 
'Eerie', /*surname*/ 
'Evelyn St, Eelaria', /*address*/ 
'Hourly', /*contractType*/
'Pickup', /*methodOfPayment*/
'ESDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
true, /*inUnion*/ 
10, /*unionDues*/
0, /*salary*/
10, /*rate*/
0) /*commissionRate*/

/* 6 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Francis', /*name*/ 
'Ford', /*surname*/ 
'Far Sea St, Francisco', /*address*/ 
'Hourly', /*contractType*/
'Bank', /*methodOfPayment*/
'FSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
true, /*inUnion*/ 
10, /*unionDues*/
0, /*salary*/
20, /*rate*/
2) /*commissionRate*/

/* 7 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Gordon', /*name*/ 
'Garlic', /*surname*/ 
'Garland St, Geneva', /*address*/ 
'Hourly', /*contractType*/
'Mailed', /*methodOfPayment*/
'GSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
false, /*inUnion*/ 
10, /*unionDues*/
0, /*salary*/
30, /*rate*/
2) /*commissionRate*/

/* 8 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Halil', /*name*/ 
'Horton', /*surname*/ 
'Helicopter St, Harvard', /*address*/ 
'Hourly', /*contractType*/
'Pickup', /*methodOfPayment*/
'HSDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
false, /*inUnion*/ 
10, /*unionDues*/
0, /*salary*/
40, /*rate*/
2) /*commissionRate*/

/* ADMIN */

/* 9 record */
insert into Employee(employeeID, name, surname, address, contractType, methodOfPayment, bankAccount, lastPaid, inUnion, unionDues, salary, rate, commissionRate)
values (
null, /*employeeID*/
'Ilary', /*name*/ 
'Ivory', /*surname*/ 
'Italic St, Ireland', /*address*/ 
'Flat', /*contractType*/
'Bank', /*methodOfPayment*/
'ISDFG123', /*bankAccount*/
'2017-07-01', /*lastPaid*/ 
false, /*inUnion*/ 
10, /*unionDues*/
2000, /*salary*/
0, /*rate*/
2) /*commissionRate*/


/* ACCOUNTS ------------------- */
insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'AAA', /*username*/
'passAAA', /*password*/
1) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'BBB', /*username*/
'passBBB', /*password*/
2) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'CCC', /*username*/
'passCCC', /*password*/
3) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'DDD', /*username*/
'passDDD', /*password*/
4) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'EEE', /*username*/
'passEEE', /*password*/
5) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'FFF', /*username*/
'passFFF', /*password*/
6) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'GGG', /*username*/
'passGGG', /*password*/
7) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
false, /*isAdmin*/
'HHH', /*username*/
'passHHH', /*password*/
8) /*employeeID*/

insert into Account(accountID, isAdmin, username, password, employeeID)
values(
null, /*accountID*/
true, /*isAdmin*/
'admin', /*username*/
'passadmin', /*password*/
9) /*employeeID*/

/* TIMECARDS ------------------- */

insert into TimeCard(timecardID, employeeID, timeCardDate, hoursWorked, paid, additionalInfo)
values(
null, /*timecardID*/
5, /*employeeID*/
2017-07-04, /*timeCardDate*/
8, /*hoursWorked*/
false, /*paid*/
'none')/*additionalInfo*/

insert into TimeCard(timecardID, employeeID, timeCardDate, hoursWorked, paid, additionalInfo)
values(
null, /*timecardID*/
6, /*employeeID*/
2017-07-01, /*timeCardDate*/
8, /*hoursWorked*/
true, /*paid*/
'none')/*additionalInfo*/

insert into TimeCard(timecardID, employeeID, timeCardDate, hoursWorked, paid, additionalInfo)
values(
null, /*timecardID*/
7, /*employeeID*/
2017-07-10, /*timeCardDate*/
10, /*hoursWorked*/
false, /*paid*/
'none')/*additionalInfo*/

insert into TimeCard(timecardID, employeeID, timeCardDate, hoursWorked, paid, additionalInfo)
values(
null, /*timecardID*/
8, /*employeeID*/
2017-07-16, /*timeCardDate*/
5, /*hoursWorked*/
false, /*paid*/
'none')/*additionalInfo*/

/* SALESRECEIPTS ------------------- */
insert into SalesReceipt(receiptID, employeeID, amount, receiptDate, company, additionalInfo, paid)
values(
null, /*receiptID*/
1, /*employeeID*/
100, /*amount*/
2017-07-05, /*receiptDate*/
'TecnoTree', /*company*/
'none',/*additionalInfo*/
false) /*paid*/

insert into SalesReceipt(receiptID, employeeID, amount, receiptDate, company, additionalInfo, paid)
values(
null, /*receiptID*/
2, /*employeeID*/
200, /*amount*/
2017-07-05, /*receiptDate*/
'TecnoTree', /*company*/
'none',/*additionalInfo*/
true) /*paid*/

insert into SalesReceipt(receiptID, employeeID, amount, receiptDate, company, additionalInfo, paid)
values(
null, /*receiptID*/
3, /*employeeID*/
300, /*amount*/
2017-08-05, /*receiptDate*/
'TecnoTree', /*company*/
'none',/*additionalInfo*/
false) /*paid*/

insert into SalesReceipt(receiptID, employeeID, amount, receiptDate, company, additionalInfo, paid)
values(
null, /*receiptID*/
4, /*employeeID*/
400, /*amount*/
2017-09-05, /*receiptDate*/
'TecnoTree', /*company*/
'none',/*additionalInfo*/
false) /*paid*/

/* SERVICECHARGES */

insert into ServiceCharge(chargeID, employeeID, chargeDate, amount, deducted)
values(
null, /*chargeID*/
1, /*employeeID*/
2017-07-01, /*chargeDate*/
30, /*amount*/
false) /*deducted*/