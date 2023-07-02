package com.yangjun.c3m1;

import java.util.HashMap;
import java.util.Map;

public abstract class ChannelSubscriber {
	private final String name;
	private final Map<String ,YoutubeChannel> subscribeChannels = new HashMap<>();
	
	protected ChannelSubscriber(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void like(Video video) {
		System.out.printf("%s 對影片 \"%s\" 按讚。%n", name, video.getTitle());
		video.beingLiked();
	}
	
	public void subscribe(YoutubeChannel youtubeChannel) {
		System.out.printf("%s 訂閱了 %s。%n", name, youtubeChannel.getName());
		youtubeChannel.subscribe(this);
		subscribeChannels.put(youtubeChannel.getName(), youtubeChannel);
	}
	
	public void unSubscribe(YoutubeChannel youtubeChannel) {
		System.out.printf("%s 解除訂閱了 %s。%n", name, youtubeChannel.getName());
		youtubeChannel.unSubscribe(this);
		subscribeChannels.remove(youtubeChannel.getName());
	}
	
	public Map<String, YoutubeChannel> getSubscribeChannels() {
		return subscribeChannels;
	}
	
	public abstract void receiveVideoNotification(Video video);
}
