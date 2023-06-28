package Strategy;

public abstract class HumanPlayCardStrategy extends PlayCardStrategy {

    public HumanPlayCardStrategy(HumanPlayCardStrategy next) {
        super(next);
    }
}
