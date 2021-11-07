package day03.mission2;

import java.util.*;

public class SimpleRPG {

    static Random randomGenerator = new Random();
    String[][] board = new String[5][5];
    Scanner joystick = new Scanner(System.in);
    Player p;
    Monster m;
    Bomb b;
    int score = 0;
    boolean gameOver = false;

    SimpleRPG() {
        initBoard();
        createPlayer();
        createBomb();
        createMonster();
    }

    void initBoard() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                board[row][col] = "⬜";
            }
        }
    }

    boolean isEmptySpace(int x, int y) {
        return board[x][y].equals("⬜");
    }

    boolean isMonster(int x, int y) {
        return board[x][y].equals(m.REAL_SHAPE);
    }

    boolean isBomb(int x, int y) {
        return board[x][y].equals(b.REAL_SHAPE);
    }

    void createPlayer() {
        p = new Player();
        board[p.x][p.y] = p.shape;
    }

    void createBomb() {
        int x = randomGenerator.nextInt(4);
        int y = randomGenerator.nextInt(4);

        if (isEmptySpace(x, y)) {
            b = new Bomb(x, y);
            board[b.x][b.y] = b.shape;
            return;
        }

        createBomb();
    }

    void createMonster() {
        int x = randomGenerator.nextInt(4);
        int y = randomGenerator.nextInt(4);

        if (isEmptySpace(x, y)) {
            m = new Monster(x, y);
            board[m.x][m.y] = m.shape;
            return;
        }

        createMonster();
    }

    void print() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    String getUserInput() {
        System.out.print("input direction >>>> ");
        return joystick.nextLine();
    }

    void movePlayer() {
        int prevX = p.x;
        int prevY = p.y;

        p.move(getUserInput());
        if (isBomb(p.x, p.y)) {
            gameOver();
            return;
        }
        if (isMonster(p.x, p.y)) {
            board[p.x][p.y] = p.shape;
            board[prevX][prevY] = "⬜";
            score++;
            createMonster();
        }
        board[p.x][p.y] = p.shape;
        board[prevX][prevY] = "⬜";
    }

    void run(SimpleRPG game) {
        while (!gameOver) {
            game.print();
            movePlayer();
        }
    }

    void gameOver() {
        System.out.println("score >>>>> " + score);
        gameOver = true;
    }

    public static void main(String[] args) {
        SimpleRPG game = new SimpleRPG();
        game.run(game);
    }
}
