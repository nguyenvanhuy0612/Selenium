Feature: Agent chat with customer

  #  I want to chat with customer
  @Accept_sequence
  Scenario Outline: Chat together
    Given agent login into Workspace
    #Chat
    When customer send ewc with <cusName1>, <cusEmail1> and <skillset1>
    And agent accepts
    And agent chat <chat1> with <cusEmail1>
    And customer with <cusEmail1> chat <cusChat1>
    #New chat
    And customer send ewc with <cusName2>, <cusEmail2> and <skillset2>
    And agent switch to ewc <cusEmail1>
    And agent accepts
    And agent chat <chat2> with <cusEmail2>
    And customer with <cusEmail2> chat <cusChat2>
    #Set acw
    And agent switch to ewc <cusEmail2>
    And agent unhold <cusEmail1>
    And agent close ewc1 and ewc2
    And set ACW <code>
    Then check ACW code displayed on Workspaces
    And print to console

    Examples: 
      | cusName1 | cusEmail1      | skillset1 | cusName2 | cusEmail2      | skillset2 | chat1       | cusChat1  |
      | huy1     | huy1@gmail.com | WC_mcha1  | huy2     | huy2@gmail.com | WC_mcha1  | welcome 111 | hello 111 |

  @Accept_sametime
  Scenario Outline: Chat same time
    Given agent login into Workspace
    #Chat
    When customer send ewc with <cusName1>, <cusEmail1> and <skillset1>
    And customer send ewc with <cusName2>, <cusEmail2> and <skillset2>
    And agent accepts <cusEmail1>
    And agent accepts <cusEmail2>
    And agent chat <chat1> with <cusEmail1>
    And customer with <cusEmail1> chat <cusChat1>
    And agent switch to ewc <cusEmail2>
    And agent chat <chat2> with <cusEmail2>
    And customer with <cusEmail2> chat <cusChat2>
    #Set acw
    And agent switch to ewc <cusEmail2>
    And agent switch to ewc <cusEmail1>
    And agent unhold <cusEmail1>
    And agent close ewc1 and ewc2
    And set ACW <code>
    Then check ACW code displayed on Workspaces
    And print to console

    Examples: 
      | cusName1 | cusEmail1      | skillset1   | cusName2 | cusEmail2      | skillset2   | code | chat1       | chat2       | cusChat1  | cusChat2  |
      | huy1     | huy1@gmail.com | WC_Webchat2 | huy2     | huy2@gmail.com | WC_Webchat3 | TI   | welcome 111 | Welcome 222 | hello 111 | hello 222 |
