package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChannelSubscriber implements ChannelSubscriberObserver{

    String name;

    List<Channel> channels = new ArrayList<>();

    public ChannelSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(Video video) {
        System.out.println("ChannelSubscriber " + name + " received video " + video.getTitle());
    }

    @Override
    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    @Override
    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    @Override
    public String getName() {
        return name;
    }
}
