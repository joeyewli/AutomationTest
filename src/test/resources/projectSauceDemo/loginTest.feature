Feature: Sauce Demo site
  @login
  Scenario: Standard user can log in with correct credentials
    Given I am a standard_user
    And I am on the Login page
    When I enter my username standard_user
    And I enter my password secret_sauce
    And press the LOGIN button
    Then I will login to the inventory page
    And the Title = Swag Labs


  Scenario Outline: User will get error when entering incorrect credentials
    Given I am a standard_user
    And I am on the Login page
    When I enter my username <username>
    And I enter my password <password>
    And press the LOGIN button
    Then I will remain at the Login page
    And I will get an error message <error>

    @login @fixthis
    Examples:
    |username| password | error|
    |standard_user | test | Epic sadface: Username and password do not match any user in this service|
    |locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.|
    |locked_out_user | test | Epic sadface: Username and password do not match any user in this service|
    |problem_user | test | Epic sadface: Username and password do not match any user in this service|

  @logout
  Scenario: Logged in user can logout
    Given I logged in as Standard_User
    When I press logout
    Then I will return to the Login Page


  Scenario: Inventory has 6 items
    Given I logged in as Standard_User
    Then I will see 6 inventory items


  @addingItems @shoppingCart
  Scenario: Adding 4 item(s) will increase the cart by 4
    Given I logged in as Standard_User
    When I added 4 item(s)
    Then there will be 4 item(s) in the cart

  @addingItems @shoppingCart
  Scenario: After adding an item, I can remove it on the same page
    Given I logged in as Standard_User
    When I added 1 item(s)
    Then I can see the item has 1 remove item

  @addingItems @shoppingCart @removingItems
  Scenario: Removing 2 item(s) will decrease the cart by 2
    Given I logged in as Standard_User
    When I added 4 item(s)
    And I remove 2 item(s)
    Then there will be 2 item(s) in the cart


  @untested
  Scenario: Test
    Given I logged in as Standard_User
    Then Test

    @donttest
  Scenario Outline: I can filter items
    Given I logged in as Standard_User
    When I filter to <option>
    Then <option> - filter option is selected
    And <Result> is passed


    Examples:
      |option|Result|
      |Name (A to Z)|name asc|
      |Name (Z to A)|name desc|
      |Price (low to high)|price asc|
     |Price (high to low)|price desc|
  @donttest
  Scenario: Items added are visible in my cart
  @donttest
  Scenario: Clicking on Item will bring me to the item's page
  @donttest
  Scenario:
