
Feature:
    As a user, I can add and view income so that I know how much money I have.

Background:
    Given a user have balance 500 baht in account book

Scenario: Record income and show total income
    When user record income 200 baht
    Then user account book balance is 700 baht and total income is 200 baht

Scenario: Record income and show totalbalance
    When user record income 500 baht
    Then user account book balance is 1000 baht and total income is 500 baht


