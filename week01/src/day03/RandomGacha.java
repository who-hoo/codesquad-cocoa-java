package day03;

import java.util.*;

public class RandomGacha {

    private int coin = 0;
    CocoaMember[] members;

    RandomGacha() {
        this.members = CocoaMember.makeSquad1();
        shuffleMember();
    }

    int getUserCoin() {
        Scanner sc = new Scanner(System.in);
        int coins = 0;
        while (true) {
            System.out.print("insert coin >>>>> ");
            try {
                coins = Integer.parseInt(sc.nextLine());
                if (coins > 0) {
                    sc.close();
                    break;
                }
            } catch (Exception e) {
            }
            System.out.println("양의 정수로 입력하시죠");
        }
        return coins;
    }

    void insertCoin(int coins) {
        this.coin += coins;
    }

    void shuffleMember() {
        CocoaMember temp = null;
        for (int i = 0; i < members.length; i++) {
            int j = new Random().nextInt(members.length);
            temp = members[i];
            members[i] = members[j];
            members[j] = temp;
        }
    }

    void draw() {
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (coin > 0 && i < members.length) {
            result.append(members[i].getName() + ", ");
            i++;
            coin--;
        }

        System.out.println(result);
    }

    void returnCoin() {
        System.out.println("남은 동전을 반환합니다...");
        while (coin > 0) {
            System.out.print("\uD83D\uDFE1 ");
            coin--;
        }
        System.out.println("...끝!");
    }

    public static void main(String[] args) {
        RandomGacha gacha = new RandomGacha();

        int coins = gacha.getUserCoin();
        gacha.insertCoin(coins);
        gacha.draw();
        gacha.returnCoin();
    }
}
