package com.yangjun.c3m1;

public class Main {
	public static void main(String[] args) {
		ChannelSubscriber waterBall = new WaterBallChannelSubscriber("水球");
	    ChannelSubscriber fireBall = new FireBallChannelSubscriber("火球");
		YoutubeChannel waterBallChannel = new YoutubeChannel("水球軟體學院");
		YoutubeChannel pewDiePieChannel = new YoutubeChannel("PewDiePie");
		
		waterBall.subscribe(waterBallChannel);
		waterBall.subscribe(pewDiePieChannel);
		fireBall.subscribe(waterBallChannel);
		fireBall.subscribe(pewDiePieChannel);
		
		waterBallChannel.upload(new Video(waterBallChannel, "C1M1S2", "這個世界正是物件導向的呢！", 240000L));
		pewDiePieChannel.upload(new Video(pewDiePieChannel, "Hello guys", "Clickbait", 30000L));
		waterBallChannel.upload(new Video(waterBallChannel, "C1M1S3", "物件 vs. 類別", 60000L));
		pewDiePieChannel.upload(new Video(pewDiePieChannel, "Minecraft", "Let’s play Minecraft", 1800000L));
	}
}
