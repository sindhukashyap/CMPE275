LAB3 - PERSISTENCE


SJSU ID :009438377
Name : Swathi Manthalapu Rameshbabu
Email : swathi6489@gmail.com


SJSU ID : 009984052
Name: Sindhu Nagaraj Kashyap
Email : sindhunagaraj.kashyap@sjsu.edu


Installation details:


1. Run the create queries in mysql from the file “Create queries.txt”
2. Import the project RestPersistenceApplication as a maven project in your STS or Eclipse IDE.
3. Clean the application once. Project-> clean and Project->install
4. Right click project -> run as-> Spring Boot application.
5. Open Postman in your Chrome browser and start hitting the URLs for the 10 APIs in order to see the json output.


Implementation details :


1. We have used Spring boot to build and run the REST APIs and Chrome plugin POSTMAN for testing the API URLs and viewing the json outputs.
2. We have used a local instance of MYSQL database and Hibernate ORM Mapping using XMLs to store the persistent data.
3. The controller classes for REST APIs are in the package : edu.sjsu.cmpe275.lab3.controller
4. The Database Handlers for managing database operations are in the package : edu.sjsu.cmpe275.lab3.handler
5. The POJO resource classes are in the package : edu.sjsu.cmpe275.lab3.resource
6. The Util class for managing connection to the database using Hibernate are in the package : edu.sjsu.cmpe275.lab3.util
7. The Hibernate config file, hibernate.cfg.xml is under: src-->main-->resources
8.  The Hibernate mapping hbm files for mapping using ORM is under: src-->main-->resources-->hibernatemappings