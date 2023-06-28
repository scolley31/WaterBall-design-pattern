public class Video {
    private String title;
    private String description;
    private int length;

    public Video(String title, String description, int length) {
        this.title = title;
        this.description = description;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
