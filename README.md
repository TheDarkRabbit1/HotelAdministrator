# HotelAdministrator
[Final project for gfl java beginner course]
Spring based application to simulate work of application for managing hotel. The back-end was developed with using validators for form, 
services and repository architectures with using desing patterns.
##Features
Pages rooms and guests which represent a table of coresponding data in them have filters for ease of search. Filters are made using javascript.
##Technologies
*The application was based on Spring framework with web addon. 
*Jakarta Validation to validate forms input
*Thymeleaf and BootStrap for handling display of the View.
*PostgreSQL for DataBase
*JPA as an ORM tool.
##Usage
For launching application user needs to instal IDE an Tomcat Server of version 10+.
##Imlementation details:
One of existing controller implementations is handling responce for request on corresponding page. The Controller then uses
service to handle business logic of the request and pass if needed to repository obj, which interacts with the database itself,
using default implementations of crud operations from JPA interface or ones created manually.
##Credits
Project was created by Khytrych Stanislav (TheDarkRabbit).
