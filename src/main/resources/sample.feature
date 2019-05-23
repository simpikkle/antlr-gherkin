#Sample feature
Feature: featureName
  Description

  #Background comment
  Background: backgroundName
    When some background check

    # Comment before tag
    @Error(errorCode)
  Scenario: scenarioName
      # Step comment
    When field "country" contains "US"
      or field "document" contains "Passport"
    Then following fields are required
        #fieldName #ErrorMessage
      | taxId | Tax id is required for US citizens |
      | address | Address is required for US citizens |

    @Error(errorCode)
  Scenario: scenarioName
    When field "country" contains "US"
      or field "document" contains "Passport"
    Then following fields are required
      | taxId | Tax id is required for US citizens |
      | address | Address is required for US citizens |
      #TODO
