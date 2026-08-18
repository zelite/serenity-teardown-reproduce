package starter.teardown;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class CleanupTask implements Task {

    public static CleanupTask cleanup() {
        return new CleanupTask();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println(">>> cleanup task body executed");
    }
}