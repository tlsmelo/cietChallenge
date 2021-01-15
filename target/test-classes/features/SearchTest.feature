@cietChallenge @web
Feature: Searching on Questions Database

  ### Scenario 1 ###
  Scenario: Searching for an inexistent Question on Questions Database
        Given I navigate to the Questions Database search page
        And I input Science: Computers in the search field
        When I click on the Search button
        And I should see No Questions Found message

  ### Scenario 2 ###
  Scenario: Searching for a Category on Questions Database and verifying the items quantity
    Given I navigate to the Questions Database search page
    And I select the Type to Category
    And I input Science: Computers in the search field
    When I click on the Search button
    Then I should see 25 items in the table results
    And I should see the pagination control active

  ### Scenario 3 ###
  Scenario: Searching for an User on Questions Database and verify the quantity of questions verified
    Given I navigate to the Questions Database search page
    And I select the Type to User
    And I input Pikabush in the search field
    When I click on the Search button
    Then I should see 14 items in the table results
    When I click on the first row user link
    And I should match the verified questions value with returned in search