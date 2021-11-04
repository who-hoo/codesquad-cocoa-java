package day03;

import java.util.*;
import java.util.stream.Stream;

public class CocoaMember {

    final static String[] SQUAD1 = {"donggi", "jerry", "k", "mandoo", "mk", "taksu", "tany",
        "nohri", "ttatgwi", "po", "hoo"};

    private String name;

    CocoaMember(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    static List<CocoaMember> makeSquad1() {
        // TODO: 파일에서 읽어오는 것으로 개선해보기
        // 이 메서드를 아예 없애고 RandomGacha() 에서 List members 초기화하자
        ArrayList<CocoaMember> squad = new ArrayList(CocoaMember.SQUAD1.length * 2);
        Stream<String> squadStream = Arrays.asList(CocoaMember.SQUAD1).stream();

        squadStream.forEach(name -> squad.add(new CocoaMember(name)));
        return squad;
    }
}
