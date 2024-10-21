# Product catalog Backend

## Content
- [Installation](#installation)
- [Configure_a_Spring_Boot_project_in_Eclipse](#configure_a_spring_boot_project_in_eclipse)
- [Using_MySQL_Workbench](#using_mysql_workbench)
- [Copy_the_project_from_GitHub](#copy_the_project_from_github)
- [Run_the_project_in_Eclipse](#run_the_project_in_eclipse)
- [Usage](#usage)
- [Technology](#technology)
- [Developers](#developers)

## Installation
### 1. Installing Java JDK (Java Development Kit):
- Download and install Java JDK from [Oracle Java](https://www.oracle.com/java/technologies/downloads/?er=221886) or [OpenJDK](https://openjdk.org/).
- Check that the JDK is running by opening a terminal or command prompt and running the following command:
``` bash
java -version
```
### 2. Installing Eclipse IDE:
- Download and install Eclipse IDE for Java Developers from [Eclipse official page](https://www.eclipse.org/downloads/).
### 3. Installing Maven:
- Maven is used to manage the Spring Boot project. Download and install Maven from the [Apache Maven page](https://maven.apache.org/download.cgi).
- Verify that Maven is installed by opening a terminal and running:
```bash
mvn -version
```
### 4. Installing MySQL:
- Download and install MySQL Server and MySQL Workbench from the [MySQL page](https://dev.mysql.com/downloads/installer/).
- Configure MySQL with the default username and password (root and your password).
- Create a new database with a name like myappdb:
```sql
CREATE DATABASE myappdb;
```
## Configure_a_Spring_Boot_project_in_Eclipse
### 1. Create a new Spring Boot project:
- Open the Eclipse IDE:
 - Go to **File** -> **New** -> **Spring Starter Project.**
### 2. Select Maven as the project type:
- Enter the project name, group ID (com.example), and artifact ID (myapp).
- Select Java 17 or later.
  
## Using_MySQL_Workbench
### 1. Connect to the MySQL server:
- Open MySQL Workbench and connect to the MySQL server using the root account and password.
### 2. Create a new database:
- Use this command:
```sql
CREATE DATABASE myappdb;
```
### .3 Check data:
- View and modify the database tables that will be created using Spring Boot's automatic database generation.


## Copy_the_project_from_GitHub:

 git clone https://github.com/AndriusJavaU10/spring-react-product-catalog.git
 cd project-name

- Install required dependencies:
 - Go to the project folder: Move the terminal to the cloned project directory:  cd repository
 - Install dependencies: npm install
 - To run a Spring Boot project, execute: mvn spring-boot:run
 - Open the project in the browser: http://localhost:8080/products/all

## Run_the_project_in_Eclipse
### 1. Launch:
- Right-click the Eclipse project and select Run As -> Spring Boot App.
- The project will be launched at http://localhost:8080.
### 2. Connecting to MySQL:
- Make sure the MySQL server is running and the myappdb database is created.
- Spring Boot will automatically connect to the database and start running.

##  Usage
- GET /api/products - Get all products.
- POST /api/products - Add new product (authentication required).
- PUT /api/products/ - Update product by ID (authentication required).
- DELETE /api/products/ - Delete product by ID (authentication required).

- GET /api/customers - Get all customers.(authentication required).
- POST /api/customers - Add new customers.
- PUT /api/customers/ - Update customers by ID (authentication required).
- DELETE /api/customers/ - Delete customers by ID (authentication required).



## Technology
- Java: Version 17
- Spring Boot: Version 3.3.4
- Database: MySQL
- Spring Security
- Spring Web
- SpringDoc OpenAPI Starter WebMVC UI
- Jackson Databind. General data-binding functionality for Jackson: works on core streaming API

## Developers
- Name: Andrius Rikteris
- Email: andrius.rikteris@codeacademylt.onmicrosoft.com
- GitHub: [AndriusJavaU10](https://github.com/AndriusJavaU10/spring-react-product-catalog)
  
