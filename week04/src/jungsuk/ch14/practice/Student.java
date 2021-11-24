package jungsuk.ch14.practice;

public class Student {

    private String name;
    private boolean isMale;
    private int hak;
    private int ban;
    private int score;

    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public boolean isMail() {
        return isMale;
    }

    public int getHak() {
        return hak;
    }

    public int getBan() {
        return ban;
    }

    public int getScore() {
        return score;
    }


    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]",
            name,
            isMale ? "남" : "여",
            hak,
            ban,
            score);
    }

    enum Level {HIGH, MID, LOW}
}
