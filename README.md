Simple web-project that allows to modify data in the table from the database.

List of required pre-installed software:
1.	Java SE 6 (JDK 6u45)
2.	Java EE 5 (SDK Update 8)
3.	GlassFish v2.1.1 (included in SDK)
4.	MySQL 5.6

List of technologies used in development:
1.	DBMS: MySQL 5.6.25
2.	Application server: GlassFish v2.1.1
3.	IDE: Eclipse IDE for Java EE Indigo
•	connector for MySQL: MySQL Connector Java 5.1.23
•	connector for GlassFish: Oracle GlassFish Tools for Eclipse
4.	Technique to access DB: JDBC
5.	Technology for creating pages: JSP
6.	CSS

SQL scripts for DB scheme, table and data creation:

CREATE SCHEMA `taskdb`;

CREATE TABLE `taskdb`.`employees` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `LName` VARCHAR(30) NOT NULL,
  `FName` VARCHAR(30) NOT NULL,
  `Gender` CHAR(1) NOT NULL,
  `JobCode` VARCHAR(5) NULL,
  `Salary` DECIMAL(10,2) NULL,
  `BirthDate` DATE NULL,
  PRIMARY KEY (`ID`));

INSERT INTO `taskdb`.`employees` (`ID`, `LName`, `FName`, `Gender`, `JobCode`, `Salary`, `BirthDate`) VALUES ('1', 'Miller', 'Ben', 'M', 'SJD', '1200.00', '1970-05-12');
INSERT INTO `taskdb`.`employees` (`ID`, `LName`, `FName`, `Gender`, `JobCode`, `Salary`, `BirthDate`) VALUES ('2', 'Smith', 'Tom', 'M', 'RE', '600.00', '1985-09-30');
INSERT INTO `taskdb`.`employees` (`ID`, `LName`, `FName`, `Gender`, `JobCode`, `Salary`, `BirthDate`) VALUES ('3', 'Evans', 'Kim', 'F', 'TL', '1500.00', '1970-05-12');
INSERT INTO `taskdb`.`employees` (`ID`, `LName`, `FName`, `Gender`, `JobCode`, `Salary`, `BirthDate`) VALUES ('4', 'Bruce', 'Wayne', 'M', 'BM', '2000.00', '1980-02-19');
INSERT INTO `taskdb`.`employees` (`ID`, `LName`, `FName`, `Gender`, `JobCode`, `Salary`, `BirthDate`) VALUES ('5', 'Diaz', 'Brenda', 'F', 'RE', '700.00', '1987-04-15');
