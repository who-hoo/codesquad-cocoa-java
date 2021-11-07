package day03.mission2;

public class Bomb extends Unit {

    final String REAL_SHAPE = "\uD83D\uDCA3";

    Bomb(int x, int y) {
        super(x, y);
        this.shape = "⬜";
    }
}
