package starter.teardown;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.HasTeardown;
import net.serenitybdd.screenplay.RefersToActor;

public class TrackingAbility implements Ability, HasTeardown, RefersToActor {

    public boolean teardownWasExecuted = false;
    private Actor actor;

    public static TrackingAbility track() {
        return new TrackingAbility();
    }

    @Override
    public void tearDown() {
        // actor.attemptsTo() triggers ByteBuddy instrumentation → StepInterceptor → throwNestedExceptions()
        actor.attemptsTo(CleanupTask.cleanup());
        teardownWasExecuted = true; // never reached if bug is present
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T) this;
    }
}