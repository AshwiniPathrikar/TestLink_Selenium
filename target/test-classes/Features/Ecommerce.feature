@tag
Feature: Feature to check integration of TestLink with Selenium  with demo site 
   
    Scenario: Verify user able to send a message
    Given Launch the application
    When I navigate to contact page
    And I enter all data
    Then Verify message is sent 
    
    Scenario: Verify user able to add product to cart
    Given Launch the application
    When I navigate to product page
    Then I add product to the cart  