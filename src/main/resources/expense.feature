
Feature:
    As a user, I can add and view expense so that I know how much money I have.

Background:
    Given a user have balance 500 baht in account book

Scenario: Record expense,show total expense and total balance
    When user record expense 200 baht
    Then user account book balance is 300 baht and total expense is 200 baht

Scenario: Record expense,show total expense and totalbalance
    When user record expense 300 baht
    Then user account book balance is 200 baht and total expense is 300 baht