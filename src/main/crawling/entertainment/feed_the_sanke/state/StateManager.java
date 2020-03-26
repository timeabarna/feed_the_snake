package crawling.entertainment.feed_the_sanke.state;

public class StateManager {

    private static State currentState = null;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        StateManager.currentState = currentState;
    }
}
