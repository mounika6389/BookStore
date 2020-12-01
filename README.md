## Bookstore API Testing using Rest-Assured, Java & TestNG

Implemented a BDD automation testing framework using following:
 - **Programming language** : Java
 - **Build tool** : Maven
 - **BDD** : Cucumber
 - **Library for REST** : REST-assured 
 - **Testing framework** : Testng
 - **Reporting** : Allure

1. Implemented all the http methods GET, POST, PUT, DELETE.
2. All the dependencies and plugins are defined in **pom.xml**
3. Defined all my scenarios in Feature file (__test.feature__).
4. Defined all my Step Definitions in _**Steps**_ directory with all the methods in it.
5. **CucumberJvm** is used as runner class
6. The project can be run from Maven using maven commands in the following order for testing and generating reports.:
 ```
 mvn clean 
 mvn test 
 mvn site
 ```
and also can be run from Maven Lifecycle options (clean, test, site)

7.Integrated Allure reporting in the framework, a detailed report will be generated here at this location
   **target/site/allure-maven-plugin.html** 

8.This feature processes the execution in the following order:
  <ul>
  <li> As a first step a user is created </li>
  <li>Then a new book is added using POST method to the collection</li>
  <li>Replace the existing book with a new book using PUT method</li>
  <li>Get the details of book in collection using GET</li>
  <li>Deleting  a book from the collection using DELETE</li>
  <li>Then as a last step the created user is deleted</li>
  </ul>
9. Any additional scenarios can also be added in future without disturbing the existing code (reusability concept).

![Allure Report](Allure-Report.png?raw=true "Allure report with all scenarios")
