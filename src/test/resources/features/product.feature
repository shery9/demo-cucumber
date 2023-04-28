Feature: Product API Feature

Scenario: Verify the response contains expected data
  Given I send a GET request to "https://dummyjson.com/products/1"
  When I receive a response
  Then the response status code should be 200
  And the response should contain the following fields:
    | id| title      |brand |price|
    | 1 | iPhone 9   |Apple |549  |
