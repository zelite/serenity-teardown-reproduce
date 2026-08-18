package starter.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import starter.teardown.TrackingAbility;

public class TeardownBugStepDefinitions {

  private Actor actor;

  @Before
  public void setStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @After
  public void teardown() {
    OnStage.drawTheCurtain();

    TrackingAbility ability = actor.abilityTo(TrackingAbility.class);
    assertThat(ability.teardownWasExecuted).as("tearDown() should have been called").isTrue();
    System.out.println("Reached end of teardown");
  }

  @Given("the actor {word} has a teardown ability")
  public void actorHasTeardownAbility(String name) {
    actor = OnStage.theActorCalled(name).whoCan(TrackingAbility.track());
    actor.entersTheScene();
  }

  @When("the actor performs a step that fails")
  public void actorPerformsFailingStep() {
    throw new RuntimeException("Intentional test failure");
  }

  @Then("this step should never be reached")
  public void thisStepShouldNeverBeReached() {}
}
