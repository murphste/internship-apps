# internship-apps
This is a repo for some of the study and projects I worked on during my internship.

# 1. 
# -- Coronavirus Tracker app, from JavaBrains: https://www.youtube.com/watch?v=8hjNG9GZGnQ
Using:
      Spring Boot (Maven, Spring Web, Spring DevTools, Thymeleaf UI)
      
# App Overview:
This application reports the number of confirmed cases of COVID19 globally, showing a breakdown by country/region and the latest total of reported cases, plus the number of cases confirmed since the previous day. It updates once daily.
      
# To run this app:
      Clone the repo to your local machine
      Import the project (Maven existing projects)
      Ensure Port 8080 is not in use
          ... if it is in use - Run CMD (Windows) "Run as Administrator"
                              - Issue netstat -aon command
                              - Identify PID (Process ID using the port)
                              - Issue taskkill /PID *pid number* /F
      Now run the program in Eclipse IDE
      Open a web browser and visit localhost:8080

# Key learnings / functionality
      Introduction to Spring Boot
      HTTP Request/Reponse & accessing a remote data source (csv file)
      Constructing a basic Service
      Spring Annotations and use of Autowiring
      Basic MVC with Spring - use of HomeController & model classes
      Basic UI development using Thymeleaf
      
---------------------------
# 2. 
# -- Basic REST API example, standard GET, POST, PUT, DELETE functionality
Using:
      Spring Boot
      
# App Overview:
This is a great example / template of a basic REST API using Spring boot, set up to provide the basic HTTP operations on a hardcoded list of "topics", but can of course be adapted for anything. This is a "starting point" API, as it has no database connection or data source, only using a hardcoded list. DB connected version, with full CRUD functionality, to follow.
      
# To run this app:
      Clone the repo to your local machine
      Import the project (Maven existing projects)
      Ensure Port 8080 is not in use
          ... if it is in use - Run CMD (Windows) "Run as Administrator"
                              - Issue netstat -aon command
                              - Identify PID (Process ID using the port)
                              - Issue taskkill /PID *pid number* /F
      Now run the program in Eclipse IDE
      Open Postman and type in URL endpoints (dont foregt to set header to "application/json")
      Try out the various HTTP methods to test, and observe the responses

# Key learnings / functionality
      Introduction to Spring Boot
      Spring Annotations and use of Autowiring
      Basic MVC with Spring - use of HomeController & model classes
      REST API template
      

---------------------------
# 3. 
# -- Basic REST API expanded with basic, EMBEDDED Apache DerbyDB connection
Using:
      Spring Boot

# Important Note:
When run, the app may show errors (in blue) in the console - related to JPA/JDBC/Hibernate. From researching StackOverflow it was commented that these errors are a known bug with Spring Boot 2+. The application still works as it should so you can ignore these.
 
# App Overview:
This builds on the previous "course-api" example, by instead of using data from a hardcoded list, we add an embedded database (Apache DerbyDB) to the project classpath. This is done by including it from the initialiser at the beginning of the project, and as can be seen by the dependancy added to the pom.xml. This is the simplest way of working with a database connection with Spring. By using model class annotations & a Spring standard "CrudRepository" interface, the app can persist data to the database and perform CRUD operations on it. 
      
# To run this app:
      Clone the repo to your local machine
      Import the project (Maven existing projects)
      Ensure Port 8080 is not in use
          ... if it is in use - Run CMD (Windows) "Run as Administrator"
                              - Issue netstat -aon command
                              - Identify PID (Process ID using the port)
                              - Issue taskkill /PID *pid number* /F
      Now run the program in Eclipse IDE
      Open Postman and type in URL endpoints (dont foregt to set header to "application/json")
      Try out the various HTTP methods to test, and observe the responses

# Key learnings / functionality
      Introduction to Spring Boot
      Including an embedded database (Apache Derby) on the class path
      ORM Mapping - @ManyToOne used here + multilevel categories
      Spring Annotations - @Entity, @Id, @Autowire etc.
      Spring "CrudRepository" interface & associated CRUD methods (very important)
      REST API template
      
---------------------------
# 4. 
# -- JUnit 5 Basics https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o
Using:
      JUnit5 (Jupiter), Spring Boot
      
# App Overview:
This is a simple example of JUnit5 integration with Spring Boot to test a simple utility class (MathUtils.java) to show some of the JUnit testing annotations and how to structure the order of tests in the actual testing class.
      
# To run this app:
      Clone the repo to your local machine
      Import the project (Maven existing projects)
      Now run the program in Eclipse IDE - "Run As : JUnit test"
      Observe the test results in the JUnit panel & observe code comments      

# Key learnings / functionality
      Introduction to JUnit5
      Integrating JUnit5 with Spring project (via pom.xml dependencies)
      Basic JUnit5 testing annotations
      Introduction to TDD (Test Driven Development)
