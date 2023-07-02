package com.yangjun.c3m1;

public class FireBallChannelSubscriber extends ChannelSubscriber {
	public FireBallChannelSubscriber(String name) {
		super(name);
	}
	
	@Override
	public void receiveVideoNotification(Video video) {
		if (video.getLength() <= 60000L) {
			unSubscribe(video.getYoutubeChannel());
		}
	}
}
