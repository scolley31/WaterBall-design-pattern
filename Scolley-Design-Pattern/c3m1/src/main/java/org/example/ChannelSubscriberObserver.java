package org.example;

public interface ChannelSubscriberObserver {

    void update(Video video);

    void addChannel(Channel channel);

    void removeChannel(Channel channel);

    String getName();
}
