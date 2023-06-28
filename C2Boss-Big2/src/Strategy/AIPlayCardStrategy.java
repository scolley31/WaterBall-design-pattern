package Strategy;


public abstract class AIPlayCardStrategy extends PlayCardStrategy {
    public AIPlayCardStrategy(AIPlayCardStrategy next) {
        super(next);
    }
}
