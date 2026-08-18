Feature: Teardown should run cleanly after a test failure

 Scenario Outline: Teardown is interrupted when test fails in data-driven context
    Given the actor Dent has a teardown ability
    When the actor performs a step that fails
    Then this step should never be reached

   Examples:
     | Name |
     | Dent |
     | Random |