package com.yangjun.c3m1;

public class Video {
	private final String title;
	private final String description;
	private final Long length;
	private int likeCount;
	private final YoutubeChannel youtubeChannel;
	
	public Video(YoutubeChannel youtubeChannel, String title, String description, Long length) {
		this.youtubeChannel = youtubeChannel;
		this.title = title;
		this.description = description;
		this.length = length;
	}
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public Long getLength() {
		return length;
	}
	
	public void beingLiked() {
		likeCount++;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public YoutubeChannel getYoutubeChannel() {
		return youtubeChannel;
	}
}
