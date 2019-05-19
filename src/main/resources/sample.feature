Feature: featureName
  Description

  Background: backgroundName
    When some background check

  Scenario: scenarioName
    When some "field" has "value"
    Or some "field" has "anotherValue"
    Then some action

  Scenario: scenario with list of fields
    When some "field" has "value"
    Then list of fields
      | fieldName1 | errorMessage |
      | fieldName2 | errorMessage |