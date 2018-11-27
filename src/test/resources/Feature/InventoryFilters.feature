@filter
Feature: Test filters in Inventory page

  Scenario Outline: I can filter items
    Given I logged in as Standard_User
    When I filter to <option>
    Then <option> - filter option is selected
    And <Result> is passed

  @donttest
    Examples:
      |option|Result|
      |Name (A to Z)|name asc|
      |Name (Z to A)|name desc|
      |Price (low to high)|price asc|
      |Price (high to low)|price desc|