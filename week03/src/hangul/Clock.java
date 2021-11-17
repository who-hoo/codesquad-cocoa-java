package hangul;

import java.time.LocalTime;

public class Clock implements Runnable {

    private static final String RESET = "\033[0m";
    private static final String GREEN_BOLD = "\033[1;32m";

    private String[][] clockView;

    public Clock() {
        initClock();
    }

    @Override
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            print();
            try {
                Thread.sleep(1000 * 60L);
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    public void print() {
        setClock();
        for (String[] row : clockView) {
            for (String e : row) {
                System.out.print(e + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void setClock() {
        initClock();
        LocalTime now = LocalTime.now();

        if (now.equals(LocalTime.MIDNIGHT)) {
            clockView[3][0] = GREEN_BOLD + clockView[3][0] + RESET;
            clockView[4][0] = GREEN_BOLD + clockView[4][0] + RESET;
            return;
        }

        if (now.equals(LocalTime.NOON)) {
            clockView[4][0] = GREEN_BOLD + clockView[4][0] + RESET;
            clockView[5][0] = GREEN_BOLD + clockView[5][0] + RESET;
            return;
        }
        setHour(now.getHour());
        setMinute(now.getMinute());
    }

    private void initClock() {
        clockView = new String[][]{
            {"한", "두", "세", "네", "다", "섯"},
            {"여", "섯", "일", "곱", "여", "덟"},
            {"아", "홉", "열", "한", "두", "시"},
            {"자", "이", "삼", "사", "오", "십"},
            {"정", "일", "이", "삼", "사", "오"},
            {"오", "육", "칠", "팔", "구", "분"}
        };
    }

    private void setHour(int hour) {
        clockView[2][5] = GREEN_BOLD + clockView[2][5] + RESET;
        switch (hour % 12) {
            case 0:
                clockView[2][2] = GREEN_BOLD + clockView[2][2] + RESET;
                clockView[2][4] = GREEN_BOLD + clockView[2][4] + RESET;
                break;
            case 1:
                clockView[0][0] = GREEN_BOLD + clockView[0][0] + RESET;
                break;
            case 2:
                clockView[0][1] = GREEN_BOLD + clockView[0][1] + RESET;
                break;
            case 3:
                clockView[0][2] = GREEN_BOLD + clockView[0][2] + RESET;
                break;
            case 4:
                clockView[0][3] = GREEN_BOLD + clockView[0][3] + RESET;
                break;
            case 5:
                clockView[0][4] = GREEN_BOLD + clockView[0][4] + RESET;
                clockView[0][5] = GREEN_BOLD + clockView[0][5] + RESET;
                break;
            case 6:
                clockView[1][0] = GREEN_BOLD + clockView[1][0] + RESET;
                clockView[1][1] = GREEN_BOLD + clockView[1][1] + RESET;
                break;
            case 7:
                clockView[1][2] = GREEN_BOLD + clockView[1][2] + RESET;
                clockView[1][3] = GREEN_BOLD + clockView[1][3] + RESET;
                break;
            case 8:
                clockView[1][4] = GREEN_BOLD + clockView[1][4] + RESET;
                clockView[1][5] = GREEN_BOLD + clockView[1][5] + RESET;
                break;
            case 9:
                clockView[2][0] = GREEN_BOLD + clockView[2][0] + RESET;
                clockView[2][1] = GREEN_BOLD + clockView[2][1] + RESET;
                break;
            case 10:
                clockView[2][2] = GREEN_BOLD + clockView[2][2] + RESET;
                break;
            case 11:
                clockView[2][2] = GREEN_BOLD + clockView[2][2] + RESET;
                clockView[2][3] = GREEN_BOLD + clockView[2][3] + RESET;
                break;
            default:
                break;
        }
    }

    private void setMinute(int minute) {
        if (minute == 0) {
            return;
        }
        clockView[5][5] = GREEN_BOLD + clockView[5][5] + RESET;

        int tens = minute / 10;
        if (tens > 0) {
            clockView[3][5] = GREEN_BOLD + clockView[3][5] + RESET;
            int idx = (tens - 1) > 0 ? (tens - 1) : 5;
            clockView[3][idx] = GREEN_BOLD + clockView[3][idx] + RESET;
        }

        int units = minute % 10;
        if (units > 0) {
            if (units < 6) {
                clockView[4][units] = GREEN_BOLD + clockView[4][units] + RESET;
            } else {
                clockView[5][units % 5] = GREEN_BOLD + clockView[5][units % 5] + RESET;
            }
        }
    }
}
