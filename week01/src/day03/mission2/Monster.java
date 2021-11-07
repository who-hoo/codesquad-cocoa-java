package day03.mission2;

public class Monster extends Unit {

    final String REAL_SHAPE = "\uD83D\uDC7B";

    Monster(int x, int y) {
        super(x, y);
        this.shape = "⬜";
    }
}
