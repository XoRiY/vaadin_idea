# language: en
Feature: Get users by id

  Scenario: calls web service to get a user by its id
    Given a user exists with given id
    When a rest api retrieves the user
    Then the status code is 200
    And response includes the following
      | totalItems | 1             |
      | kind       | books#volumes |
  # And response includes the following in any order
  #   | items.volumeInfo.title 		| Steve Jobs		  |
  #   | items.volumeInfo.publisher 	| Simon and Schuster  |
  #   | items.volumeInfo.pageCount 	| 630			      |