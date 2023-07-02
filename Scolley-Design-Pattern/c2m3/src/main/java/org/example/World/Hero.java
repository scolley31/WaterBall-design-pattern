package org.example.World;

public class Hero extends Sprite {

    private int HP = 30;

    public Hero() {
        name = "H";
    }

    public void addHP() {
        HP = HP + 10;
        System.out.println("Hero 加10滴血，目前有 "+HP+" 滴血");
    }

    public void reduceHP() {
        HP = HP - 10;
        System.out.println("Hero 減10滴血，目前有 "+HP+" 滴血)");
    }

}
