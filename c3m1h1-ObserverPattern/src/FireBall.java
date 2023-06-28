import java.util.ArrayList;
import java.util.List;

public class FireBall extends ChannelSubscriber {
    public FireBall(String name) {
        super(name);
    }

    @Override
    public void doSomethingAfterNotify(Channel channel) {
        if (channel.getNewestVideo().getLength() <= 60) {
            channel.unsubscribe(this);
        }
    }
}
