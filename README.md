# Product catalog Backend

## Content
- [Installation](#installation)
- [Usage](#usage)
- [Common_commands](#common_commands)
- [Technology](#technology)
- [Developers](#developers)

## Installation

To run the project on your local machine, follow these steps:

### 1. **Copy the project** from GitHub:

 git clone https://github.com/your-name/project-name.git
 cd project-name

### 2. Install required dependencies:
- Go to the project folder: Move the terminal to the cloned project directory:  cd repository
- Install dependencies: npm install
- To run a Spring Boot project, execute: mvn spring-boot:run
- Open the project in the browser: http://localhost:8080/products/all

##  Usage
- GET /api/products - Get all products.
- POST /api/products - Add new product (authentication required).
- PUT /api/products/ - Update product by ID (authentication required).
- DELETE /api/products/ - Delete product by ID (authentication required).

- GET /api/customers - Get all customers.(authentication required).
- POST /api/customers - Add new customers.
- PUT /api/customers/ - Update customers by ID (authentication required).
- DELETE /api/customers/ - Delete customers by ID (authentication required).

## Common_commands
```json
 "scripts": {
    "preinstall": "npx npm-force-resolutions",
    "start": "react-scripts --openssl-legacy-provider start",
    "build": "react-scripts --openssl-legacy-provider build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  }
```


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
  
