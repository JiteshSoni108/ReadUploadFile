# ReadUploadFile
This project will have method to read , upload and delete record of uploaded data from file.

In this project, We are using in memory H2 DB for saving record data of file. 
Note : spring.jpa.hibernate.ddl-auto=create-drop // we are creating adn droping tables in every run of application.


Validation :
- Checking fileId column in file is not empty and we are updating duplicate fileId with new data.
- Checking updated Time cloumn as per ISO 8601 standard.


Swagger ui :  http://localhost:8081/swagger-ui.html#/file-reader-controller
Sample : File is present in test/resource folder.


In project , We have added Junit for all end points:
POST  --> /upload
GET -->  /fetch/{id}
DELETE --> /delete/{id}

