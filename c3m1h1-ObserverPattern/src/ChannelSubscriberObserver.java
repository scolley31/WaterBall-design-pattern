public interface ChannelSubscriberObserver {
    String getName();

    void doSomethingAfterNotify(Channel channel);
}
