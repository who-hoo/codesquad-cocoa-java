package day03;

public class CocoaMember {

    private String name;

    CocoaMember(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    static CocoaMember[] makeSquad1() {
        return new CocoaMember[]{
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
    }
}
