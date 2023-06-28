import java.util.List;

public abstract class ChannelSubscriber implements ChannelSubscriberObserver{
    private String name;
    public ChannelSubscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
