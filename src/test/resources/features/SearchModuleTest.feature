Feature: As an Etna User I should be able to search for a pn,mpn,upc etc..

  @search
  @TestCaseId("TC_003")
  Scenario Outline: Search functionality
  Given I am in Home Page
  When I search for "<searchText>"
  And I click on search go button
  Then "<attribute>" should be displayed with value "<searchText>"

  Examples:
      |         attribute                 | searchText  |
      |         partNumber                | test1234    |
      |         mpn                       | TestMPN     |

