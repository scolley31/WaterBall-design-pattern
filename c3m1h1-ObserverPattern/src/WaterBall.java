public class WaterBall extends ChannelSubscriber {
    public WaterBall(String name) {
        super(name);
    }

    @Override
    public void doSomethingAfterNotify(Channel channel) {
        if (channel.getNewestVideo().getLength() >= 180) {
            System.out.printf("%s 對影片 \"%s\" 按讚。\n", getName(), channel.getNewestVideo().getTitle());
        }
    }
}
