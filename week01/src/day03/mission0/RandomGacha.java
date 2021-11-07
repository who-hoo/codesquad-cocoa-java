package day03.mission0;

import java.util.*;

public class RandomGacha {

    final static int MIN_COIN = 1;

    Scanner coinSlot;
    private int coin = 0;
    List<CocoaMember> members;

    RandomGacha() {
        coinSlot = new Scanner(System.in);
        this.members = CocoaMember.makeSquad1();
        shuffleMember();
    }

    boolean isValidInput(String input) {
        try {
            if (Integer.parseInt(input) < MIN_COIN) {
                throw new InputMismatchException();
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력");
            return false;
        } catch (InputMismatchException e) {
            System.out.println("0보다 큰 숫자로 입력");
            return false;
        }
    }

    void getUserCoin() {
        String input = "";

        System.out.print("insert coin >>>>> ");
        input = coinSlot.nextLine();

        if (isValidInput(input)) {
            int coins = Integer.parseInt(input);
            insertCoin(coins);
            return;
        }

        getUserCoin();
    }

    void insertCoin(int coins) {
        this.coin += coins;
    }

    void shuffleMember() {
        Collections.shuffle(members);
    }

    void pick() {
        StringBuilder result = new StringBuilder();

        List<CocoaMember> subMembers = members.subList(0, Math.min(coin, members.size()));
        for (CocoaMember member: subMembers) {
            result.append(member.getName() + ", ");
            coin--;
        }
        System.out.println(result.substring(0, result.length() - 2));
    }

    void returnCoin() {
        System.out.println("남은 동전을 반환합니다...");
        for (; coin > 0; coin--) {
            System.out.print("\uD83D\uDFE1 ");
        }
        System.out.println("...끝!");
    }

    public static void main(String[] args) {
        RandomGacha gacha = new RandomGacha();

        gacha.getUserCoin();
        gacha.pick();
        gacha.returnCoin();
    }
}
