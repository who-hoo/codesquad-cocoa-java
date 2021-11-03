package day03;

import java.util.Scanner;

class CocoaMember {
    private String name;

    CocoaMember(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    static CocoaMember[] makeSquad1() {
        CocoaMember[] squad1 = {
                new CocoaMember("Donggi"),
                new CocoaMember("K"),
                new CocoaMember("Tany"),
                new CocoaMember("Taksu"),
                new CocoaMember("Hoo"),
                new CocoaMember("Jerry"),
                new CocoaMember("Nohri"),
                new CocoaMember("MK"),
                new CocoaMember("ttasjwi"),
                new CocoaMember("PO"),
                new CocoaMember("Mandoo")
        };

        return squad1;
    }
}

class RandomGacha {
    private int coin = 0;
    CocoaMember[] members;

    RandomGacha() {
        this.members = CocoaMember.makeSquad1();
    }

    void insertCoin(int coins) {
        this.coin += coins;
    }

    void shuffleMember() {
        // TODO: 랜덤뽑기를 위한 멤버 배열 섞기 구현
        // 배열을 섞는게 효율적일까, draw() 내에서 중복없는 난수를 발생시키는게 효율적일까?
    }

    void draw() {
        String result = "";

        int i = 0;
        while (coin > 0 && i < members.length) {
            result += (members[i].getName() + ", ");
            i++;
            coin--;
        }

        System.out.println(result);
        System.out.println("남은 동전을 반환합니다...");
        while (coin > 0) {
            System.out.print("\uD83D\uDFE1 ");
            coin--;
        }
        System.out.println();
        System.out.println("완료 !");
    }
}

class RandomGachaTest {
    public static void main(String[] args) {
        RandomGacha gacha = new RandomGacha();

        Scanner sc = new Scanner(System.in);
        System.out.print("insert coin >>>>> ");
        int coins = sc.nextInt();
        sc.close();

        gacha.insertCoin(coins);
        gacha.shuffleMember();
        gacha.draw();
    }
}
