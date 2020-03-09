# internship-apps
This is a repo for some of the study and projects I worked on during my internship.

# 1. 
# -- Coronavirus Tracker app, from JavaBrains: https://www.youtube.com/watch?v=8hjNG9GZGnQ
Using:
      Spring Boot (Maven, Spring Web, Spring DevTools, Thymeleaf UI)
      
# Overview:
This app provides information about the global reported cases of COVID19. It updates daily, and breaks down number of reported cases by region/country and shows the difference in each to the number of cases reported the previous day.

      
# To run this app:
      Clone the repo to your local machine
      Import the project (Maven existing projects)
      Ensure Port 8080 is not in use
          ... if it is in use - Run CMD (Windows) "Run as Administrator"
                              - Issue netstat -aon command
                              - Identify PID (Process ID using the port)
                              - Issue taskkill /PID *pid number*
      Now run the program in Eclipse IDE
      Open a web browser and visit localhost:8080

# Key learnings / functionality
      Introduction to Spring Boot
      HTTP Request/Reponse & accessing a remote data source (csv file)
      Constructing a basic Service
      Spring Annotations and use of Autowiring
      Basic MVC with Spring - use of HomeController & model classes
      Basic UI development using Thymeleaf
      
