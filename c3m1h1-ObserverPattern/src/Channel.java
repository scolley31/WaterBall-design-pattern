import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String name;
    private List<ChannelSubscriberObserver> channelSubscriberObserverList = new ArrayList<>();

    public List<ChannelSubscriberObserver> getChannelSubscriberObserverList() {
        return channelSubscriberObserverList;
    }

    private List<Video> videos = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    public void upload(Video video) {
        videos.add(video);
        System.out.printf("頻道 %s 上架了一則新影片 \"%s\"。\n", name, video.getTitle());
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (ChannelSubscriberObserver channelSubscriberObserver : channelSubscriberObserverList) {
            channelSubscriberObserver.doSomethingAfterNotify(this);
        }
    }

    public void register(ChannelSubscriberObserver channelSubscriberObserver) {
        System.out.printf("%s 訂閱了 %s。\n", channelSubscriberObserver.getName(), name);
        channelSubscriberObserverList.add(channelSubscriberObserver);
    }

    public void unsubscribe(ChannelSubscriberObserver channelSubscriberObserver) {
        List<ChannelSubscriberObserver> temp = new ArrayList<>();
        temp.addAll(channelSubscriberObserverList);
        temp.remove(channelSubscriberObserver);
        System.out.printf("%s 解除訂閱了 %s。\n", channelSubscriberObserver.getName(), name);
        channelSubscriberObserverList = temp;
    }

    public Video getNewestVideo() {
        return videos.get(videos.size() - 1);
    }
}
