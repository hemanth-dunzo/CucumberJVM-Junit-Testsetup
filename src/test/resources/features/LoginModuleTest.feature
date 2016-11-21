Feature: As an Etna User I should be able to login as a Super User, General User, APA, Retail User, Commercial Customer

  @login @SeverityLevel.BLOCKER @TestCaseId("TC_002")
  Scenario Outline: login functionality
    Given I am in Home Page
    When I click on "login" link in home page
    And I enter username as "<emailId>"
    And I enter password as "<password>"
    And I click on login button
    Then I verify welcome message is displayed with the "<nameOfTheUser>"
    And I click on "logout" link in home page

    Examples: 
      | emailId                           | password   | nameOfTheUser     |
      | hemanth.bs@unilogcorp.com         | hemanth123 | Super User        |
      | hemanthgeneraluser@unilogcorp.com | hemanth123 | General User User |
