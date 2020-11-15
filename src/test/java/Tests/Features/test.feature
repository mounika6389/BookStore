Feature: Testing Bookstore API - GET, POST, PUT, DELETE methods

  Scenario: Creating a user
    Given create a user with valid username and password

  Scenario: Add a new book to the collection - POST Positive scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Add new book with valid ISBN "9781449337711" to the collection
    And Check if the status code is "201"

  Scenario: Add a new book to the collection - POST Negative scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Add new book with invalid ISBN "9781449331819" to the collection
    And Check if the status code is "400"

  Scenario: Replace a new book in place of existing book - PUT Positive scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Replace existing book ISBN "9781449337711" with new book ISBN "9781593275846" in the collection
    And Check if the status code is "200"

  Scenario: Replace a new book in place of existing book - PUT Negative scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Replace existing invalid book ISBN "9781449337712" with new book ISBN "9781593277574" in the collection
    And Check if the status code is "400"

  Scenario: Fetch book details - GET Positive scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Get book details in the collection with valid isbn "9781449325862"
    And Check if the status code is "200"

  Scenario: Fetch book details - GET Negative scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Get book details in the collection with invalid isbn "9781449325864"
    And Check if the status code is "400"

  Scenario: Delete a book from the collection - DELETE Positive scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Delete a book from the collection with valid isbn "9781593275846"
    And Check if the status code is "204"

  Scenario: Delete a book from the collection - DELETE negative scenario
    Given I enter the Username and Password
    And Generate a token for that user
    Then Delete a book from the collection with invalid isbn "9781593277575"
    And Check if the status code is "400"

  Scenario: Delete the user created
    Given I enter the Username and Password
    And Generate a token for that user
    Then Delete the user
    And Check if the status code is "204"

