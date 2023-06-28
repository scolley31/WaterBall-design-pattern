public class Main {
    public static void main(String[] args) {
        Channel 水球軟體學院 = new Channel("水球軟體學院");
        Channel pewDiePie = new Channel("PewDiePie");
        FireBall 火球 = new FireBall("火球");
        WaterBall 水球 = new WaterBall("水球");

        水球軟體學院.register(水球);
        pewDiePie.register(水球);

        水球軟體學院.register(火球);
        pewDiePie.register(火球);

        Video c1M1S2 = new Video("C1M1S2", "這個世界正是物件導向的呢！", 240);
        水球軟體學院.upload(c1M1S2);

        Video helloGuys = new Video("Hello guys", "Clickbait", 30);
        pewDiePie.upload(helloGuys);

        Video c1M1S3 = new Video("C1M1S3", "物件 vs. 類別", 60);
        水球軟體學院.upload(c1M1S3);

        Video minecraft = new Video("Minecraft", "Let's play Minecraft", 1800);
        pewDiePie.upload(minecraft);
    }
}