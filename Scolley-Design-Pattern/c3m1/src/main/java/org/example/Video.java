package org.example;

public class Video {

    private Channel channel;
    private String title;
    private String description;
    private int length;
    private int likes;

    public Video(String title, String description, int length, Channel channel) {
        this.title = title;
        this.description = description;
        this.length = length;
        setChannel(channel);
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void addLike() {
        System.out.println("Video " + title + " received a like");
        likes += 1;
    }
}
