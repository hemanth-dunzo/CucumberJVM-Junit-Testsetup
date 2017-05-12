Feature: As a Developer I should be able to attach files

  @attachment @SeverityLevel.BLOCKER @TestCaseId("TC_001")
  Scenario Outline: sample attachment tests
    Given I am in Home Page
    And I attach "<fileformat>" files

    Examples:
      | fileformat |
      | xlsx       |
      | csv        |
      | txt        |