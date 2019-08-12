# cleaner-partnet-api
  This project is a API for workforce optimization tool for cleaning partners.
  
It is necessary to use maven to run the project http://maven.apache.org
	
	mvn spring-boot:run
	
Then access in your browser http://localhost:8080
	
# Technologies and Resources
	- Spring Boot
	- Java 11
	- Lombok
	- Junit

# Endpoints

# Save and shorten Url
	
* **URL**
	- api/optimization
* **Method**
	- GET
* **Params**
	- {rooms: [34, 45, 12, 34]}, senior: 10, junior: 9}
* **Success**
	- code 200
* **Invalid Data**
  - code 400
* **Communication Problem**
	- code 502
	


	
