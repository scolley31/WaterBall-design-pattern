package org.example;

public class Main {
    public static void main(String[] args) {

        WaterBall waterBall = new WaterBall("WaterBall");
        FireBall fireBall = new FireBall("FireBall");
        Channel waterBallClub = new Channel("水球軟體學院");
        Channel pewDiePie = new Channel("PewDiePie");
        Video c1m1s2 = new Video("C1M1S2", "這個世界正是物件導向的呢！", 240, waterBallClub);
        Video helloGuys = new Video("HelloGuys", "Clickbait", 30, pewDiePie);
        Video c1m1S3 = new Video("C1M1S3", "物件 vs. 類別", 60, waterBallClub);
        Video minecraft = new Video("Minecraft", "Let’s play Minecraft", 1800, pewDiePie);

        waterBallClub.subScribe(waterBall);
        waterBallClub.subScribe(fireBall);
        pewDiePie.subScribe(waterBall);
        pewDiePie.subScribe(fireBall);

        waterBallClub.upLoad(c1m1s2);
        pewDiePie.upLoad(helloGuys);
        waterBallClub.upLoad(c1m1S3);
        pewDiePie.upLoad(minecraft);

    }
}