# Address Parser

[![Build Status](https://travis-ci.org/ClaudioNazareth/address-parser.svg?branch=master)](https://travis-ci.org/ClaudioNazareth/address-parser)
[![codecov](https://codecov.io/gh/ClaudioNazareth/address-parser/branch/master/graph/badge.svg)](https://codecov.io/gh/ClaudioNazareth/address-parser)

![javaversion](https://img.shields.io/badge/Java-8-yellowgreen.svg)
![springboot](https://img.shields.io/badge/spring%20boot-1.5.9.RELEASE-orange.svg)


Scenario for this application:

An address provider returns addresses only with concatenated street
names and numbers. Our own system on the other hand has separate
fields for street name and street number.

**Input:** _string of address_
**Output:** _string of street and string of street-number_

1. **Write a simple program that does the task for the most simple
cases, e.g.**
  * a. “Winterallee 3” --> {“ Winterallee”, “3”}
  * b. “Musterstrasse 45” --> { “Musterstrasse”, “45”}
  * c. “Blaufeldweg 123B” --> {“Blaufeldweg”, “123B”}
  
2. C**onsider more complicated cases**
  * a. “Am Bächle 23” --> {“Am Bächle”, “23”}
  * b. “Auf der Vogelwiese 23 b” --> {“Auf der Vogelwiese”, “23 b”}

3. **BONUS: Consider other countries (complex cases)**
  * a. “4, rue de la revolution” --> {“rue de la revolution” ,"4”}  
  * b. “200 Broadway Av” --> “Broadway Av",“200”}
  * c. “Calle Aduana, 29” --> {“Calle Aduana”, “29”}
  * d. “Calle 39 No 1540” --> {“Calle 39”, “No 1540”}
  
Instructions
============
----------------------------------------------------------------------------------------------------
  
To compile and run this project you will need:

  * **Java 8** (JDK8)
  * **Maven 3.0.5** or grater
  
 
To start the application use the command bellow   

```bash
mvn spring-boot:run
```
**The base path for the endpoins is**: /v1

**Application port** :8080  

To run all unit and integration tests use the command bellow   

```bash
mvn test
```  


APIs - Swagger
==============
----------------------------------------------------------------------------------------------------
To document the APIs I have used Swagger.

Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS),
enabling development across the entire API lifecycle, from design and documentation, 
to test and deployment.

Here you can read more about [Swagger](https://swagger.io/)

To **see and test** the APIs go to path **/swagger-ui.html** (ex: _http://localhost:8080/swagger-ui.html_)



APIs - Swagger
==============

#### Clean Code

Clean Code is a development style that focuses on the **ease of writing, reading and maintaining code**.

**Robert C. Martin**, in his book, "**Clean Code: A Handbook of Agile Software Craftsmanship**," 
states that the reading to writing ratio of the code is 10: 1. Therefore, a well-written code that 
facilitates reading is not only desirable, **but necessary in the current scenario**.

For this project I've used some clean code principles like :

* **Names are very important** : 
  * **Be precise**: _we must pass the central idea of ​​our variable or method, without turning, being concise 
    and direct_.
  * **Do not be afraid of big names**: _a very descriptive name, even if it is large, will enable a 
    better understanding and subsequent maintenance of the code_.
    
* **Comments only the necessary**
  * _Comment what is needed and only what is necessary. Codes are constantly modified, while comments rarely. 
    Thus, it is common for a comment to cease to have meaning, or worse, to pass on a false meaning after some time_.    

#### Clean Architecture

The Clean Architecture leverages well-known and not so well-known concepts, rules, and patterns, 
explaining how to fit them together, to propose a standardised way of building applications.

The core objectives behind Clean Architecture are the same as for Ports & Adapters (Hexagonal)
 and Onion Architectures:

* Independence of tools;
* Independence of delivery mechanisms;
* Testability in isolation.

In the [post](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) about 
Clean Architecture was published, this was the diagram used to explain the global idea:

![cleanarchitecture](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

**_The best of clean architecture is its use an software design technique to understand and solve 
complexity is Domain Driven Design (DDD). Domain Driven Design advocates modeling based on the 
reality of business as relevant to our use cases._** 
