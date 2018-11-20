Feature: Sauce Demo site
  @login
  Scenario: Standard user can log in with correct credentials
    Given I am a standard_user
    And I am on https://www.saucedemo.com/
    When I enter my username standard_user
    And I enter my password secret_sauce
    And press the LOGIN button
    Then I will login to a page https://www.saucedemo.com/inventory.html
    And the Title = Swag Labs


  Scenario Outline: User will get error when entering incorrect credentials
    Given I am a standard_user
    And I am on https://www.saucedemo.com/
    When I enter my username <username>
    And I enter my password <password>
    And press the LOGIN button
    Then I will login to a page https://www.saucedemo.com/
    And I will get an error message <error>

    @login
    Examples:
    |username| password | error|
    |standard_user | test | Epic sadface: Username and password do not match any user in this service|
    |locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.|
    |locked_out_user | test | Epic sadface: Username and password do not match any user in this service|
    |problem_user | test | Epic sadface: Username and password do not match any user in this service|

  @untested
  Scenario: Logged in user can logout
    Given I logged in as Standard_User
    When I press logout
    Then I return to https://www.saucedemo.com/

