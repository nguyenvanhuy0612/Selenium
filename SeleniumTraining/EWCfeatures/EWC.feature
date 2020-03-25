Feature: Agent chat with customer

  #  I want to chat with customer
  @Accept_sequence
  Scenario Outline: Chat together
    Given agent login into Workspace
    #Chat
    When customer send ewc
    And agent accepts
    And agent chat <chat1>
    And customer chat <cus1>
    #New chat
    And customer send ewc
    And agent switch to ewc 2
    And agent accepts
    And agent chat <chat1>
    And customer chat <cus1>
    #Set acw
    And agent switch to ewc 1
    And agent unhold
    And agent close ewc1 va ewc2
    And set ACW
    Then check ACW code displayed on Workspaces
    And print to console

    Examples: 
      | chat1       | cus1      |
      | welcome 111 | hello 111 |
      | welcome 222 | hello 222 |
      | welcome 333 | hello 333 |
