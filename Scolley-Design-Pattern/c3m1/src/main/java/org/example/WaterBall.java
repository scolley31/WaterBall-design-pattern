package org.example;

public class WaterBall extends ChannelSubscriber {
    public WaterBall(String name) {
        super(name);
    }

    @Override
    public void update(Video video) {
        System.out.println("ChannelSubscriber " + name + " received video " + video.getTitle());
        if (video.getLength() >= 180) {
            video.addLike();
        }
    }

}
