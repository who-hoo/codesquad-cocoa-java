package hoosh;

import java.util.*;

public class Input {

    public static Scanner input = new Scanner(System.in);

    public static void close() {
        input.close();
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }
}
