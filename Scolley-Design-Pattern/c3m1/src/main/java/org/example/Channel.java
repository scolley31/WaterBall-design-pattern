package org.example;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    String name;
    List<ChannelSubscriberObserver> channelSubscriberObservers = new ArrayList<>();
    List<Video> videos = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    public void upLoad(Video video) {
        System.out.println("Channel " + name + " upload video " + video.getTitle());
        videos.add(video);
        List<ChannelSubscriberObserver> copy = new ArrayList<>(channelSubscriberObservers);
        for (ChannelSubscriberObserver channelSubscriberObserver : copy) {
            channelSubscriberObserver.update(video);
        }
    }

    public void subScribe(ChannelSubscriberObserver subscriber) {
        System.out.println("ChannelSubscriber " + subscriber.getName() + " subScribe channel " + name);
        channelSubscriberObservers.add(subscriber);
        subscriber.addChannel(this);
    }

    public void unSubScribe(ChannelSubscriberObserver subscriber) {
        System.out.println("ChannelSubscriber " + subscriber.getName() + " unSubScribe channel " + name);
        channelSubscriberObservers.remove(subscriber);
        subscriber.removeChannel(this);
    }

}
