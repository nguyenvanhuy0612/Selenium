Feature: Agent chat with customer

  #  I want to chat with customer
  @Accept_sequence
  Scenario: Chat together
    Given Agent login Workspaces and accepts two incoming EWC contacts
    When Switch between two contacts to chat with customers
    And Set ACW then closes contacts
    Then Check ACW code displayed on Workspaces
    And print to console
#  @Accept_same_time
#  Scenario Outline: Chat same time
