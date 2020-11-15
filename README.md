I have created a BDD framework with Cucumber, restAssured,Java,Testng and Allure reporting.

1. I have used all the http methods GET, PUT,POST,DELETE.
2. I have defined all my scenarios in Feature file.
3. I have defined all my Step Definitions in Steps directory with all the methods in it.
4. CucumberJvm is used as runner class
5. The project can be run from Maven using maven commands like mvn clean, mvn test and mvn site for generating reports.
6. I have integrated Allure reporting with cucumber where a detailed report will be generated.
7. This feature contains the scenarios in following order:
  <ul>
  <li> As a first step a user is created </li>
  <li>Then a new book is added using POST method to the collection</li>
  <li>Replace the existing book with a new book using PUT method</li>
  <li>Getting all the books in collection using GET</li>
  <li>Deleting  a book from the collection using DELETE</li>
  <li>Then as a last step the created user is deleted</li>
  </ul>
8. Any additional scenarios can also be added in future without disturbing the existing code. 


TO DO 

- Parameterise step definitions
- Assertion as new step