##Basic

This application was built as part of the coursework for Enterprise Application Development course at San Jose State University. A set of REST APIs are built to manage entities and relationships in a mini gaming website. 

##Usage 

There are two primary types of entities: Players and Sponsors. They have the following relationships and constraints:
1. Opponents: if two players play against each other, they are opponents. The opponent relationship is s​ymmetric in that is Alice is an opponent of Bob, then Bob is also an opponent of Alice. A player can have zero or more opponent players.
2. Sponsors: a player can optionally be sponsored by an external sponsor. D​ifferent players can have the s​ame​ sponsor.
3. The firstname, lastname, and email fields are required for any player. Emails have to be u​nique​ across players.
4. The name field is r​equired​ for any Sponsor, and does n​ot​ need to be unique.

##Features 
The application supports the following APIs:

1. Create a player
2. Get a player
3. Update a player
4. Delete a player
5. Create a Sponsor
6. Get a Sponsor
7. Update a Sponsor
8. Delete a Sponsor
9. Add an opponent
10. Delete an opponent

##Implementation details :

1. Spring boot is used to build and run the REST APIs and Chrome plugin POSTMAN for testing the API URLs and viewing the json outputs.
2. Local instance of MYSQL database and Hibernate ORM Mapping using XMLs is used to store the persistent data.

##Tools
1. Eclipse with Spring Boot plug-in
2. mysql Workbench to execute queries
3. Postman REST Client for testing the REST APIs
