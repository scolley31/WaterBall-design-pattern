package org.example;

public class FireBall extends ChannelSubscriber {

    public FireBall(String name) {
        super(name);
    }

    @Override
    public void update(Video video) {
        System.out.println("ChannelSubscriber " + name + " received video " + video.getTitle());
        if (video.getLength() <= 60) {
           video.getChannel().unSubScribe(this);
        }
    }
}
