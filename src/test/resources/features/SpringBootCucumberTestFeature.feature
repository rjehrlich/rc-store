Feature: RestApi Functionalities

  Scenario: User able to add and remove product
    Given a list of products are available
    When i add a product to my productList
    Then the product is added
    When i find a product by id
    Then the product is displayed
    When i remove product from my productList
    Then the product is removed



