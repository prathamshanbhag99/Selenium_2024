@tag
Feature: Login to saucedemo application

  @tag1
  Scenario: Login successful
    Given Launch the browser
    And Navigate to the url
    When Enter the valid username and password
    And Click on the login button
    Then Verify the login
    And close the browser

  @tag2
  Scenario: Login Unsuccessful
    Given Launch the browser
    And Navigate to the url
    When Enter the invalid username and password
    And Click on the login button
    Then Verify the login
    And close the browser
