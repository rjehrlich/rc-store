# RC Store API

* <a href="project-description"> Project Description</a>
* <a href="tools-technologies"> Tools & Technologies</a>
* <a href="general-approach"> General Project Approach</a>
* <a href="unsolved-problems"> Unsolved Problems / Hurdles</a>
* <a href="installation"> Installation Instructions/ Dependencies</a>

## Project Description

## Tools & Technologies
- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
- ![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
- ![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

## General Project Approach

### User Stories
* As a User, I want to create a profile so that I can save my personal information and preferences
* As a User, I want to be able to view and edit my profile information, including my name, email address, and password.
* As a User, I want to be able to view and save products in my profile.
* As a User, I want to be able to delete out of season products.
#### Nice to have in the future:
* As a user, I want to be able to add products to my cart and checkout securely.
* As a user, I want to receive email notifications about my order status and shipping updates.

### ERD Diagram

### Project Plan

### Endpoint Mappings

#### Product Controller
| HTTP Methods 	| Full URL                             	|           URL          	|     Functionally     	|
|--------------	|--------------------------------------	|:----------------------:	|:--------------------:	|
| GET          	| http://localhost:8080/api/products/  	| /products/             	| List of all products 	|
| GET          	| http://localhost:8080/api/products/1 	| /products/{productId}/ 	| One product by ID    	|
| POST         	| http://localhost:8080/api/products/  	| /products/             	| Create new product   	|
| PUT          	| http://localhost:8080/api/products/  	| /products/{productId}/ 	| Save products        	|
| DELETE       	| http://localhost:8080/api/products/  	| /products/{productId}/ 	| Remove products      	|

#### User Controller

| HTTP Methods 	| Full URL                                  	|          URL         	|        Functionally       	|
|--------------	|-------------------------------------------	|:--------------------:	|:-------------------------:	|
| POST         	| http://localhost:8080/auth/users/login    	| /auth/users/login    	| Logs user in              	|
| POST         	| http://localhost:8080/auth/users/register 	| /auth/users/register 	| Registers/ creates a user 	|


## Unsolved Problems / Hurdles

## Installation Instructions/ Dependencies
