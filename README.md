###HotelAdministrator
[Final project for GFL Java Beginner Course]

Spring-based application to simulate the work of an application for managing a hotel. The back-end was developed using validators for form validation, services, and repository architectures, incorporating design patterns.

###Technologies
The technologies used in this application are:

*Spring framework with web addon
*Jakarta Validation for form input validation
*Thymeleaf and Bootstrap for handling the display of the view
*PostgreSQL for the database
*JPA as an ORM tool
###Usage
To launch the application, the user needs to install an IDE and Tomcat Server of version 10+.

###Implementation Details
One of the existing controller implementations handles the response for requests on the corresponding pages. The controller then uses a service to handle the business logic of the request and, if needed, passes it to a repository object. The repository interacts with the database itself using default implementations of CRUD operations from the JPA interface or manually created ones.

###Credits
Project created by Khytrych Stanislav (TheDarkRabbit).
