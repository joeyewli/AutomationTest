@shoppingCart
Feature: testing adding, removing items from Cart
  @addingItems  @logout
  Scenario: Adding 4 item(s) will increase the cart by 4
    Given I logged in as Standard_User
    When I added 4 item(s)
    Then there will be 4 item(s) in the cart

  @addingItems
  Scenario: After adding an item, I can remove it on the same page
    Given I logged in as Standard_User
    When I added 1 item(s)
    Then I can see the item has 1 remove item

  @addingItems  @removingItems
  Scenario: Removing 2 item(s) will decrease the cart by 2
    Given I logged in as Standard_User
    When I added 4 item(s)
    And I remove 2 item(s)
    Then there will be 2 item(s) in the cart

  @donttest
  Scenario: Items added are visible in my cart
  @donttest
  Scenario: Clicking on Item will bring me to the item's page
  @donttest
  Scenario: