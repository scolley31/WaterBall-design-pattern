package com.yangjun.c3m1;

public class WaterBallChannelSubscriber extends ChannelSubscriber {

	public WaterBallChannelSubscriber(String name) {
		super(name);
	}

	@Override
	public void receiveVideoNotification(Video video) {
		if (video.getLength() >= 180000L) {
			like(video);
		}
	}
}
