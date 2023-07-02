package com.yangjun.c3m1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YoutubeChannel {
	private final String name;
	private final Map<String, ChannelSubscriber> subscriber = new HashMap<>();
	private final List<Video> videos = new ArrayList<>();	
	
	public YoutubeChannel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void subscribe(ChannelSubscriber channelSubscriber) {
		subscriber.put(channelSubscriber.getName(), channelSubscriber);
	}
	
	public void unSubscribe(ChannelSubscriber channelSubscriber) {
		subscriber.remove(channelSubscriber.getName());
	}
	
	public void upload(Video video) {
		System.out.printf("頻道 %s 上架了一則新影片 \"%s\"。%n", name, video.getTitle());
		videos.add(video);
		notifySubscribers(video);
	}
	
	public void notifySubscribers(Video video) {
        List<ChannelSubscriber> subscribersSnapshot = new ArrayList<>(subscriber.values());
        subscribersSnapshot.forEach(s -> s.receiveVideoNotification(video));
    }
}
