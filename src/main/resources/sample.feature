Feature: featureName
  Description

  Background: backgroundName
    When some background check

  Scenario: scenarioName
    When field "country" contains "US"
      or field "document" contains "Passport"
    Then following fields are required
      | taxId | Tax id is required for US citizens |
      | address | Address is required for US citizens |