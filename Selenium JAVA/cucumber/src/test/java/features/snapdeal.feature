@tag
Feature: Snapdeal Product

  @tag1
  Scenario: Search and Add Product to Cart - Successful
    Given launch the browser
    And navigate to the Snapdeal website
    When search for a specific product that exists
    Then verify the product is found
    And add the product to the cart
    And print the cart value
    And close the browser

  @tag2
  Scenario: Search and Add Product to Cart - Unsuccessful
    Given launch the browser
    And navigate to the Snapdeal website
    When search for a specific product that does not exist
    Then verify the product is found
    And close the browser
