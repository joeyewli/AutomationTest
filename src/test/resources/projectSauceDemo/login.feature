Feature: Login
  logging in scenarios for all Users

      Scenario: PM can log into Helix
      Given I am a Project Manager
      And I'm on SDL SIT site
      When I enter my username project.manager
      And my password Appian2018
        And Press the login button
      Then I can login into my home page = SDL main site



